package com.food.delivery.mvp.presenter.fragments;

import android.app.Activity;

import com.food.delivery.di.components.FragmentComponent;
import com.food.delivery.mvp.interfaces.activity.IProdcutAboutView;
import com.food.delivery.mvp.presenter.BasePresenter;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductAboutPresenter extends BasePresenter<IProdcutAboutView> {
    public ProductAboutPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {

    }

    @Override
    protected void onViewDetach() {

    }
}