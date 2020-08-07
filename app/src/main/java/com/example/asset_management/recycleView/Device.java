package com.example.asset_management.recycleView;

import android.view.View;
import android.widget.EditText;

import com.example.asset_management.R;

import java.io.IOException;

/**
 * Device
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class Device {
    private String inventoryNumber;
    private String serialnumber;
    private String manufacturer;
    private String model;
    private String status;
    private String deviceCategorie;

    public Device(){
    }

    public Device(String inventoryNumber,String serialNumber, String manufacturer, String model, String deviceCategorie,  String status) {
        this.inventoryNumber = inventoryNumber;
        this.serialnumber = serialNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.deviceCategorie = deviceCategorie;
        this.status = status;
    }

    /**
     * Takes the input from the AddDeviceActivity fields, creates an Device object.
     * @throws IOException
     */
    public Device(View viewById, View viewById1, View viewById2, View viewById3, View viewById4, View viewById5) {

        EditText editInventoryNumber = (EditText) viewById;
        EditText editSerialNumber = (EditText) viewById1;
        EditText editModel = (EditText) viewById2;
        EditText editManufacturer = (EditText) viewById3;
        EditText editCategorie = (EditText) viewById4;
        EditText editStatus = (EditText) viewById5;

        this.inventoryNumber = editInventoryNumber.getText().toString();
        this.serialnumber = editSerialNumber.getText().toString();
        this.model = editModel.getText().toString();
        this.manufacturer = editManufacturer.getText().toString();
        this.deviceCategorie = editCategorie.getText().toString();
        this.status = editStatus.getText().toString();
//
//        Device device = new Device(stringInventoryNumber,stringSerialNumber, stringEditModel, stringManufacturer,
//                stringCategorie, stringStatus);
    }
//
//    public static Device addDevice(EditText editInventoryNumber, EditText editSerialNumber, EditText editManufacturer, EditText editModel
//            , EditText editCategorie, EditText editStatus) {
//
//        String stringInventoryNumber = editInventoryNumber.getText().toString();
//        String stringSerialNumber = editSerialNumber.getText().toString();
//        String stringEditModel = editModel.getText().toString();
//        String stringManufacturer = editManufacturer.getText().toString();
//        String stringCategorie = editCategorie.getText().toString();
//        String stringStatus = editStatus.getText().toString();
//
//        Device device = new Device(stringInventoryNumber,stringSerialNumber, stringEditModel, stringManufacturer,
//                stringCategorie, stringStatus);
//
//        return device;
//    }


    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDeviceCategorie() {
        return deviceCategorie;
    }

    public void setDeviceCategorie(String deviceCategorie) {
        this.deviceCategorie = deviceCategorie;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @Override
    public String toString() {
        return "Device{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", serialNumber='" + serialnumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", deviceCategorie='" + deviceCategorie + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
