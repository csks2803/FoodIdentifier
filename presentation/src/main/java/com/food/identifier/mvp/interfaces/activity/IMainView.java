package com.food.identifier.mvp.interfaces.activity;

import com.food.identifier.di.IHasComponent;
import com.food.identifier.di.components.ActivityComponent;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:24 PM
 * Company: FoodDelivery
 */
public interface IMainView extends IBaseView, IHasComponent<ActivityComponent> {

    void replace();

    void initToolBar();

    void setAdapter();

    void initActionBar();

    void setNavigationListener();
}
