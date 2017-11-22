package com.food.delivery.di.module;

import android.content.Context;

import com.food.delivery.UIThread;
import com.food.delivery.application.FoodDeliveryApplication;
import com.fooddelivery.data.executor.JobExecutor;
import com.fooddelivery.data.net.FoodDeliveryFactory;
import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.net.IFoodDeliveryFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:39 AM.
 * Company : FoodDelivery
 */
@Module
public class FoodDeliveryAppModule {

    private final FoodDeliveryApplication application;

    public FoodDeliveryAppModule(FoodDeliveryApplication app) {
        application = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    PostExecutionThread provideUIThreadExecutor(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    IFoodDeliveryFactory provideFoodDeliveryFactory(FoodDeliveryFactory factory) {
        return factory;
    }
}
