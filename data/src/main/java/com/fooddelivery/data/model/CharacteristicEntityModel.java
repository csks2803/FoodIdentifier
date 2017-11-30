package com.fooddelivery.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taras on 11/25/2017.
 */

public class CharacteristicEntityModel {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
