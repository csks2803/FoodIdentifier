package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;

import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ITutorialView;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.mvp.presenter.activity.LoginPresenter.LoginSuccess;
import com.food.identifier.mvp.presenter.activity.RegisterPresenter.RegisterSuccess;
import com.food.identifier.other.utility.SharedPrefPreferencesWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.food.identifier.other.utility.SharedPrefPreferencesWrapper.KEY_TUTORIAL;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 3:40 PM
 * Company: FoodDelivery
 */
public class TutorialPresenter extends BasePresenter<ITutorialView> {

    public static final int LOGIN = 2;
    public TutorialPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        EventBus.getDefault().register(this);
        mView.createAdapter();
    }

    @Override
    protected void onViewDetach() {
        EventBus.getDefault().unregister(this);
    }

    public void replaceAction() {
        mView.replace();
    }

    public void saveTutorialVisit(Activity activity) {
        SharedPrefPreferencesWrapper mSharedManager = new SharedPrefPreferencesWrapper();
        mSharedManager.saveToSharedPreferences(activity, KEY_TUTORIAL, true);
    }

    @Subscribe
    public void successLogin(LoginSuccess successLogin) {
        mView.removeLoginItem(LOGIN);
    }

    @Subscribe
    public void successRegister(RegisterSuccess successRegister) {
        mView.removeLoginItem(LOGIN);
    }
}
