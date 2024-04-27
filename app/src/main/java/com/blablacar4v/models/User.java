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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
