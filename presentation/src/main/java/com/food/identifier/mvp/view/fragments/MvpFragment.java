package com.food.identifier.mvp.view.fragments;

import android.support.annotation.NonNull;
import android.view.View;

import com.food.identifier.mvp.presenter.BasePresenter;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 1:10 PM
 * Company: FoodDelivery
 */
public abstract class MvpFragment<T extends BasePresenter> extends BaseFragment {

    protected T mPresenter;

    @Override
    protected void onPresenterInit() {
        if (mPresenterContainer != null) {
            //noinspection unchecked
            mPresenter = (T) mPresenterContainer.getPresenter(getPresenterKey());
        }
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        //noinspection unchecked
        mPresenter.attachView(this);
    }

    @NonNull
    public abstract T createPresenter();

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onViewShown();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onViewHidden();
        if (mPresenterContainer != null) {
            mPresenterContainer.putPresenter(getPresenterKey(), mPresenter);
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        mPresenter = null;
        super.onDestroyView();
    }

    @Override
    public void runOnMainThread(@NonNull Runnable runnable) {
        View view = getView();
        if (view != null) {
            view.post(runnable);
        }
    }

    private String getPresenterKey() {
        String tag = getTag();
        return tag == null ? getName() : getName() + "- " + tag;
    }

    @NonNull
    protected abstract String getName();
}

