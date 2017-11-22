package com.food.delivery.di.components;

import com.food.delivery.di.annotation.PerFragment;
import com.food.delivery.di.module.FragmentModule;
import com.food.delivery.mvp.presenter.fragments.StoreListPresenter;
import com.food.delivery.mvp.presenter.fragments.StoreMapPresenter;
import com.food.delivery.mvp.presenter.fragments.TutorialScreenPresenter;
import com.food.delivery.mvp.view.fragments.BaseFragment;
import com.food.delivery.mvp.view.fragments.TutorialScreenFragment;

import dagger.Subcomponent;

/**
 * Created by Taras Matolinets
 * Data: 9/19/2017.
 * Time: 8:42 AM
 * Company: FoodDelivery
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(BaseFragment baseFragment);

    void inject(StoreMapPresenter storeMapPresenter);

    void inject(TutorialScreenPresenter tutorialScreenPresenter);

    void inject(StoreListPresenter storeListPresenter);
}
