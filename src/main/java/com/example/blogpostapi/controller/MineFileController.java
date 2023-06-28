package com.example.blogpostapi.controller;

import com.example.blogpostapi.service.MineFile;
import com.example.blogpostapi.service.impl.CSVMineFile;
import com.example.blogpostapi.service.impl.PDFMineFile;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping(value = "/file")
public class MineFileController {

    @Autowired
    CSVMineFile csvMineFile;
    @Autowired
    PDFMineFile pdfMineFile;

    @GetMapping(path = "/mine", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mineFile(@RequestParam String path, @RequestParam String type) throws IOException {
        MineFile.FileTypes fileType = MineFile.FileTypes.getFileTypeEnum(type);
        if (fileType == null) return new ResponseEntity<>("invalid message", OK);
        MineFile mineFile = null;
        if (fileType.equals(MineFile.FileTypes.PDF)) {
            mineFile = pdfMineFile;
        } else {
            mineFile = csvMineFile;
        }
        return new ResponseEntity<>(mineFile.mine(path), OK);
    }
}
