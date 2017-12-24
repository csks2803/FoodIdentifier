package com.food.identifier.mvp.presenter.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IProductView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.BasePresenter;
import com.food.identifier.mvp.presenter.activity.LoginPresenter.LoginSuccess;
import com.food.identifier.mvp.presenter.activity.RegisterPresenter.RegisterSuccess;
import com.food.identifier.mvp.view.adapters.ProductImageAdapter;
import com.food.identifier.mvp.view.adapters.ProductImageAdapter.ProductImageClick;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import static com.food.identifier.other.Constants.ORGANIZATION_TYPE;
import static com.food.identifier.other.Constants.USER_TYPE;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:25 PM
 * Company: FoodIdentifier
 */

public class ProductPresenter extends BasePresenter<IProductView> {
    @Inject ProductHolder mProductHolder;

    public ProductPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        EventBus.getDefault().register(this);
        mView.initToolBar();
        mView.initActionBar();
        mView.setupTabLayout();
        mView.setAdapter();
        mView.setNavigationListener();
        mView.setProductImageAdapter(mProductHolder.getProductHolder().getImageUrls());
    }

    @Override
    protected void onViewDetach() {
        EventBus.getDefault().unregister(this);
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

    @Subscribe
    public void changeToolbarColor(ChangeToolbarColor toolbarColor) {
        mView.changeToolbarColor(toolbarColor.getColor());
    }

    @Subscribe
    public void imageClick(ProductImageClick imageClick) {
        List<String> imageUls = mProductHolder.getProductHolder().getImageUrls();
        mView.imageClick(imageUls);
    }

    @Subscribe
    public void successLogin(LoginSuccess successLogin) {
        switch (successLogin.getRole()) {
            case ORGANIZATION_TYPE:
                mView.showOrganizationView();
                break;
        }
    }

    @Subscribe
    public void successRegister(RegisterSuccess successRegister) {
        switch (successRegister.getUserType()) {
            case ORGANIZATION_TYPE:
                mView.showOrganizationView();
                break;
        }
    }

    public static class ChangeToolbarColor {
        private int color;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
