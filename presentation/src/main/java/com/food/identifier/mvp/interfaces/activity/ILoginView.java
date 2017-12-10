package com.food.identifier.mvp.interfaces.activity;

/**
 * Created by taras on 12/9/2017.
 */

public interface ILoginView extends IBaseView {
    void checkEmailValidation(String error);

    void checkPasswordValidation(String error);

    void showLoading();

    void hideProgress();
}
