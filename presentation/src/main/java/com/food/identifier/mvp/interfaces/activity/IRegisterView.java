package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 12/11/2017.
 */

public interface IRegisterView extends IBaseView {

    void hideScreen();

    void showProgress();

    void hideProgress();

    void configureToolbar();

    void showLoginValidationError(int error);

    void showPasswordValidationError(int error);

    void showFirstNameValidationError(int error);

    void showLastNameValidationError(int error);

    void showError(String message);

    void closeScreen();

    void saveLoginState();
}
