package com.food.delivery.mvp.presenter.activity;

import android.app.Activity;

import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.mvp.interfaces.activity.IPackageView;
import com.food.delivery.mvp.presenter.BasePresenter;

/**
 * Created by taras on 11/19/2017.
 */

public class PackageDetailsPresenter extends BasePresenter<IPackageView> {
    public PackageDetailsPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {

    }

    @Override
    protected void onViewDetach() {

    }
}
