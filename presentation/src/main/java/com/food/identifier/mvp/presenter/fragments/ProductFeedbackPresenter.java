package com.food.identifier.mvp.presenter.fragments;

import android.app.Activity;

import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.IProductFeedbackView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductFeedbackPresenter extends BasePresenter<IProductFeedbackView> {
    @Inject ProductHolder mProductHolder;

    public ProductFeedbackPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {
        mView.setupRecycleView();
        mView.setupAdapter(mProductHolder.getProductHolder().getFeedback());
    }

    @Override
    protected void onViewDetach() {

    }

}
