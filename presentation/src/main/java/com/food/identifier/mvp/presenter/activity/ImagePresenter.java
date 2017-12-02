package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.content.Intent;

import com.food.identifier.application.FoodIdentifierApplication;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IImageView;
import com.food.identifier.mvp.presenter.BasePresenter;


/**
 * Created by taras on 12/1/2017.
 */

public class ImagePresenter extends BasePresenter<IImageView> {
    public static final String IMAGE_URL = FoodIdentifierApplication.TAG + "image.url";

    public ImagePresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {
        mView.configureToolbar();
        Intent intent = context.getIntent();
        String url = intent.getStringExtra(IMAGE_URL);

        mView.loadImage(url);
    }

    @Override
    protected void onViewDetach() {

    }
}
