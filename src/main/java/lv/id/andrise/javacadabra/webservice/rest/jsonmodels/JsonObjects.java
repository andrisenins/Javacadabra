package lv.id.andrise.javacadabra.webservice.rest.jsonmodels;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andris on 15.22.9.
 */
@Service
public class JsonObjects {

    public List<JSONObject> readJson() {
        File folder = new File("assetdefinitions/");
        File[] listOfFiles = folder.listFiles();
        JSONParser parser = new JSONParser();
        List<JSONObject> jsonObjects = new ArrayList<>();
        Object obj;
        for (File listOfFile : listOfFiles) {
            try {
                obj = parser.parse(new FileReader(listOfFile.getName()));
                JSONObject jsonObject = (JSONObject) obj;
                jsonObjects.add(jsonObject);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }

    public Map<String, JSONObject> mapOfJsons() {
        File folder = new File("assetdefinitions/");
        System.out.println(folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        JSONParser parser = new JSONParser();
        Map<String, JSONObject> jsonObjects = new HashMap<>();
        Object obj;
        for (File listOfFile : listOfFiles) {
            try {
                obj = parser.parse(new FileReader(listOfFile.getName()));
                JSONObject jsonObject = (JSONObject) obj;
                jsonObjects.put(listOfFile.getName(), jsonObject);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }

    public String assetDefinition(String assetName, JSONObject jsonObject) {
        File file = new File("." + assetName);
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("." + assetName);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String assetCreation(String assetName, JSONObject jsonObject) {

        return null;
    }
}
