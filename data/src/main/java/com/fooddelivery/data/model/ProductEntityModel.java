package com.fooddelivery.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by taras on 11/11/2017.
 */

public class ProductEntityModel {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("tradeMark")
    private String tradeMark;
    @SerializedName("about")
    private String about;
    @SerializedName("characteristics")
    private List<ProductEntityCharacteristicsModel> listCharacteristics;
    @SerializedName("imageUrls")
    private List<String> imageUrls;
    @SerializedName("feedback")
    private List<FeedbackEntityModel> feedback;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductEntityCharacteristicsModel> getListCharacteristics() {
        return listCharacteristics;
    }

    public void setListCharacteristics(List<ProductEntityCharacteristicsModel> listCharacteristics) {
        this.listCharacteristics = listCharacteristics;
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

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }
}
