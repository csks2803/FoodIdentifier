package com.food.delivery.mvp.interfaces.activity;

import android.support.annotation.NonNull;

/**
 * Created by Taras Matolinets
 * Data: 9/9/2017.
 * Time: 3:53 PM
 * Company: FoodDelivery
 */
public interface IBaseView {

    void runOnMainThread(@NonNull Runnable runnable);
}
