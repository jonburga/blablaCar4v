package com.blablacar4v.models;

import java.util.ArrayList;
import java.util.Date;

public class Travel {

    private Date arrivalDate;
    private Date departureDate;
    private String departurePlace;
    private String arrivalPlace;
    private String description;
    private User userPublicated;
    private ArrayList<User> usersInterested;

    public Travel() {
    }

    public Travel(Date arrivalDate, Date departureDate, String departurePlace, String arrivalPlace, String description, User userPublicated) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.description = description;
        this.userPublicated = userPublicated;
    }

    public void addUserInterested(User user){
        usersInterested.add(user);
    }

    public void removeUserInterested(User user){
        usersInterested.remove(user);
    }


}
