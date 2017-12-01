package com.foodidentifier.domain.model;

import java.util.List;

/**
 * Created by taras on 11/11/2017.
 */

public class ProductDomainModel {
    private int id;
    private String title;
    private String tradeMark;
    private String about;
    private List<ProductCharacteristicsDomainModel> listCharacteristics;
    private List<String> imageUrls;
    private List<FeedbackDomainModel> feedback;

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

    public List<ProductCharacteristicsDomainModel> getListCharacteristics() {
        return listCharacteristics;
    }

    public void setListCharacteristics(List<ProductCharacteristicsDomainModel> listCharacteristics) {
        this.listCharacteristics = listCharacteristics;
    }

    public List<FeedbackDomainModel> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<FeedbackDomainModel> feedback) {
        this.feedback = feedback;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }
}
