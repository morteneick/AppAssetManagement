package com.example.asset_management.deviceCard.ui.reservation;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

public class Reservation {
    int inventory_number;
    Date loan_day;
    Date loan_end;
    String name;
    String surname;
    String Baustelle;

    String inventoryNumber;
    Calendar start;
    Calendar end;

    public Reservation(String inventoryNumber, Calendar start, Calendar end) {
        this.inventoryNumber = inventoryNumber;
        this.start = start;
        this.end = end;
    }

    public Reservation(){

    }

    public int getInventory_number() {
        return inventory_number;
    }

    public void setInventory_number(int inventory_number) {
        this.inventory_number = inventory_number;
    }

    public Date getLoan_day() {
        return loan_day;
    }

    public void setLoan_day(Date loan_day) {
        this.loan_day = loan_day;
    }

    public Date getLoan_end() {
        return loan_end;
    }

    public void setLoan_end(Date loan_end) {
        this.loan_end = loan_end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBaustelle() {
        return Baustelle;
    }

    public void setBaustelle(String baustelle) {
        Baustelle = baustelle;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}
