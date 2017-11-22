package com.food.delivery.di.components;

import com.food.delivery.di.module.ActivityModule;
import com.food.delivery.di.module.FoodDeliveryAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:38 AM.
 * Company : FoodDelivery
 */
@Singleton
@Component(modules = FoodDeliveryAppModule.class)
public interface FoodDeliveryAppComponent {

    ActivityComponent plus(ActivityModule module);
}
