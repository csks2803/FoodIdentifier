package com.food.identifier.di.module;

import android.content.Context;

import com.food.identifier.UIThread;
import com.food.identifier.application.FoodIdentifierApplication;
import com.food.identifier.mvp.model.ProductPresentationModel;
import com.fooddelivery.data.executor.JobExecutor;
import com.fooddelivery.data.net.FoodIdentifierFactory;
import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.net.IFoodIdentifierFactory;

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
public class FoodIdentificatorAppModule {

    private final FoodIdentifierApplication application;

    public FoodIdentificatorAppModule(FoodIdentifierApplication app) {
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
    IFoodIdentifierFactory provideFoodDeliveryFactory(FoodIdentifierFactory factory) {
        return factory;
    }

    @Provides
    @Singleton
    ProductPresentationModel provideProductPresentratio() {
        return new ProductPresentationModel();
    }
}
