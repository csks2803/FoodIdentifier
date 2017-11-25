package com.food.identifier.mvp.model;

import com.fooddelivery.domain.model.FeedbackDomainModel;
import com.fooddelivery.domain.model.ProductCharacteristicsDomainModel;

import java.util.List;

/**
 * Created by taras on 11/25/2017.
 */

public class ProductPresentationModel {
    private int id;
    private String title;
    private String tradeMark;
    private String about;
    private List<ProductCharacteristicsPresentationModel> listCharacteristics;
    private List<String> imageUrls;
    private List<FeedbackPresentationModel> feedback;

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

    public List<ProductCharacteristicsPresentationModel> getListCharacteristics() {
        return listCharacteristics;
    }

    public void setListCharacteristics(List<ProductCharacteristicsPresentationModel> listCharacteristics) {
        this.listCharacteristics = listCharacteristics;
    }

    public List<FeedbackPresentationModel> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<FeedbackPresentationModel> feedback) {
        this.feedback = feedback;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }
}
