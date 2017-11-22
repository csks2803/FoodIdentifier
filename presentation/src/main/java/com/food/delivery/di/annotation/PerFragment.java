package com.food.delivery.di.annotation;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Taras Matolinets
 * Data: 9/19/2017.
 * Time: 8:42 AM
 * Company: FoodDelivery
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
