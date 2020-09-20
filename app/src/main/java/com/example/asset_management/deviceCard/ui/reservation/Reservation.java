package com.example.asset_management.deviceCard.ui.reservation;

import java.util.Calendar;
import java.util.Date;
/**
 * Reservation
 * <p>
 *     Version 1.0
 * </p>
 * 30.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class Reservation {
    Date loanDay;
    Date loanEnd;
    String name;
    String surname;
    int projectId;
    String buildingSite;
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

    public Date getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(Date loanDay) {
        this.loanDay = loanDay;
    }

    public Date getLoanEnd() {
        return loanEnd;
    }

    public void setLoanEnd(Date loanEnd) {
        this.loanEnd = loanEnd;
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

    public String getBuildingSite() {
        return buildingSite;
    }

    public void setBuildingSite(String buildingSite) {
        this.buildingSite = buildingSite;
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
