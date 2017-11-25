package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 11/23/2017.
 */

public interface IIdScannerView extends IBaseView {
    void startLoading();
    void finishLoading();
    void replaceToFoodProduct();
}
