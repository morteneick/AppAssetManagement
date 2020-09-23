package com.example.asset_management.login;

public class userRights {
    private Boolean booking_device;
    private Boolean edit_device;
    private Boolean add_device;
    private Boolean view_device;
    private Boolean delete_user;
    private Boolean add_user;
    private Boolean delete_device;
    private Boolean edit_user;
    private Boolean delete_booking;
    private Boolean edit_booking;


    public userRights(Boolean booking_device,Boolean edit_device,Boolean add_device,
                      Boolean view_device, Boolean delete_user, Boolean add_user,
                      Boolean delete_device, Boolean edit_user, Boolean delete_booking,
                      Boolean edit_booking) {

        this.add_device = add_device;
        this.add_user = add_user;
        this.booking_device = booking_device;
        this.delete_booking = delete_booking;
        this.delete_device = delete_device;
        this.delete_user = delete_user;
        this.edit_booking = edit_booking;
        this.edit_device = edit_device;
        this.edit_user = edit_user;
        this.view_device = view_device;
    }

}
