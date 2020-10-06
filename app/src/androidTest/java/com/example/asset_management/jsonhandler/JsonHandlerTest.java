package com.example.asset_management.jsonhandler;

import com.example.asset_management.recycleViewDeviceList.Device;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;


public class JsonHandlerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    String jsonName = "DeviceTest.json";
    Device device = new Device();

    @Before public void initialize() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 05);
        calendar.set(Calendar.YEAR, 1996);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();
        Timestamp timestamp = Timestamp.valueOf("2007-09-23 10:10:10.0");

        device.setInventoryNumber("1");
        device.setModel("Model");
        device.setManufacturer("Manufacturer");
        device.setSerialNumber("Serialnumber");
        device.setGuarantee(date);
        device.setNote("Note");
        device.setDeviceStatus(1);
        device.setDeviceStatus(1);
        device.setCategory("Category");
        device.setLongitude(1.0);
        device.setLatitude(1.0);
//        device.setLastUvv(date);
//        device.setLastTuev(date);
//        device.setLastRepair(date);
//        device.setLastChange(date);
        device.setProjectId(1);
        device.setName("Name");
        device.setStreet("Street");
        device.setPostcode("Postcode");
        device.setStatus("Status");
        device.setRepairNote("RepairNote");
        device.setLastLocationUpdate(timestamp);
        device.setBeaconMajor("1");
        device.setBeaconMinor("1");
        device.setCity("City");
        device.setDescription("Description");
        device.setTimestamp(timestamp);
    }

    @Test
    public void testIfFileCreated() {
        JsonHandler.createJsonFromObject(device, jsonName, getApplicationContext());

    File file = new File(String.valueOf(getApplicationContext().getFilesDir())
            +"/DeviceTest.json");

        Assert.assertTrue(file.exists());
    }

    @Test
    public void testCreateJson() throws IOException {

//        String jsonActual = "{\"deviceCategorie\":\"5\",\"inventoryNumber\":\"1\"," +
//                "\"manufacturer\":\"3\",\"model\":\"4\",\"serialnumber\":\"2\",\"status\":\"6\"}";
//
//        String jsonConvert = JsonHandler.convertIntoString(device);

        Device deviceFromJson = JsonHandler.getDevice(jsonName, getApplicationContext());
        String is = JsonHandler.convertIntoString(deviceFromJson);
        String actual = JsonHandler.convertIntoString(device);

        Assert.assertEquals(is, actual);
    }

//    @Test
//    public void testIfFileDataEqualsInsertedData() {
//
//        JsonHandler.createJsonFromObject(device, jsonName, getApplicationContext());
//
//        String jsonActual = "{\"deviceCategorie\":\"5\",\"inventoryNumber\":\"1\"," +
//                "\"manufacturer\":\"3\",\"model\":\"4\",\"serialnumber\":\"2\",\"status\":\"6\"}\n";
//
//        String jsonConvert = "";
//        File file = new File(String.valueOf(getApplicationContext()
//                .getFilesDir())+"/DeviceTest.json");
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            DataInputStream in = new DataInputStream(fis);
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//            String strLine;
//            while ((strLine = br.readLine()) != null) {
//                jsonConvert = jsonConvert + strLine + "\n";
//            }
//            br.close();
//            in.close();
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Assert.assertEquals(jsonActual,jsonConvert);
//    }
}