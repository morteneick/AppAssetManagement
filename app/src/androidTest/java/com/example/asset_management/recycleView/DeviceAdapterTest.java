package com.example.asset_management.recycleView;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeviceAdapterTest {

    @Test
    public void getItemCount() {
        ArrayList<Device> list = new ArrayList<Device>();
        DeviceAdapter.OnNoteListener mOnNoteListener = null;

        for(int i = 0; i<49; i++){
            list.add(new Device());
        }

        DeviceAdapter adapter = new DeviceAdapter(list, mOnNoteListener);

        int expectedInt = 50;
        int actualInt = adapter.getItemCount();

        Assert.assertEquals(actualInt,actualInt);
    }
    @Test
    public void getItemCountIs0() {
        ArrayList<Device> list = new ArrayList<Device>();
        DeviceAdapter.OnNoteListener mOnNoteListener = null;

        for(int i = 0; i<0; i++){
            list.add(new Device());
        }

        DeviceAdapter adapter = new DeviceAdapter(list, mOnNoteListener);

        int expectedInt = 0;
        int actualInt = adapter.getItemCount();

        Assert.assertEquals(actualInt,actualInt);
    }
}