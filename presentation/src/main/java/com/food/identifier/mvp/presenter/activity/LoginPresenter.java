package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ILoginView;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.utility.Utility;

/**
 * Created by taras on 12/9/2017.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity context) {

    }

    @Override
    protected void onViewDetach() {

    }

    public void signUpClick() {

    }

    public void loginClick(Activity activity, Editable email, Editable password) {
        boolean isEmailValid = emailValidation(activity, email);
        boolean isPasswordValid = passwordValidation(activity, password);

        if (isEmailValid && isPasswordValid) {
            mView.showLoading();
        }
    }

    private boolean passwordValidation(Activity activity, Editable password) {
        String error;
        if (!TextUtils.isEmpty(password) && Utility.passwordValidate(password.toString())) {
            error = activity.getString(R.string.please_write_correct_email);
        } else {
            error = activity.getString(R.string.empty_password);
        }

        if (TextUtils.isEmpty(error)) {
            return true;
        } else {
            mView.checkPasswordValidation(error);
        }

        return false;
    }

    private boolean emailValidation(Activity activity, Editable email) {
        String error;
        if (!TextUtils.isEmpty(email) && Utility.emailValidate(email.toString())) {
            error = activity.getString(R.string.please_write_correct_email);
        } else {
            error = activity.getString(R.string.empty_email);
        }

        if (TextUtils.isEmpty(error)) {
            return true;
        } else {
            mView.checkEmailValidation(error);
        }

        return false;
    }

    private void login() {

    }
}
