package lv.id.andrise.javacadabra.webservice.rest.util;

import java.util.List;

/**
 * Created by andris on 15.29.9.
 */
public class AssetIdGenerator {

    public Long generateNewAssetId() {
        Long generatedId = getLastCreatedAssetId() + 1;
        saveAssetIdToList(generatedId);
        return generatedId;
    }

    public List<Long> getAllAssetIds() {

        return null;
    }

    public Long getLastCreatedAssetId() {

        return null;
    }

    public void saveAssetIdToList(Long id) {

    }
}
