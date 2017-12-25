package com.food.identifier.mvp.presenter.fragments;

import android.app.Activity;
import android.text.TextUtils;

import com.food.identifier.di.annotation.PerActivity;
import com.food.identifier.di.components.FragmentComponent;
import com.food.identifier.mvp.interfaces.fragment.ITutorialScreenView;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.utility.SharedPrefPreferencesWrapper;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.food.identifier.mvp.presenter.activity.TutorialPresenter.LOGIN;
import static com.food.identifier.other.Constants.SUCCESS_LOGIN;

/**
 * Created by taras on 9/24/2017.
 */
@PerActivity
public class TutorialScreenPresenter extends BasePresenter<ITutorialScreenView> {
    @Inject SharedPrefPreferencesWrapper mSharedPrefPreferencesWrapper;

    public TutorialScreenPresenter(FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.showHideButtons();
        mView.bindTitle();
    }

    @Override
    protected void onViewDetach() {
    }

    public void bindTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mView.setTitle(title);
        }
    }

    public void showHideButtons(Activity activity, int position) {
        boolean isLogged = mSharedPrefPreferencesWrapper.getBooleanValue(activity, SUCCESS_LOGIN);
        if (position == LOGIN && !isLogged) {
            mView.showHideLogin(VISIBLE);
            mView.showHideRegister(VISIBLE);
        } else {
            mView.showHideLogin(GONE);
            mView.showHideRegister(GONE);
        }
    }

    public void replaceToLogin() {
        mView.replaceToLogin();
    }

    public void replaceToRegister() {
        mView.replaceToSelectRole();
    }
}
