package com.foodidentifier.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taras on 12/10/2017.
 */

public class UserEntityModel {

    @SerializedName("id")
    private int id;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("type")
    private int type;
    @SerializedName("imageUrl")
    private String imageUrl;

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
