package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 12/11/2017.
 */

public interface IRegisterView extends IBaseView {

    void hideScreen();
    void showProgress();
    void hideProgress();
    void configureToolbar();
    void showLoginValidationError(String error);
    void showPasswordValidationError(String error);
    void showFirstNameValidationError(String error);
    void showLastNameValidationError(String error);

}
