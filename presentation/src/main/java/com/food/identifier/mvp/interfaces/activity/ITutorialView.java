package com.food.identifier.mvp.interfaces.activity;

import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.food.identifier.di.IHasComponent;
import com.food.identifier.di.components.ActivityComponent;


/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 3:41 PM
 * Company: FoodDelivery
 */
public interface ITutorialView extends IBaseView, IHasComponent<ActivityComponent>, OnPageChangeListener {

    void replace();

    void createAdapter();

}
