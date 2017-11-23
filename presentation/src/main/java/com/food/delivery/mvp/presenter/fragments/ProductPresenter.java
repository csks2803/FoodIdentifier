package com.food.delivery.mvp.presenter.fragments;

import android.app.Activity;

import com.food.delivery.di.components.FragmentComponent;
import com.food.delivery.mvp.interfaces.fragment.IProductView;
import com.food.delivery.mvp.presenter.BasePresenter;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductPresenter extends BasePresenter<IProductView> {
    public ProductPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {

    }

    @Override
    protected void onViewDetach() {

    }
}
