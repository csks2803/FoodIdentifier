package com.food.identifier.di.module;

import android.content.Context;

import dagger.Module;

/**
 * Created by Taras Matolinets
 * Data: 9/19/2017.
 * Time: 8:43 AM
 * Company: FoodDelivery
 */
@Module
public class FragmentModule {
    private final Context mContext;

    public FragmentModule(Context context) {
        mContext = context;
    }
}
