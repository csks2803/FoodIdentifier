package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IProductFeedbacView;
import com.food.identifier.mvp.presenter.fragments.ProductFeedbackPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductFeedbackFragment extends MvpFragment<ProductFeedbackPresenter> implements IProductFeedbacView {

    private Unbinder mUnBinder;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        mUnBinder =   ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
