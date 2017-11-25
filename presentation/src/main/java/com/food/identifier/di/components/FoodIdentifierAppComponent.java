package com.food.identifier.di.components;

import com.food.identifier.di.module.ActivityModule;
import com.food.identifier.di.module.FoodIdentificatorAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Taras Matolinets.
 * Date : 9/7/2017.
 * Time : 8:38 AM.
 * Company : FoodDelivery
 */
@Singleton
@Component(modules = FoodIdentificatorAppModule.class)
public interface FoodIdentifierAppComponent {

    ActivityComponent plus(ActivityModule module);
}
