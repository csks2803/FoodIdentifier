package com.food.delivery.mvp.presenter.activity;

import android.app.Activity;

import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.mvp.interfaces.activity.IMainView;
import com.food.delivery.mvp.presenter.BasePresenter;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:25 PM
 * Company: FoodDelivery
 */

public class MainActivityPresenter extends BasePresenter<IMainView> {

    public MainActivityPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.initToolBar();
        mView.initActionBar();
        mView.setNavigationListener();
    }

    @Override
    protected void onViewDetach() {

    }
}
