package com.example.blogpostapi.service;

import com.example.blogpostapi.models.ParsedData;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MineFile {

    public enum FileTypes {
        PDF, CSV;

        FileTypes(){}

        public static FileTypes getFileTypeEnum(String type) {
            if (Strings.isBlank(type)) return null;
            if (type.equals("pdf")) return PDF;
            if (type.equals("csv")) return CSV;
            return null;
        }
    }

    public String mine(String path) throws IOException {
        FileOutputStream file = openFile(path);
        Object object = extractData(file);
        ParsedData data = parseData(object);
        String analysis = analyzeData(data);
        String link = sendReport(analysis);
        closeFile(file);
        return link;
    }

    public FileOutputStream openFile(String path) throws FileNotFoundException {
        List<String> asda = new ArrayStack<>();
        asda.get(100);
        new ArrayList<>();
        return new FileOutputStream(new File(path));
    }

    public abstract Object extractData(FileOutputStream file);

    public abstract ParsedData parseData(Object object);

    public String analyzeData(ParsedData data) {
        return data.getNames().stream().reduce(String::concat).orElse("none");
    }

    public String sendReport(String analysis) {
        return analysis;
    }

    public void closeFile(FileOutputStream file) throws IOException {
        file.close();
    }
}
