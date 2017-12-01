package com.fooddelivery.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by taras on 11/11/2017.
 */

public class ProductEntityModel {
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("tradeMark")
    private String tradeMark;
    @SerializedName("about")
    public String about;
    @SerializedName("imageUrls")
    private List<String> imageUrls;
    @SerializedName("feedback")
    public List<FeedbackEntityModel> feedback;
    @SerializedName("characteristics")
    public List<CharacteristicEntityModel> characteristics;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CharacteristicEntityModel> getСharacteristic() {
        return characteristics;
    }

    public List<FeedbackEntityModel> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<FeedbackEntityModel> feedback) {
        this.feedback = feedback;
    }

    public String getTradeMark() {
        return tradeMark;
    }

}
