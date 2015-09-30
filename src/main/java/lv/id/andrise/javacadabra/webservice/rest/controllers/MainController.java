package lv.id.andrise.javacadabra.webservice.rest.controllers;

import lv.id.andrise.javacadabra.webservice.rest.jsonmodels.AssetService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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
    private AssetService assetService;

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public List<JSONObject> assetDefinitionList() {
        return assetService.getAssetDefinitionsList();
    }

    @RequestMapping(value = "/jsonmap", method = RequestMethod.GET)
    public Map<String, JSONObject> assetDefinitionMap() {
        return assetService.getAssetDefinitionsMap();
    }

    @RequestMapping(value = "/assets/jsonmap", method = RequestMethod.GET)
    public Map<String, JSONObject> assetsMap() {
        return assetService.mapOfAssets();
    }

    @RequestMapping(value = "/get/asset/{assetName}", method = RequestMethod.GET)
    public JSONObject assetsMap(@PathVariable String assetName) throws IOException, ParseException {
        return assetService.getAssetDefinition(assetName);
    }

    @RequestMapping(value = "/define/asset/{assetName}", method = RequestMethod.POST)
    public String defineNewAsset(@PathVariable String assetName,@RequestBody JSONObject jsonObject) throws IOException {
        assetService.createAssetDefinition(assetName, jsonObject);
        return "success";
    }

    @RequestMapping(value = "/create/asset/{assetName}", method = RequestMethod.POST)
    public String createAsset(@PathVariable String assetName, @RequestBody JSONObject jsonObject) throws IOException {

        return assetService.assetCreation(assetName, jsonObject);
    }

    @RequestMapping(value = "/get/asset/byid/{id}", method = RequestMethod.GET)
    public JSONObject getAssetById(@PathVariable Long id) throws IOException, ParseException {
        return assetService.getAsset(id);
    }

}
