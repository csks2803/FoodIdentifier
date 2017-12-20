package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.ISelectRoleView;
import com.food.identifier.mvp.presenter.activity.SelectRolePresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.food.identifier.other.Constants.ORGANIZATION_TYPE;
import static com.food.identifier.other.Constants.USER_TYPE;

/**
 * Created by taras on 12/13/2017.
 */

public class SelectRoleActivity extends MvpActivity<SelectRolePresenter> implements ISelectRoleView {
    @Override
    public void replaceToRegistration(Bundle bundle) {
        mNavigator.replaceActivityAnimation(this, RegisterActivity.class, bundle, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @NonNull
    @Override
    public SelectRolePresenter createPresenter() {
        return new SelectRolePresenter(createActivityComponent());
    }

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.select_role);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_user, R.id.bt_organization})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_user:
                mPresenter.replace(USER_TYPE);
                break;
            case R.id.bt_organization:
                mPresenter.replace(ORGANIZATION_TYPE);
                break;
        }
    }
}
