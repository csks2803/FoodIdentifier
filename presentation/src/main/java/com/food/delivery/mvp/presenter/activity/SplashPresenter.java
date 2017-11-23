package com.food.delivery.mvp.presenter.activity;

import android.app.Activity;
import android.os.Handler;

import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.mvp.interfaces.activity.ISplashView;
import com.food.delivery.mvp.presenter.BasePresenter;
import com.food.delivery.other.utility.SharedPrefPreferencesWrapper;

import static com.food.delivery.other.utility.SharedPrefPreferencesWrapper.KEY_TUTORIAL;

/**
 * Created by Taras Matolinets
 * Data: 9/9/2017.
 * Time: 3:50 PM
 * Company: FoodDelivery
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private static final long SPLASH_DISPLAY_LENGTH = 2000;

    public SplashPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.replace();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    protected void onViewDetach() {

    }
}
