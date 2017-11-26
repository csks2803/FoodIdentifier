package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.IProductAboutView;
import com.food.identifier.mvp.presenter.fragments.ProductAboutPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductAboutFragment extends MvpFragment<ProductAboutPresenter> implements IProductAboutView {

    @BindView(R.id.tv_about_product) TextView mTvAboutProduct;

    private Unbinder mUnBinder;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        mUnBinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @Override
    public void setAboutText(String about) {
        mTvAboutProduct.setText(about);
    }
}
