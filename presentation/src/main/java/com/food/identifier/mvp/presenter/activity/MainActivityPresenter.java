package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IMainView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:25 PM
 * Company: FoodIdentifier
 */

public class MainActivityPresenter extends BasePresenter<IMainView> {
    @Inject ProductHolder mProductHolder;

    public MainActivityPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.initToolBar();
        mView.initActionBar();
        mView.setupTabLayout();
        mView.setAdapter();
        mView.setNavigationListener();
        mView.setProductImageAdapter(mProductHolder.getProductHolder().getImageUrls());
    }

    @Override
    protected void onViewDetach() {

    }

    public void changeCollapseColor(Context context, int position) {
        String url = mProductHolder.getProductHolder().getImageUrls().get(position);
        Glide.with(context).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                mView.setCollapseToolBarColor(resource);
            }
        });
    }
}
