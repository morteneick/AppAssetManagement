package com.example.asset_management.deviceCard.ui.card;

import com.example.asset_management.recycleViewDeviceList.Device;

import org.junit.Assert;
import org.junit.Test;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

public class CardFragmentTest {

    @Test
    public void getPosition0() {
        Device device = new Device();
        device.setStatus("Verfügbar");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPosition1() {
        Device device = new Device();
        device.setStatus("Ausgeliehen");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPosition2() {
        Device device = new Device();
        device.setStatus("In Wartung");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPosition3() {
        Device device = new Device();
        device.setStatus("Außer Betrieb");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPosition4() {
        Device device = new Device();
        device.setStatus("Defekt");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPosition5() {
        Device device = new Device();
        device.setStatus("Verschollen/Verschwunden");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPosition6() {
        Device device = new Device();
        device.setStatus("Gestohlen");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void getPositionFailed() {
        Device device = new Device();
        device.setStatus("");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

    @Test
    public void onDateSet() {
        Device device = new Device();
        device.setStatus("");
        Assert.assertEquals(device.getPosition(getApplicationContext()),0);
    }

}