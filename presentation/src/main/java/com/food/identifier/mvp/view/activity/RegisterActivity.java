package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IRegisterView;
import com.food.identifier.mvp.presenter.activity.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by taras on 12/9/2017.
 */

public class RegisterActivity extends MvpActivity<RegisterPresenter> implements IRegisterView {
    @BindView(R.id.toolbar_header) Toolbar mToolbar;
    @BindView(R.id.et_input_login) EditText mEtLogin;
    @BindView(R.id.et_input_password) EditText mEtPassword;
    @BindView(R.id.pr_login_loading) ProgressBar mPrLoginLoading;
    @BindView(R.id.et_input_first_name) EditText mEtFirstName;
    @BindView(R.id.et_last_name) EditText mEtLastName;

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter(createActivityComponent());
    }

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public void configureToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_close_white);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClick() {
        mPresenter.showProgress();
        mPresenter.registerUser(this, mEtLogin.getText().toString(), mEtPassword.getText().toString(), mEtFirstName.getText().toString(), mEtLastName.getText().toString());
    }

    @Override
    public void showLoginValidationError(String error) {
        mEtLogin.setError(error);
    }

    @Override
    public void showPasswordValidationError(String error) {
        mEtPassword.setError(error);
    }

    @Override
    public void showFirstNameValidationError(String error) {
        mEtFirstName.setError(error);
    }

    @Override
    public void showLastNameValidationError(String error) {
        mEtLastName.setError(error);
    }

    @Override
    public void showProgress() {
        mPrLoginLoading.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        mPrLoginLoading.setVisibility(GONE);
    }

    @Override
    public void hideScreen() {
        finish();
    }

}
