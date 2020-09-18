package com.example.asset_management.login;

public class UserInfo {
    private int workerId;
    private String password;
    private String eMail;
    private String surname;
    private String firstname;
    private String role;
    boolean bookingDevice;
    boolean editDevice;
    boolean addDevice;
    boolean viewDevice;
    boolean deleteDevice;
    boolean editUser;
    boolean deleteBooking;
    boolean editBooking;

    public UserInfo(int workerId, String password, String eMail, String surname, String firstname,
                    String role, boolean bookingDevice, boolean editDevice, boolean addDevice,
                    boolean viewDevice, boolean deleteDevice, boolean editUser, boolean deleteBooking,
                    boolean editBooking) {
        this.workerId = workerId;
        this.password = password;
        this.eMail = eMail;
        this.surname = surname;
        this.firstname = firstname;
        this.role = role;
        this.bookingDevice = bookingDevice;
        this.editDevice = editDevice;
        this.addDevice = addDevice;
        this.viewDevice = viewDevice;
        this.deleteDevice = deleteDevice;
        this.editUser = editUser;
        this.deleteBooking = deleteBooking;
        this.editBooking = editBooking;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBookingDevice() {
        return bookingDevice;
    }

    public void setBookingDevice(boolean bookingDevice) {
        this.bookingDevice = bookingDevice;
    }

    public boolean isEditDevice() {
        return editDevice;
    }

    public void setEditDevice(boolean editDevice) {
        this.editDevice = editDevice;
    }

    public boolean isAddDevice() {
        return addDevice;
    }

    public void setAddDevice(boolean addDevice) {
        this.addDevice = addDevice;
    }

    public boolean isViewDevice() {
        return viewDevice;
    }

    public void setViewDevice(boolean viewDevice) {
        this.viewDevice = viewDevice;
    }

    public boolean isDeleteDevice() {
        return deleteDevice;
    }

    public void setDeleteDevice(boolean deleteDevice) {
        this.deleteDevice = deleteDevice;
    }

    public boolean isEditUser() {
        return editUser;
    }

    public void setEditUser(boolean editUser) {
        this.editUser = editUser;
    }

    public boolean isDeleteBooking() {
        return deleteBooking;
    }

    public void setDeleteBooking(boolean deleteBooking) {
        this.deleteBooking = deleteBooking;
    }

    public boolean isEditBooking() {
        return editBooking;
    }

    public void setEditBooking(boolean editBooking) {
        this.editBooking = editBooking;
    }
}
