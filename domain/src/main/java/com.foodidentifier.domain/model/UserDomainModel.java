package com.foodidentifier.domain.model;

/**
 * Created by taras on 12/10/2017.
 */

public class UserDomainModel {
    private int id;
    private String firstName;
    private String lastName;
    private int type;
    private String imageUrl;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getType() {
        return type;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
