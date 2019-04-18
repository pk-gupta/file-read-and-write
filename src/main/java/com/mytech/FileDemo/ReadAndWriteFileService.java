package com.mytech.FileDemo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReadAndWriteFileService {

    @Value("${spring.json.config}")
    private String jsonConfig;

    @Value("${spring.json.data}")
    private String jsonData;

    @Value("${spring.json.dir}")
    private String jsonDir;


    public List<String> getFileList() {
        List<String> files = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile(jsonDir);
            //Classpath will use data from target dir
            //File file = ResourceUtils.getFile("classpath:"+jsonData);
            //If we want to read file from external location
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
            File file = ResourceUtils.getFile(jsonDir+fileName);
            //Classpath will use data from target dir
            //File file = ResourceUtils.getFile("classpath:"+jsonData+fileName);
            // If we want to read file from external location
            //File file = ResourceUtils.getFile(jsonConfig+fileName);
            FileReader fileReader = new FileReader(file);
            Object obj = jsonParser.parse(fileReader);
            jsonObject = (JSONObject)obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public void saveFile(MultipartFile file){
        Path path = Paths.get(jsonDir+file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
