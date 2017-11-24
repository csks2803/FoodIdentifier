package com.food.delivery.mvp.view.fragments;

import android.support.annotation.NonNull;

import com.food.delivery.mvp.interfaces.activity.IProdcutAboutView;
import com.food.delivery.mvp.presenter.fragments.ProductAboutPresenter;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductAboutFragment extends MvpFragment<ProductAboutPresenter> implements IProdcutAboutView {
    @NonNull
    @Override
    public ProductAboutPresenter createPresenter() {
        return new ProductAboutPresenter(createFragmentComponent());
    }

    @NonNull
    @Override
    protected String getName() {
        return getClass().getName();
    }
}
