package com.food.delivery.application;

import android.app.Application;

import com.food.delivery.di.components.DaggerFoodIdentifierAppComponent;
import com.food.delivery.di.components.FoodIdentifierAppComponent;
import com.food.delivery.di.module.FoodIdentificatorAppModule;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:38 AM.
 * Company : FoodIdentifier
 */

public class FoodIdentifierApplication extends Application {
    public static String TAG = "food.identifier";
    private FoodIdentifierAppComponent mFoodIdentifierApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
    }

    private void initializeInjector() {
        mFoodIdentifierApplication = DaggerFoodIdentifierAppComponent.builder()
                .foodIdentificatorAppModule(new FoodIdentificatorAppModule(this))
                .build();
    }

    public FoodIdentifierAppComponent getFoodDeliveryAppComponent() {
        return mFoodIdentifierApplication;
    }

}
