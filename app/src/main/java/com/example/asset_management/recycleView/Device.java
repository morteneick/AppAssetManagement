package com.example.asset_management.recycleView;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asset_management.jsonhandler.JsonHandler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Device
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class Device implements Serializable {
    private String inventoryNumber;
    private String serialnumber;
    private String manufacturer;
    private String model;
    private String status;
    private String category;
    private String guarantee;
    private String location;
    private Date lastRepair;


    public Device(){
    }

    public Device(String inventoryNumber, String serialNumber, String manufacturer, String model,
                  String category, String status) {
        this.inventoryNumber = inventoryNumber;
        this.serialnumber = serialNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.category = category;
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
        this.category = editCategorie.getText().toString();
        this.status = editStatus.getText().toString();
//
//        Device device = new Device(stringInventoryNumber,stringSerialNumber, stringEditModel, stringManufacturer,
//                stringCategorie, stringStatus);
    }

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


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getLastRepair() {
        return lastRepair;
    }

    public void setLastRepair(Date lastRepair) {
        this.lastRepair = lastRepair;
    }

    @Override
    public String toString() {
        return "Device{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", serialNumber='" + serialnumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", deviceCategorie='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
