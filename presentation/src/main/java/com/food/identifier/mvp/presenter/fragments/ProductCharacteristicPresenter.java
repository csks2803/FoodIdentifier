package com.food.identifier.mvp.presenter.fragments;

import android.app.Activity;

import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.IProductCharacteristicView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductCharacteristicPresenter extends BasePresenter<IProductCharacteristicView> {
    @Inject ProductHolder mProductHolder;

    public ProductCharacteristicPresenter(FragmentComponent fragmentComponent) {

        fragmentComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {
        mView.setupRecycleView();
        mView.setupAdapter(mProductHolder.getProductHolder().getListCharacteristics());
    }

    @Override
    protected void onViewDetach() {

    }
}
