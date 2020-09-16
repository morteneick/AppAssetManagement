package com.example.asset_management.login;

public class userInfo {
    private String worker_id;
    private String e_mail;
    private String name;
    private String surname;
    private String role;


    public userInfo(String worker_id, String e_mail, String name, String surname, String role){
        this.worker_id = worker_id;
        this.e_mail = e_mail;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

}
