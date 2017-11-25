package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.food.identifier.mvp.presenter.BasePresenter;

/**
 * Created by Taras Matolinets
 * Data: 9/17/2017.
 * Time: 6:24 PM
 * Company: FoodDelivery
 */
public abstract class MvpActivity<T extends BasePresenter> extends BaseActivity {

    protected T mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPresenterInit() {
        if (mPresenterContainer != null) {
            //noinspection unchecked
            mPresenter = (T) mPresenterContainer.getPresenter(null);
        }
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
    }

    @Override
    protected void setActivityContent() {
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
            mPresenterContainer.putPresenter(null, mPresenter);
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void runOnMainThread(@NonNull Runnable runnable) {
        runOnUiThread(runnable);
    }
}
