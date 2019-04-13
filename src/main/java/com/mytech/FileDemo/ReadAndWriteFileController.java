package com.mytech.FileDemo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ReadAndWriteFileController {

    @Autowired
    private ReadAndWriteFileService readAndWriteFileService;

    @GetMapping("/files")
    public ResponseEntity<List<String>> getFiles(){
        return new ResponseEntity<>(readAndWriteFileService.getFileList(), HttpStatus.OK);
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<JSONObject> getFiles(@PathVariable String fileName){
        return new ResponseEntity<>(readAndWriteFileService.getFile(fileName), HttpStatus.OK);
    }

}
