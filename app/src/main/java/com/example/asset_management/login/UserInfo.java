package com.example.asset_management.login;

public class UserInfo {
    private int workerId;
    private String password;
    private String eMail;
    private String surname;
    private String firstname;
    private String role;
    int bookingDevice;
    int editDevice;
    int addDevice;
    int viewDevice;
    int deleteDevice;
    int addUser;
    int deleteUser;
    int editUser;
    int deleteBooking;
    int editBooking;

    public UserInfo(int workerId, String password, String eMail, String surname, String firstname,
                    String role, int bookingDevice, int editDevice, int addDevice, int viewDevice,
                    int deleteDevice, int addUser, int deleteUser, int editUser, int deleteBooking,
                    int editBooking) {
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
        this.addUser = addUser;
        this.deleteUser = deleteUser;
        this.editUser = editUser;
        this.deleteBooking = deleteBooking;
        this.editBooking = editBooking;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getWorkerIdString() {
        return String.valueOf(workerId);
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

    public int getBookingDevice() {
        return bookingDevice;
    }

    public void setBookingDevice(int bookingDevice) {
        this.bookingDevice = bookingDevice;
    }

    public int getEditDevice() {
        return editDevice;
    }

    public void setEditDevice(int editDevice) {
        this.editDevice = editDevice;
    }

    public int getAddDevice() {
        return addDevice;
    }

    public void setAddDevice(int addDevice) {
        this.addDevice = addDevice;
    }

    public int getViewDevice() {
        return viewDevice;
    }

    public void setViewDevice(int viewDevice) {
        this.viewDevice = viewDevice;
    }

    public int getDeleteDevice() {
        return deleteDevice;
    }

    public void setDeleteDevice(int deleteDevice) {
        this.deleteDevice = deleteDevice;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
        this.addUser = addUser;
    }

    public int getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(int deleteUser) {
        this.deleteUser = deleteUser;
    }

    public int getEditUser() {
        return editUser;
    }

    public void setEditUser(int editUser) {
        this.editUser = editUser;
    }

    public int getDeleteBooking() {
        return deleteBooking;
    }

    public void setDeleteBooking(int deleteBooking) {
        this.deleteBooking = deleteBooking;
    }

    public int getEditBooking() {
        return editBooking;
    }

    public void setEditBooking(int editBooking) {
        this.editBooking = editBooking;
    }


}