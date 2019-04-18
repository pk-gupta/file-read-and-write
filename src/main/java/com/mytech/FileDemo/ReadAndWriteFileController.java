package com.mytech.FileDemo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
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
    @PostMapping("/files/save")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty",HttpStatus.NOT_FOUND);
        }
            readAndWriteFileService.saveFile(file);
            return new ResponseEntity<>(file.getOriginalFilename()+"file uploaded successfully", HttpStatus.OK);


    }

}
