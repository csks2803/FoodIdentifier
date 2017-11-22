package com.food.delivery.di.module;

import android.content.Context;

import com.food.delivery.di.annotation.PerActivity;
import com.food.delivery.mvp.model.FoodPackageHolder;
import com.food.delivery.mvp.model.FoodPackagePresenterModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:39 AM.
 * Company : FoodDelivery
 */
@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context context) {
        mContext = context;
    }

    @PerActivity
    @Provides
    FoodPackageHolder provideFoodPackage() {
        return new FoodPackageHolder();
    }
}
