package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.ILoginView;
import com.food.identifier.mvp.presenter.activity.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by taras on 12/9/2017.
 */

public class LoginActivity extends MvpActivity<LoginPresenter> implements ILoginView {
    @BindView(R.id.toolbar_header) Toolbar mToolbar;
    @BindView(R.id.et_input_login) EditText mEtInputEmail;
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

    @OnClick(R.id.btn_sign_in)
    public void loginClick() {
        mPresenter.loginClick(this, mEtInputEmail.getText(), mEtInputPassword.getText());
    }

    @OnClick(R.id.tv_sign_up)
    public void signUpClick() {
        mPresenter.signUpClick();
    }

    @Override
    public void showEmailValidationError(String error) {
        mEtInputEmail.setError(error);
    }

    @Override
    public void showPasswordValidationError(String error) {
        mEtInputPassword.setError(error);
    }

    @Override
    public void showLoading() {
        mPrLoginLoading.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        mPrLoginLoading.setVisibility(GONE);
    }

    @Override
    public void closeScreen() {
        finish();
    }

    @Override
    public void replaceToSignUp() {
        mNavigator.replaceActivityAnimation(this, SelectRoleActivity.class, android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
