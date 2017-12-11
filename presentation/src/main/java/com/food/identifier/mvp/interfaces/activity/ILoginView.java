package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 12/9/2017.
 */

public interface ILoginView extends IBaseView {
    void showEmailValidationError(String error);

    void showPasswordValidationError(String error);

    void showLoading();

    void configureToolbar();

    void hideProgress();

    void closeScreen();

    void replaceToSignUp();
}
