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
public class AssetService {

    public static final String ASSET_DEFINITIONS_FOLDER = "assetdefinitions/";
    public static final String ASSET_REPOSITORY = "repository/";

    @Autowired
    private AssetIdGenerator assetIdGenerator;

    public JSONObject getAssetDefinition(String assetName) throws IOException, ParseException {
        File assetFile = new File(ASSET_DEFINITIONS_FOLDER + assetName + ".definition");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(assetFile));
        return (JSONObject) obj;
    }

    public List<JSONObject> getAssetDefinitionsList() {
        File folder = new File(ASSET_DEFINITIONS_FOLDER);
        File[] listOfFiles = folder.listFiles();
        JSONParser parser = new JSONParser();
        List<JSONObject> jsonObjects = new ArrayList<>();
        Object obj;
        for (File listOfFile : listOfFiles != null ? listOfFiles : new File[0]) {
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

    public Map<String, JSONObject> getAssetDefinitionsMap() {
        File folder = new File(ASSET_DEFINITIONS_FOLDER);
        System.out.println(folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        JSONParser parser = new JSONParser();
        Map<String, JSONObject> jsonObjects = new HashMap<>();
        Object obj;
        for (File listOfFile : listOfFiles != null ? listOfFiles : new File[0]) {
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



    public JSONObject getAsset(Long assetId) {

        if(assetId >= AssetIdGenerator.ASSET_ID_BEGINNING) {
            List<JSONObject> assetList = listOfAssets();
            for (JSONObject asset : assetList) {
                if(asset.containsValue(assetId)) {
                    return asset;
                }
            }

        }
        if(assetId < AssetIdGenerator.ASSET_ID_BEGINNING) {
            for (JSONObject assetDefinition : getAssetDefinitionsList()) {
                if(assetDefinition.containsValue(assetId)) {
                    return assetDefinition;
                }
            }
        }
        return null;
    }

    public Map<String, JSONObject> mapOfAssets() {
        File folder = new File(ASSET_REPOSITORY);
        File[] listOfFiles = folder.listFiles();
        JSONParser parser = new JSONParser();
        Map<String, JSONObject> jsonObjects = new HashMap<>();
        Object obj;
        for (File listOfFile : listOfFiles != null ? listOfFiles : new File[0]) {
            try {
                obj = parser.parse(new FileReader(listOfFile));
                JSONObject jsonObject = (JSONObject) obj;
                jsonObjects.put(listOfFile.getName(), jsonObject);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }

    public List<JSONObject> listOfAssets() {
        File folder = new File(ASSET_REPOSITORY);
        File[] listOfFiles = folder.listFiles();
        JSONParser parser = new JSONParser();
        List<JSONObject> jsonObjects = new ArrayList<>();
        Object obj;
        for (File listOfFile : listOfFiles != null ? listOfFiles : new File[0]) {
            try {
                obj = parser.parse(new FileReader(listOfFile));
                JSONObject jsonObject = (JSONObject) obj;
                jsonObjects.add(jsonObject);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }


    @SuppressWarnings("unchecked")
    public String createAssetDefinition(String assetName, JSONObject jsonObject) throws IOException {

        @SuppressWarnings("AccessStaticViaInstance")
        Long generatedId = assetIdGenerator.generateNewAssetId(AssetIdGenerator.ASSET_DEFINITION_ID);
        jsonObject.put("assetId", generatedId);
        assetIdGenerator.saveAssetIdToList(AssetIdGenerator.ASSET_DEFINITION_ID, generatedId);

        File file = new File(ASSET_DEFINITIONS_FOLDER + assetName + "." + "definition");
        if (!file.exists()) {
            try {
                if(file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    public String assetCreation(String assetName, JSONObject jsonObject) throws IOException {
        Long generatedId = assetIdGenerator.generateNewAssetId(AssetIdGenerator.ASSET_ID);
        jsonObject.put("assetId", generatedId);
        try {
            JSONObject assetDefinition = getAssetDefinition(assetName);
            for (Object o : assetDefinition.keySet()) {
                System.out.println("Asset definition keyset key: " + o.toString());
                System.out.println("Newly created article value for key: " + jsonObject.get(o));
                if (jsonObject.get(o).toString().isEmpty()) {
                    return "Required asset value was empty";
                }
            }
        } catch (FileNotFoundException e) {
            return "Asset doesn't exist";
        } catch (NullPointerException e) {
            return "Required asset value was null";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assetIdGenerator.saveAssetIdToList(AssetIdGenerator.ASSET_ID, generatedId);
        File file = new File(ASSET_REPOSITORY + generatedId + "." + assetName);
        if (!file.exists()) {
            try {
                if(file.createNewFile()) {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
