package com.food.delivery.mvp.presenter.activity;

import android.app.Activity;

import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.mvp.interfaces.activity.ITutorialView;
import com.food.delivery.mvp.presenter.BasePresenter;
import com.food.delivery.other.utility.SharedPrefPreferencesWrapper;

import javax.inject.Inject;

import static com.food.delivery.other.utility.SharedPrefPreferencesWrapper.KEY_TUTORIAL;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 3:40 PM
 * Company: FoodDelivery
 */
public class TutorialPresenter extends BasePresenter<ITutorialView> {

    public TutorialPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.createAdapter();
    }

    @Override
    protected void onViewDetach() {

    }

    public void replaceToStoreList() {
        mView.replace();
    }

    public void saveTutorialVisit(Activity activity) {
        SharedPrefPreferencesWrapper mSharedManager = new SharedPrefPreferencesWrapper();
        mSharedManager.saveToSharedPreferences(activity, KEY_TUTORIAL, true);
    }
}
