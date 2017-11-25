package com.food.identifier.mvp.presenter.fragments;

import android.app.Activity;

import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.IProductView;
import com.food.identifier.mvp.presenter.BasePresenter;

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
