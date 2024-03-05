package com.blablacar4v.models;

import java.util.ArrayList;

public class Travel {

    private String departureHour;
    private String arrivalHour;
    private String departureDate;
    private String departurePlace;
    private String arrivalPlace;
    private String description;
    private String userPublicated;
    private int seats;
    private ArrayList<User> usersInterested;

    public Travel() {
    }



    public Travel(String arrivalHour, String arrivalPlace, String departureDate, String departureHour, String departurePlace, String description,  int seats, String userPublicated) {
        this.arrivalHour = arrivalHour;
        this.arrivalPlace = arrivalPlace;
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.departurePlace = departurePlace;
        this.description = description;
        this.userPublicated = userPublicated;
        this.seats = seats;
    }

    public void addUserInterested(User user){
        usersInterested.add(user);
    }

    public void removeUserInterested(User user){
        usersInterested.remove(user);
    }


    public String getUserPublicated() {
        return userPublicated;
    }

    public void setUserPublicated(String userPublicated) {
        this.userPublicated = userPublicated;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
