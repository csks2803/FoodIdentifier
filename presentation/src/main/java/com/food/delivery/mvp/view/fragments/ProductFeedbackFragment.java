package com.food.delivery.mvp.view.fragments;

import android.support.annotation.NonNull;

import com.food.delivery.mvp.interfaces.activity.IProductFeedbacView;
import com.food.delivery.mvp.presenter.fragments.ProductFeedbackPresenter;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductFeedbackFragment extends MvpFragment<ProductFeedbackPresenter> implements IProductFeedbacView {
    @NonNull
    @Override
    public ProductFeedbackPresenter createPresenter() {
        return new ProductFeedbackPresenter(createFragmentComponent());
    }

    @NonNull
    @Override
    protected String getName() {
        return getClass().getName();
    }
}
