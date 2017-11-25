package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IProductChareacteristicView;
import com.food.identifier.mvp.presenter.fragments.ProductCharacteristicPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductDescriptionFragment extends MvpFragment<ProductCharacteristicPresenter> implements IProductChareacteristicView {
    private Unbinder mUnBinder;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
