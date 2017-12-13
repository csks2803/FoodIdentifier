package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.os.Bundle;

import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ISelectRoleView;
import com.food.identifier.mvp.presenter.BasePresenter;

import static com.food.identifier.other.Constants.USER_ROLE;

/**
 * Created by taras on 12/13/2017.
 */

public class SelectRolePresenter extends BasePresenter<ISelectRoleView> {

    public SelectRolePresenter(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {

    }

    public void replace(int role) {
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ROLE, role);
        mView.replaceToRegistration(bundle);
    }

    @Override
    protected void onViewDetach() {

    }
}
