package com.mytech.FileDemo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReadAndWriteFileService {

    List<String> files = new ArrayList<>();
    public List<String> getFileList() {
        try {
            File file = ResourceUtils.getFile("classpath:json-data/");
            System.out.println(file);
            for(File jsonFile : file.listFiles()){
                files.add(jsonFile.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return files;
    }
    public JSONObject getFile(String fileName) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            File file = ResourceUtils.getFile("classpath:json-data/"+fileName);
            FileReader fileReader = new FileReader(file);
            Object obj = jsonParser.parse(fileReader);
            jsonObject = (JSONObject)obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
