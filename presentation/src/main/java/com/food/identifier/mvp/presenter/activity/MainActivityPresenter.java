package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IMainView;
import com.food.identifier.mvp.model.ItemDrawerModel;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:25 PM
 * Company: FoodIdentifier
 */

public class MainActivityPresenter extends BasePresenter<IMainView> {
    @Inject ProductHolder mProductHolder;

    public MainActivityPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        mView.initToolBar();
        mView.initActionBar();
        mView.setupTabLayout();
        mView.setAdapter();
        List<ItemDrawerModel> list = prepareDataForMenuAdapter(activity);
        mView.prepareMenuAdapter(list);
        mView.setNavigationListener();
        mView.setProductImageAdapter(mProductHolder.getProductHolder().getImageUrls());
    }

    private List<ItemDrawerModel> prepareDataForMenuAdapter(Context context) {
        List<ItemDrawerModel> listItems = new ArrayList<>();

        ItemDrawerModel itemIdentify = new ItemDrawerModel();
        itemIdentify.setTitle(context.getString(R.string.identify_product));

        listItems.add(itemIdentify);

        ItemDrawerModel itemHistory = new ItemDrawerModel();
        itemHistory.setTitle(context.getString(R.string.products_history));

        listItems.add(itemHistory);

        return listItems;
    }

    @Override
    protected void onViewDetach() {

    }

    public void changeCollapseColor(Context context, int position) {
        String url = mProductHolder.getProductHolder().getImageUrls().get(position);
        Glide.with(context).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                mView.setCollapseToolBarColor(resource);
            }
        });
    }
}
