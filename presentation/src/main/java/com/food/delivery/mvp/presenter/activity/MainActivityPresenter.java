package com.food.delivery.mvp.presenter.activity;

import android.app.Activity;
import android.location.Location;

import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.mvp.interfaces.activity.IMainView;
import com.food.delivery.mvp.model.FoodPackageHolder;
import com.food.delivery.mvp.presenter.BasePresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:25 PM
 * Company: FoodDelivery
 */

public class MainActivityPresenter extends BasePresenter<IMainView> {
    @Inject FoodPackageHolder mFoodPackageHolder;

    public MainActivityPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        setMapLocation(activity, fusedLocationClient);

        mView.initToolBar();
        mView.initActionBar();
        mView.setNavigationListener();
        mView.createAdapter();
    }

    private void setMapLocation(final Activity activity, final FusedLocationProviderClient fusedLocationClient) {
        fusedLocationClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mFoodPackageHolder.setLatitude(location.getLatitude());
                    mFoodPackageHolder.setLongitude(location.getLongitude());
                }
            }
        });
    }

    @Override
    protected void onViewDetach() {

    }
}
