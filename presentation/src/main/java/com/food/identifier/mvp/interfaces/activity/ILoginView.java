package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 12/9/2017.
 */

public interface ILoginView extends IBaseView {
    void showEmailValidationError(int error);

    void showPasswordValidationError(int error);

    void showLoading();

    void configureToolbar();

    void hideProgress();

    void replaceToMainScreen();

    void replaceToSignUp();

    void showError(String message);

    void replaceToScanScreen();

    void closeScreen();

    void saveLoginState();
}
