package lv.id.andrise.javacadabra.webservice.rest.util;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by andris on 15.29.9.
 */
@Service
public class AssetIdGenerator {

    public static final Long ASSET_ID_BEGINNING = 100_000_000_000_000L;
    public static final Long ASSET_DEFINITION_ID_BEGINNING = 0L;
    public static final String ASSET_ID = "assetId";
    public static final String ASSET_DEFINITION_ID = "assetDefinitionId";
    public static final String SEQUENCE_TXT = "sequence.txt";
    public static final String CONFIG_FOLDER = "config/";

    public Long generateNewAssetId(String assetType) throws IOException {
        return getLastCreatedAssetId(assetType) + 1L;
    }

    private Long getLastCreatedAssetId(String assetType) throws IOException {
        Long maxValue = null;
        File file = new File(CONFIG_FOLDER + assetType + SEQUENCE_TXT);
        if (!file.exists() && assetType.equals(ASSET_ID)) {
            saveAssetIdToList(assetType, ASSET_ID_BEGINNING);
            return ASSET_ID_BEGINNING;
        }
        else if(!file.exists() && assetType.equals(ASSET_DEFINITION_ID)){
            saveAssetIdToList(assetType, ASSET_DEFINITION_ID_BEGINNING);
            return ASSET_DEFINITION_ID_BEGINNING;
        }

        else if(file.exists() && (assetType.equals(ASSET_ID) || assetType.equals(ASSET_DEFINITION_ID))){
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<Long> assetIds = new ArrayList<>();
            br.lines().forEach(s -> assetIds.add(Long.valueOf(s)));
            maxValue = Collections.max(assetIds);
            System.out.println("Max id: " + maxValue);
        }
        return maxValue;
    }

    public void saveAssetIdToList(String assetType, Long id) throws IOException {
        File file = new File(CONFIG_FOLDER + assetType + SEQUENCE_TXT);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.valueOf(id));
        bw.newLine();
        bw.close();
    }
}
