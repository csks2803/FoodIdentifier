package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;

import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IMainView;
import com.food.identifier.mvp.presenter.BasePresenter;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:25 PM
 * Company: FoodIdentifier
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
