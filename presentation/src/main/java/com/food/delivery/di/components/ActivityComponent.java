package com.food.delivery.di.components;

import com.food.delivery.di.annotation.PerActivity;
import com.food.delivery.di.module.ActivityModule;
import com.food.delivery.di.module.FragmentModule;
import com.food.delivery.mvp.presenter.activity.PackageDetailsPresenter;
import com.food.delivery.mvp.presenter.activity.SplashPresenter;
import com.food.delivery.mvp.presenter.activity.StoreDetailsPresenter;
import com.food.delivery.mvp.presenter.activity.StoreInfoPresenter;
import com.food.delivery.mvp.presenter.activity.TutorialPresenter;
import com.food.delivery.mvp.view.activity.BaseActivity;
import com.food.delivery.mvp.view.activity.SplashActivity;
import com.food.delivery.mvp.view.activity.StoreInfoActivity;
import com.food.delivery.mvp.view.fragments.BaseFragment;

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
    void inject(SplashActivity splashActivity);

    void inject(BaseActivity tBaseActivity);

    void inject(StoreInfoActivity storeInfoActivity);

    ////////PRESENTER INJECTION////////////////////
    void inject(SplashPresenter presenter);

    void inject(TutorialPresenter tutorialPresenter);

    void inject(StoreInfoPresenter storeListPresenter);

    void inject(StoreDetailsPresenter storeDetailsPresenter);

    void inject(PackageDetailsPresenter packageDetailsPresenter);
}
