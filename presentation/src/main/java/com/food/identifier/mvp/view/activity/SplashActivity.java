package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.ISplashView;
import com.food.identifier.mvp.presenter.activity.SplashPresenter;

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
        mNavigator.replaceActivity(this, IdScannerActivity.class);
    }
}
