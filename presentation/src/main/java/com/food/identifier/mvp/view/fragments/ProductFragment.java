package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.IProductView;
import com.food.identifier.mvp.presenter.fragments.ProductPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/23/2017.
 */

public class ProductFragment extends MvpFragment<ProductPresenter> implements IProductView {
    private Unbinder mUnBinder;

    @NonNull
    @Override
    public ProductPresenter createPresenter() {
        return new ProductPresenter(createFragmentComponent());
    }

    @NonNull
    @Override
    protected String getName() {
        return getClass().getName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        mUnBinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}

