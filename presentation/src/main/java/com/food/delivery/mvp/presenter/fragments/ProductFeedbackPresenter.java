package com.food.delivery.mvp.presenter.fragments;

import android.app.Activity;

import com.food.delivery.di.components.FragmentComponent;
import com.food.delivery.mvp.interfaces.activity.IProductFeedbacView;
import com.food.delivery.mvp.presenter.BasePresenter;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductFeedbackPresenter extends BasePresenter<IProductFeedbacView> {
    public ProductFeedbackPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {

    }

    @Override
    protected void onViewDetach() {

    }
}
