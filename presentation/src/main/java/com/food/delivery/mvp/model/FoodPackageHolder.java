package com.food.delivery.mvp.model;

import java.util.List;

/**
 * Created by taras on 11/19/2017.
 */

public class FoodPackageHolder {
    private double latitude;
    private double longitude;
    private List<FoodPackagePresenterModel> listFoodPackagePresenter;

    public List<FoodPackagePresenterModel> getListFoodPackagePresenter() {
        return listFoodPackagePresenter;
    }

    public void setListFoodPackagePresenter(List<FoodPackagePresenterModel> listFoodPackagePresenter) {
        this.listFoodPackagePresenter = listFoodPackagePresenter;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
