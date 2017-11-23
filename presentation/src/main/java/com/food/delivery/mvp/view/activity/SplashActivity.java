package com.food.delivery.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.food.delivery.R;
import com.food.delivery.mvp.interfaces.activity.ISplashView;
import com.food.delivery.mvp.presenter.activity.SplashPresenter;

/**
 * Created by taras on 11/23/2017.
 */

public class SplashActivity extends MvpActivity<SplashPresenter> implements ISplashView {
    @Override
    protected void onInit(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash_screen);
    }

    @NonNull
    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter(createActivityComponent());
    }

    @Override
    public void replace() {
        mNavigator.replaceActivity(this, MainActivity.class);
    }
}
