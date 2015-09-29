package lv.id.andrise.javacadabra.webservice.rest.controllers;

import lv.id.andrise.javacadabra.webservice.rest.jsonmodels.JsonObjects;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by andris on 15.22.9.
 */
@RestController
public class MainController {

    @Autowired
    private JsonObjects jsonObjects;

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public List<JSONObject> assetDefinitionList() {
        return jsonObjects.readJson();
    }

    @RequestMapping(value = "/jsonmap", method = RequestMethod.GET)
    public Map<String, JSONObject> assetDefinitionMap() {
        return jsonObjects.mapOfJsons();
    }

    @RequestMapping(value = "/define/asset/{assetName}", method = RequestMethod.POST)
    public String defineNewAsset(@PathVariable String assetName,@RequestBody JSONObject jsonObject) throws IOException {
        jsonObjects.assetDefinition(assetName, jsonObject);
        return "success";
    }

    @RequestMapping(value = "/create/asset/{assetName}", method = RequestMethod.POST)
    public String createAsset(@PathVariable String assetName, @RequestBody JSONObject jsonObject) throws IOException {
        jsonObjects.assetCreation(assetName, jsonObject);
        return "success";
    }

}
