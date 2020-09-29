package com.example.asset_management.recycleViewDeviceList;

import android.view.View;
import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
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

    private String inventoryNumber;
    private String model;
    private String manufacturer;
    private String serialNumber;
    private Date guarantee;
    private String note;
    private int deviceStatus;

    @SerializedName("statusDescription")
    private String description;
    private String category;
    private Double longitude;
    private Double latitude;
    private Timestamp lastLocationUpdate;
    private Date lastTuev;
    private Date lastUvv;
    private int projectId;
    private String name;
    private String street;
    private String postcode;
    private String city;
    private String status;
    private Date lastRepair;
    private Date lastChange;
    private String repairNote;
    private String beaconMajor;
    private String beaconMinor;

    public Device(){
    }

    public Device(String inventoryNumber, String serialNumber, String manufacturer, String model,
                  String category, String status) {
        this.inventoryNumber = inventoryNumber;
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.category = category;
        this.status = status;
    }

    public Device(String inventoryNumber, String model, String manufacturer, String serialNumber,
                  Date guarantee, String note, int deviceStatus, String description, String category,
                  Double longitude, Double latitude, Timestamp lastLocationUpdate, Date lastTuev,
                  Date lastUvv, int projectId, String name, String street, String postcode,
                  String city, String status, Date lastRepair) {
        this.inventoryNumber = inventoryNumber;
        this.model = model;
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
        this.guarantee = guarantee;
        this.note = note;
        this.deviceStatus = deviceStatus;
        this.description = description;
        this.category = category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lastLocationUpdate = lastLocationUpdate;
        this.lastTuev = lastTuev;
        this.lastUvv = lastUvv;
        this.projectId = projectId;
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.status = status;
        this.lastRepair = lastRepair;
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
        this.serialNumber = editSerialNumber.getText().toString();
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

    public String getBeaconMajor() {
        return beaconMajor;
    }

    public void setBeaconMajor(String beaconMajor) {
        this.beaconMajor = beaconMajor;
    }

    public String getBeaconMinor() {
        return beaconMinor;
    }

    public void setBeaconMinor(String beaconMinor) {
        this.beaconMinor = beaconMinor;
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

    public String getRepairNote() {
        return repairNote;
    }

    public void setRepairNote(String repairNote) {
        this.repairNote = repairNote;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInventoryNumberInt(){
        return Integer.parseInt(inventoryNumber);
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSerialnumber() {
        return serialNumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialNumber = serialnumber;
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

    public Timestamp getLastLocationUpdate() {
        return lastLocationUpdate;
    }

    public Date getLastTuev() {
        return lastTuev;
    }

    public Date getLastUvv() {
        return lastUvv;
    }

    public int getProjectId() {
        return projectId;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLastLocationUpdate(Timestamp lastLocationUpdate) {
        this.lastLocationUpdate = lastLocationUpdate;
    }

    public void setLastTuev(Date lastTuev) {
        this.lastTuev = lastTuev;
    }

    public void setLastUvv(Date lastUvv) {
        this.lastUvv = lastUvv;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    @Override
    public String toString() {
        return "Device{" +
                "inventoryNumber='" + inventoryNumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", deviceCategorie='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
