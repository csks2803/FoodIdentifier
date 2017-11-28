package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;

import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ITutorialView;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.other.utility.SharedPrefPreferencesWrapper;

import static com.food.identifier.other.utility.SharedPrefPreferencesWrapper.KEY_TUTORIAL;

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
