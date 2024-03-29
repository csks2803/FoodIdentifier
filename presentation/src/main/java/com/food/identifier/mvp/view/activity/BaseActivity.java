package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.food.identifier.application.FoodIdentifierApplication;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.di.module.ActivityModule;
import com.food.identifier.mvp.interfaces.activity.IBaseView;
import com.food.identifier.mvp.presenter.PresenterContainer;
import com.food.identifier.navigator.Navigator;

import javax.inject.Inject;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:41 AM.
 * Company : FoodDelivery
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @Inject PresenterContainer mPresenterContainer;
    @Inject Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent();
        onPresenterInit();
        onInit(savedInstanceState);
        setActivityContent();
    }

    protected abstract void setActivityContent();

    protected abstract void onPresenterInit();

    protected abstract void onInit(@Nullable Bundle bundle);

    protected void setupComponent(){
        createActivityComponent().inject(this);
    }

    public ActivityComponent createActivityComponent() {
        return ((FoodIdentifierApplication) getApplication()).getFoodDeliveryAppComponent().plus(new ActivityModule(this));
    }

}
