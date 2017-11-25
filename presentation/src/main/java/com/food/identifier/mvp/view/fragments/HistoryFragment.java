package com.food.identifier.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.fragment.IHistoryView;
import com.food.identifier.mvp.presenter.fragments.HistoryPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by taras on 11/23/2017.
 */

public class HistoryFragment extends MvpFragment<HistoryPresenter> implements IHistoryView {
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
}
