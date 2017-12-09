package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.ILoginView;
import com.food.identifier.mvp.presenter.activity.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

/**
 * Created by taras on 12/9/2017.
 */

public class LoginActivity extends MvpActivity<LoginPresenter> implements ILoginView {
    @BindView(R.id.et_input_email) EditText mEtInputEmail;
    @BindView(R.id.et_input_password) EditText mEtInputPassword;
    @BindView(R.id.pr_login_loading) ProgressBar mPrLoginLoading;

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(createActivityComponent());
    }

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void loginClick() {
        mPresenter.loginClick(this, mEtInputEmail.getText(), mEtInputPassword.getText());
    }

    @OnClick(R.id.tv_sign_up)
    public void signUpClick() {
        mPresenter.signUpClick();
    }

    @Override
    public void checkEmailValidation(String error) {
        mEtInputEmail.setError(error);
    }

    @Override
    public void checkPasswordValidation(String error) {
        mEtInputPassword.setError(error);
    }

    @Override
    public void showLoading() {
        mPrLoginLoading.setVisibility(VISIBLE);
    }
}
