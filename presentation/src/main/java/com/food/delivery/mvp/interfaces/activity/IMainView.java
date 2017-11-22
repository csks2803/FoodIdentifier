package com.food.delivery.mvp.interfaces.activity;

import com.food.delivery.di.IHasComponent;
import com.food.delivery.di.components.ActivityComponent;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:24 PM
 * Company: FoodDelivery
 */
public interface IMainView extends IBaseView, IHasComponent<ActivityComponent> {

    void replace();

    void createAdapter();

    void setupComponent();

    void initToolBar();

    void initActionBar();

    void setNavigationListener();
}
