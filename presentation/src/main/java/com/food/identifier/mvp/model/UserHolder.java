package com.food.identifier.mvp.model;

import javax.inject.Inject;

/**
 * Created by taras on 12/30/2017.
 */

public class UserHolder {
    private UserPresenterModel mUserPresenterModel;

    @Inject
    public UserHolder() {
    }

    public UserPresenterModel getUserPresenterModel() {
        return mUserPresenterModel;
    }

    public void setUserPresenterModel(UserPresenterModel mUserPresenterModel) {
        this.mUserPresenterModel = mUserPresenterModel;
    }
}
