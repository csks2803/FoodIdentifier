package com.food.identifier.di.components;

import com.food.identifier.di.annotation.PerFragment;
import com.food.identifier.di.module.FragmentModule;
import com.food.identifier.mvp.presenter.fragments.HistoryPresenter;
import com.food.identifier.mvp.presenter.fragments.ProductAboutPresenter;
import com.food.identifier.mvp.presenter.fragments.ProductCharacteristicPresenter;
import com.food.identifier.mvp.presenter.fragments.ProductFeedbackPresenter;
import com.food.identifier.mvp.presenter.fragments.ProductPresenter;
import com.food.identifier.mvp.presenter.fragments.TutorialScreenPresenter;
import com.food.identifier.mvp.view.fragments.BaseFragment;

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

    void inject(TutorialScreenPresenter tutorialScreenPresenter);
}
