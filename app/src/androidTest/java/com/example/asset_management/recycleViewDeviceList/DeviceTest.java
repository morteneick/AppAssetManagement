package com.example.asset_management.recycleViewDeviceList;

import org.junit.Assert;
import org.junit.Test;

public class DeviceTest {


    @Test
    public void addDevice() {

    }

    @Test
    public void testToString() {
        Device device = new Device("1", "2","3","4","5","6");

        String actualString = "Device{inventoryNumber='1', serialNumber='2', manufacturer='3', model='4', deviceCategorie='5', status='6'}";
        String expectedString = device.toString();
        Assert.assertEquals(actualString,expectedString);
    }
}