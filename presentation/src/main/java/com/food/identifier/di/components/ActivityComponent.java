package com.food.identifier.di.components;

import com.food.identifier.di.annotation.PerActivity;
import com.food.identifier.di.module.ActivityModule;
import com.food.identifier.di.module.FragmentModule;
import com.food.identifier.mvp.presenter.activity.ImagePresenter;
import com.food.identifier.mvp.presenter.activity.SplashPresenter;
import com.food.identifier.mvp.presenter.activity.TutorialPresenter;
import com.food.identifier.mvp.presenter.fragments.HistoryPresenter;
import com.food.identifier.mvp.presenter.activity.IdScannerPresenter;
import com.food.identifier.mvp.presenter.activity.ProductPresenter;
import com.food.identifier.mvp.view.activity.BaseActivity;
import com.food.identifier.mvp.view.activity.ProductActivity;

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

    void inject(ProductActivity mainActivity);

    ////////PRESENTER INJECTION////////////////////

    void inject(ProductPresenter storeListPresenter);

    void inject(IdScannerPresenter idScannerPresenter);

    void inject(HistoryPresenter historyPresenter);

    void inject(SplashPresenter splashPresenter);

    void inject(TutorialPresenter tutorialPresenter);

    void inject(ImagePresenter imagePresenter);
}
