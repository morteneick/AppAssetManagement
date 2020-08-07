package com.example.asset_management.recycleView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asset_management.R;
import com.example.asset_management.addDevice.AddDeviceActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import kotlin.reflect.KVariance;

import static org.junit.Assert.*;

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