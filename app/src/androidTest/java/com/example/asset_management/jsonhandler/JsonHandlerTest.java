package com.example.asset_management.jsonhandler;

import com.example.asset_management.recycleView.Device;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;


public class JsonHandlerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    String jsonName = "DeviceTest.json";
    Device device = new Device("1","2","3",
            "4","5","6");

    @Test
    public void testIfFileCreated() {

        JsonHandler.createJsonFromObject(device, jsonName, getApplicationContext());

    File file = new File(String.valueOf(getApplicationContext().getFilesDir())
            +"/DeviceTest.json");

        Assert.assertTrue(file.exists());
    }

    @Test
    public void testCreateJson() {

        String jsonActual = "{\"deviceCategorie\":\"5\",\"inventoryNumber\":\"1\"," +
                "\"manufacturer\":\"3\",\"model\":\"4\",\"serialnumber\":\"2\",\"status\":\"6\"}";

        String jsonConvert = JsonHandler.convertIntoString(device);

        Assert.assertEquals(jsonActual,jsonConvert);
    }

    @Test
    public void testIfFileDataEqualsInsertedData() {

        JsonHandler.createJsonFromObject(device, jsonName, getApplicationContext());

        String jsonActual = "{\"deviceCategorie\":\"5\",\"inventoryNumber\":\"1\"," +
                "\"manufacturer\":\"3\",\"model\":\"4\",\"serialnumber\":\"2\",\"status\":\"6\"}\n";

        String jsonConvert = "";
        File file = new File(String.valueOf(getApplicationContext()
                .getFilesDir())+"/DeviceTest.json");
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                jsonConvert = jsonConvert + strLine + "\n";
            }
            br.close();
            in.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(jsonActual,jsonConvert);
    }
}