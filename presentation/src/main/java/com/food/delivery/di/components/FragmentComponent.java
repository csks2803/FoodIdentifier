package com.food.delivery.di.components;

import com.food.delivery.di.annotation.PerFragment;
import com.food.delivery.di.module.FragmentModule;
import com.food.delivery.mvp.presenter.fragments.HistoryPresenter;
import com.food.delivery.mvp.presenter.fragments.ProductAboutPresenter;
import com.food.delivery.mvp.presenter.fragments.ProductCharacteristicPresenter;
import com.food.delivery.mvp.presenter.fragments.ProductFeedbackPresenter;
import com.food.delivery.mvp.presenter.fragments.ProductPresenter;
import com.food.delivery.mvp.view.fragments.BaseFragment;

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

    void inject(HistoryPresenter historyPresenter);

    void inject(ProductPresenter productPresenter);

    void inject(ProductAboutPresenter productAboutPresenter);

    void inject(ProductFeedbackPresenter productFeedbackPresenter);

    void inject(ProductCharacteristicPresenter productCharacteristicPresenter);
}
