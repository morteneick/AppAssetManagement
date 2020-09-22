package com.example.asset_management.recycleViewDeviceList;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
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
 * AUTHOR: Dominik Dziersan
 */
public class Device implements Serializable {

    @Nullable
    private String inventoryNumber;
    @Nullable
    private String model;
    @Nullable
    private String manufacturer;
    @Nullable
    private String serialNumber;
    @Nullable
    private Date guarantee;
    @Nullable
    private String note;
    @Nullable
    private int deviceStatus;
    @Nullable
    @SerializedName("statusDescription")
    private String description;
    @Nullable
    private String category;
    @Nullable
    private String beaconMajor;
    @Nullable
    private String beaconMinor;
    @Nullable
    private Double longitude;
    @Nullable
    private Double latitude;
    @Nullable
    private Date lastLocationUpdate;
    @Nullable
    private Date lastTuev;
    @Nullable
    private Date lastUvv;
    @Nullable
    private Date lastRepair;
    @Nullable
    private String repairNote;
    @Nullable
    private int projectId;
    @Nullable
    @SerializedName("buildingSite")
    private String name;
    @Nullable
    private String street;
    @Nullable
    private String postcode;
    @Nullable
    private String city;
    @Nullable
    private Date lastChange;

    @Nullable
    private String status;


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

    public Device(@Nullable String inventoryNumber, @Nullable String model,
                  @Nullable String manufacturer, @Nullable String serialNumber,
                  @Nullable Date guarantee, @Nullable String note, int deviceStatus,
                  @Nullable String description, @Nullable String category,
                  @Nullable String beaconMajor, @Nullable String beaconMinor,
                  @Nullable Double longitude, @Nullable Double latitude,
                  @Nullable Date lastLocationUpdate, @Nullable Date lastTuev,
                  @Nullable Date lastUvv, @Nullable Date lastRepair,
                  @Nullable String repairNote, int projectId, @Nullable String name,
                  @Nullable String street, @Nullable String postcode,
                  @Nullable String city, @Nullable Date lastChange, @Nullable String status) {
        this.inventoryNumber = inventoryNumber;
        this.model = model;
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
        this.guarantee = guarantee;
        this.note = note;
        this.deviceStatus = deviceStatus;
        this.description = description;
        this.category = category;
        this.beaconMajor = beaconMajor;
        this.beaconMinor = beaconMinor;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lastLocationUpdate = lastLocationUpdate;
        this.lastTuev = lastTuev;
        this.lastUvv = lastUvv;
        this.lastRepair = lastRepair;
        this.repairNote = repairNote;
        this.projectId = projectId;
        this.name = name;
        this.street = street;
        this.postcode = postcode;
        this.city = city;
        this.lastChange = lastChange;
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
        this.serialNumber = editSerialNumber.getText().toString();
        this.model = editModel.getText().toString();
        this.manufacturer = editManufacturer.getText().toString();
        this.category = editCategorie.getText().toString();
        this.status = editStatus.getText().toString();
//
//        Device device = new Device(stringInventoryNumber,stringSerialNumber, stringEditModel, stringManufacturer,
//                stringCategorie, stringStatus);
    }

    @Nullable
    public String getBeaconMajor() {
        return beaconMajor;
    }

    public void setBeaconMajor(@Nullable String beaconMajor) {
        this.beaconMajor = beaconMajor;
    }

    @Nullable
    public String getBeaconMinor() {
        return beaconMinor;
    }

    public void setBeaconMinor(@Nullable String beaconMinor) {
        this.beaconMinor = beaconMinor;
    }

    public void setLastLocationUpdate(@Nullable Date lastLocationUpdate) {
        this.lastLocationUpdate = lastLocationUpdate;
    }

    @Nullable
    public String getRepairNote() {
        return repairNote;
    }

    public void setRepairNote(@Nullable String repairNote) {
        this.repairNote = repairNote;
    }
    @Nullable
    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }
    @Nullable
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    @Nullable
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Nullable
    public String getStatus() {
        status = description;
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Nullable
    public int getInventoryNumberInt(){
        return Integer.parseInt(inventoryNumber);
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Nullable
    public String getSerialnumber() {
        return serialNumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialNumber = serialnumber;
    }
    @Nullable
    public Date getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Date guarantee) {
        this.guarantee = guarantee;
    }
    @Nullable
    public Date getLastRepair() {
        return lastRepair;
    }

    public void setLastRepair(Date lastRepair) {
        this.lastRepair = lastRepair;
    }

    @Nullable
    public String getNote() {
        return note;
    }
    @Nullable
    public int getDeviceStatus() {
        return deviceStatus;
    }
    @Nullable
    public String getDescription() {
        return description;
    }
    @Nullable
    public Double getLongitude() {
        return longitude;
    }
    @Nullable
    public Double getLatitude() {
        return latitude;
    }
    @Nullable
    public Date getLastLocationUpdate() {
        return lastLocationUpdate;
    }
    @Nullable
    public Date getLastTuev() {
        return lastTuev;
    }
    @Nullable
    public Date getLastUvv() {
        return lastUvv;
    }
    @Nullable
    public int getProjectId() {
        return projectId;
    }
    @Nullable
    public String getName() {
        return name;
    }
    @Nullable
    public String getStreet() {
        return street;
    }
    @Nullable
    public String getPostcode() {
        return postcode;
    }
    @Nullable
    public String getCity() {
        return city;
    }
    @Nullable
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
