package com.food.delivery.di.components;

import com.food.delivery.di.annotation.PerActivity;
import com.food.delivery.di.module.ActivityModule;
import com.food.delivery.di.module.FragmentModule;
import com.food.delivery.mvp.presenter.activity.StoreInfoPresenter;
import com.food.delivery.mvp.view.activity.BaseActivity;
import com.food.delivery.mvp.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:38 AM.
 * Company : FoodDelivery
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    FragmentComponent plus(FragmentModule fragmentModule);

    ////////ACTIVITY INJECTION////////////////////

    void inject(BaseActivity tBaseActivity);

    void inject(MainActivity mainActivity);

    ////////PRESENTER INJECTION////////////////////

    void inject(StoreInfoPresenter storeListPresenter);

}
