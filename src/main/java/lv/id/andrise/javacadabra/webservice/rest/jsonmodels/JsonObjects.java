package lv.id.andrise.javacadabra.webservice.rest.jsonmodels;


import lv.id.andrise.javacadabra.webservice.rest.util.AssetIdGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final String ASSET_DEFINITIONS_FOLDER = "assetdefinitions/";
    public static final String ASSET_REPOSITORY = "repository/";

    @Autowired
    private AssetIdGenerator assetIdGenerator;

    public List<JSONObject> readJson() {
        File folder = new File(ASSET_DEFINITIONS_FOLDER);
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
        File folder = new File(ASSET_DEFINITIONS_FOLDER);
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

    public String assetDefinition(String assetName, JSONObject jsonObject) throws IOException {
        Long generatedId = assetIdGenerator.generateNewAssetId(assetIdGenerator.ASSET_DEFINITION_ID);
        jsonObject.put("assetId", generatedId);
        File file = new File(ASSET_DEFINITIONS_FOLDER + assetName + "." + "definition");
        if(!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    public String assetCreation(String assetName, JSONObject jsonObject) throws IOException {
        Long generatedId = assetIdGenerator.generateNewAssetId(assetIdGenerator.ASSET_ID);
        jsonObject.put("assetId", generatedId);
        File file = new File(ASSET_REPOSITORY + generatedId + "." + assetName);
        if(!file.exists()) {
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
    public JSONObject getAsset(Long assetId) {
        return null;
    }
}
