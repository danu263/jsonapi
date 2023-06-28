package com.example.blogpostapi.service.impl;

import com.example.blogpostapi.models.ParsedData;
import com.example.blogpostapi.service.MineFile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class CSVMineFile extends MineFile {
    @Override
    public Object extractData(FileOutputStream file) {
        /// adasidhjasdioh
        return null;
    }

    @Override
    public ParsedData parseData(Object object) {
        return null;
    }
}
