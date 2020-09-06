package com.example.asset_management.recycleView;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import com.example.asset_management.jsonhandler.JsonHandler;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.valueOf;

/**
 * Device
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 */
public class Device implements Serializable {

    private int inventory_number;
    private String inventoryNumber;
    private String model;
    private String manufacturer;
    @SerializedName("serial_number")
    private String serialnumber;
    @SerializedName("gurantee")
    private Date guarantee;
    private String note;
    @SerializedName("device_status")
    private int deviceStatus;
    private String description;
    private String category;
    private Double longitude;
    private Double latitude;
    @SerializedName("Max(Timesstamp")
    private Timestamp timestamp;
    private Date LAST_TUEV;
    private Date LAST_UVV;
    @SerializedName("project_id")
    private int projectID;
    private String name;
    private String street;
    private String postcode;
    private String city;
    private String status;
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
        inventoryNumber = valueOf(inventory_number);
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
        status = description;
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

    public Date getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Date guarantee) {
        this.guarantee = guarantee;
    }

    public Date getLastRepair() {
        return lastRepair;
    }

    public void setLastRepair(Date lastRepair) {
        this.lastRepair = lastRepair;
    }


    public String getNote() {
        return note;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public String getDescription() {
        return description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Date getLAST_TUEV() {
        return LAST_TUEV;
    }

    public Date getLAST_UVV() {
        return LAST_UVV;
    }

    public int getProjectID() {
        return projectID;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
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
