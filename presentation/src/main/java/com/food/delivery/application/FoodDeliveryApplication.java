package com.food.delivery.application;

import android.app.Application;

import com.food.delivery.di.components.DaggerFoodDeliveryAppComponent;
import com.food.delivery.di.components.FoodDeliveryAppComponent;
import com.food.delivery.di.module.FoodDeliveryAppModule;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:38 AM.
 * Company : FoodDelivery
 */

public class FoodDeliveryApplication extends Application {
    public static String TAG = "food.delivery";
    private FoodDeliveryAppComponent mFoodDeliveryApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
    }

    private void initializeInjector() {
        mFoodDeliveryApplication = DaggerFoodDeliveryAppComponent.builder()
                .foodDeliveryAppModule(new FoodDeliveryAppModule(this))
                .build();
    }

    public FoodDeliveryAppComponent getFoodDeliveryAppComponent() {
        return mFoodDeliveryApplication;
    }

}
