package com.food.identifier.mvp.interfaces.fragment;


import com.food.identifier.mvp.interfaces.activity.IBaseView;

/**
 * Created by taras on 9/24/2017.
 */

public interface ITutorialScreenView extends IBaseView {
    void bindTitle();

    void setTitle(String text);

    void showHideButtons();

    void showHideLogin(int visibilityState);

    void showHideRegister(int visibilityState);

    void replaceToLogin();

    void replaceToSelectRole();
}
