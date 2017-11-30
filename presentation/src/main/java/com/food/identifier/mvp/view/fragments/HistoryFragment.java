package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.IHistoryView;
import com.food.identifier.mvp.model.ProductPresentationModel;
import com.food.identifier.mvp.presenter.fragments.HistoryPresenter;
import com.food.identifier.mvp.view.activity.ProductActivity;
import com.food.identifier.mvp.view.adapters.HistoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by taras on 11/23/2017.
 */

public class HistoryFragment extends MvpFragment<HistoryPresenter> implements IHistoryView {
    @BindView(R.id.rv_history) RecyclerView mRvHistory;
    @BindView(R.id.pr_history_loading) ProgressBar mProgressHistory;
    @BindView(R.id.tv_no_history) TextView mTvNoHistory;

    private Unbinder mUnBinder;

    @NonNull
    @Override
    public HistoryPresenter createPresenter() {
        return new HistoryPresenter(createFragmentComponent());
    }

    @NonNull
    @Override
    protected String getName() {
        return getClass().getName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    @Override
    public void configureRecycleView() {
        mRvHistory.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvHistory.setLayoutManager(layoutManager);
    }

    @Override
    public void setAdapter(List<ProductPresentationModel> productPresentationList) {
        HistoryAdapter adapter = new HistoryAdapter(getActivity(), productPresentationList);
        mRvHistory.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        mProgressHistory.setVisibility(GONE);
    }

    @Override
    public void showProgress() {
        mProgressHistory.setVisibility(VISIBLE);
    }

    @Override
    public void hideNoHistory() {
        mTvNoHistory.setVisibility(GONE);
    }

    @Override
    public void showNoHistory() {
        mTvNoHistory.setVisibility(VISIBLE);
    }

    @Override
    public void showNoConnectionToast() {
        Toast.makeText(getActivity(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replaceToProductScreen() {
        getActivity().finish();
        startActivity(getActivity().getIntent());
        //   mNavigator.replaceActivityAnimation(getActivity(), ProductActivity.class, android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
