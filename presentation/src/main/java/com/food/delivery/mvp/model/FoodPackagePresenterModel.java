package com.food.delivery.mvp.model;

/**
 * Created by taras on 11/11/2017.
 */

public class FoodPackagePresenterModel {
    private int storeId;
    private String storeName;
    private String packageInfo;
    private double longitude;
    private double latitude;
    private double packagePrice;
    private String packageName;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(String storeInfo) {
        this.packageInfo = storeInfo;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
