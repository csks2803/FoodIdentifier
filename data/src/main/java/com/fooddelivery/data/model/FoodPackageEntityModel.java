package com.fooddelivery.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taras on 11/11/2017.
 */

public class FoodPackageEntityModel {
    @SerializedName("storeId")
    private int storeId;
    @SerializedName("storeName")
    private String storeName;
    @SerializedName("packageInfo")
    private String packageInfo;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("packagePrice")
    private double packagePrice;
    @SerializedName("packageName")
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

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
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
