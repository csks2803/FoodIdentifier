package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.IProductCharacteristicView;
import com.food.identifier.mvp.model.ProductCharacteristicsPresentationModel;
import com.food.identifier.mvp.presenter.fragments.ProductCharacteristicPresenter;
import com.food.identifier.mvp.view.adapters.CharacteristicAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductCharacteristicFragment extends MvpFragment<ProductCharacteristicPresenter> implements IProductCharacteristicView {
    @BindView(R.id.rv_characteristics) RecyclerView mRvCharacteristics;
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
        View view = inflater.inflate(R.layout.fragment_characteristic, container, false);

        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @Override
    public void setupRecycleView() {
        mRvCharacteristics.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvCharacteristics.setLayoutManager(layoutManager);
    }

    @Override
    public void setupAdapter(List<ProductCharacteristicsPresentationModel> listCharacteristics) {
        CharacteristicAdapter adapter = new CharacteristicAdapter(getActivity(), listCharacteristics);
        mRvCharacteristics.setAdapter(adapter);
    }
}
