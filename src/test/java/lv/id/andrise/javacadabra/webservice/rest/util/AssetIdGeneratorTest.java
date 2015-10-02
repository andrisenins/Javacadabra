package lv.id.andrise.javacadabra.webservice.rest.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

import static org.junit.Assert.assertEquals;


/**
 * Created by andris on 15.2.10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AssetIdGenerator.class)
public class AssetIdGeneratorTest {



    @Autowired
    private AssetIdGenerator assetIdGenerator;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenerateNewAssetIdFileDoesntExist() throws Exception {
        String assetType = "assetId";
        Long expectedId = AssetIdGenerator.ASSET_ID_BEGINNING + 1;
        File file = new File(AssetIdGenerator.CONFIG_FOLDER + assetType + AssetIdGenerator.SEQUENCE_TXT);
        file.delete();
        assertEquals(expectedId, assetIdGenerator.generateNewAssetId(assetType));
        file.delete();
    }

    @Test
    public void testGenerateNewAssetIdForAssetFileExist() throws Exception {
        String assetType = "assetId";
        Long expectedId = AssetIdGenerator.ASSET_ID_BEGINNING + 1;
        File file = new File(AssetIdGenerator.CONFIG_FOLDER + assetType + AssetIdGenerator.SEQUENCE_TXT);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.valueOf(AssetIdGenerator.ASSET_ID_BEGINNING));
        bw.newLine();
        bw.close();
        assetIdGenerator.generateNewAssetId(assetType);
        assertEquals(expectedId, assetIdGenerator.generateNewAssetId(assetType));
    }

    @Test
    public void testGenerateNewAssetIdForAssetDefinitionFileDoesntExist() throws Exception {
        String assetType = "assetDefinitionId";
        Long expectedId = AssetIdGenerator.ASSET_DEFINITION_ID_BEGINNING + 1;
        File file = new File(AssetIdGenerator.CONFIG_FOLDER + assetType + AssetIdGenerator.SEQUENCE_TXT);
        file.delete();
        assertEquals(expectedId, assetIdGenerator.generateNewAssetId(assetType));
        file.delete();
    }

    @Test
    public void testSaveAssetIdToList() throws Exception {
        String assetType = "assetDefinitionId";
        Long expectedId = AssetIdGenerator.ASSET_DEFINITION_ID_BEGINNING + 1;
        File file = new File(AssetIdGenerator.CONFIG_FOLDER + assetType + AssetIdGenerator.SEQUENCE_TXT);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.valueOf(AssetIdGenerator.ASSET_DEFINITION_ID_BEGINNING));
        bw.newLine();
        bw.close();
        assetIdGenerator.saveAssetIdToList(assetType, 1L);
        File file2 = new File(AssetIdGenerator.CONFIG_FOLDER + assetType + AssetIdGenerator.SEQUENCE_TXT);
        BufferedReader br = new BufferedReader(new FileReader(file2));
        assertEquals(expectedId, Long.valueOf(br.readLine()));
    }
}