package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 12/1/2017.
 */

public interface IImageView extends IBaseView {
    void hideProgress();

    void loadImage(String url);
    void configureToolbar();

}
