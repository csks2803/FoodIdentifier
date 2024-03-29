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
import com.food.identifier.mvp.interfaces.fragment.IProductFeedbackView;
import com.food.identifier.mvp.model.FeedbackPresentationModel;
import com.food.identifier.mvp.presenter.fragments.ProductFeedbackPresenter;
import com.food.identifier.mvp.view.adapters.FeedbackAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/24/2017.
 */

public class ProductFeedbackFragment extends MvpFragment<ProductFeedbackPresenter> implements IProductFeedbackView {
    @BindView(R.id.rv_feedback) RecyclerView mRvFeedback;

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
        mRvFeedback.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvFeedback.setLayoutManager(layoutManager);
    }

    @Override
    public void setupAdapter(List<FeedbackPresentationModel> listFeedback) {
        FeedbackAdapter adapter = new FeedbackAdapter(getActivity(), listFeedback);
        mRvFeedback.setAdapter(adapter);
    }
}
