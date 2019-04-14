package com.mytech.FileDemo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.json.config}")
    private String jsonConfig;

    @Value("${spring.json.data}")
    private String jsonData;

    public List<String> getFileList() {
        List<String> files = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:"+jsonData);
            // If we want to to use external location of project
            //File file = ResourceUtils.getFile(jsonConfig);
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
            File file = ResourceUtils.getFile("classpath:"+jsonData+fileName);
            // If we want to to use external location of project
            //File file = ResourceUtils.getFile(jsonConfig+fileName);
            FileReader fileReader = new FileReader(file);
            Object obj = jsonParser.parse(fileReader);
            jsonObject = (JSONObject)obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
