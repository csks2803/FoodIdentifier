package com.food.identifier.mvp.presenter.fragments;

import android.app.Activity;

import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.IProductAboutView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductAboutPresenter extends BasePresenter<IProductAboutView> {
    @Inject ProductHolder mProductHolder;

    public ProductAboutPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {
        mView.setAboutText(mProductHolder.getProductHolder().getAbout());
    }

    @Override
    protected void onViewDetach() {

    }
}
