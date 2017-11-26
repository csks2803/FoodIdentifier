package com.food.identifier.mvp.interfaces.activity;

import android.graphics.Bitmap;

import com.food.identifier.di.IHasComponent;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.model.ItemDrawerModel;

import java.util.List;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:24 PM
 * Company: FoodDelivery
 */
public interface IMainView extends IBaseView, IHasComponent<ActivityComponent> {

    void initToolBar();

    void setAdapter();

    void initActionBar();

    void setNavigationListener();

    void setupTabLayout();

    void setCollapseToolBarColor(Bitmap resource);

    void setProductImageAdapter(List<String> imageUrls);

    void prepareMenuAdapter(List<ItemDrawerModel> list);
}
