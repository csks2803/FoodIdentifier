package com.food.delivery.mvp.view.fragments;

import android.support.annotation.NonNull;

import com.food.delivery.mvp.interfaces.activity.IProductChareacteristicView;
import com.food.delivery.mvp.presenter.fragments.ProductCharacteristicPresenter;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductCharacteristicFragment extends MvpFragment<ProductCharacteristicPresenter> implements IProductChareacteristicView {
    @NonNull
    @Override
    public ProductCharacteristicPresenter createPresenter() {
        return new ProductCharacteristicPresenter(createFragmentComponent());
    }

    @NonNull
    @Override
    protected String getName() {
        return getClass().getName();
    }
}
