package com.blablacar4v.models;

public class User {
    private String email;
    private String password;
    private String direction;
    private String phone;
    private String name;
    private String photo;

    public User() {
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String direction, String phone, String name) {
        this.email = email;
        this.password = password;
        this.direction = direction;
        this.phone = phone;
        this.name = name;
    }
}
