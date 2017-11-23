package com.food.delivery.di.module;

import android.content.Context;

import dagger.Module;

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

}
