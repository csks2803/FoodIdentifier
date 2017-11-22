package com.food.delivery.mvp.presenter.activity;

import android.app.Activity;
import android.location.Location;

import com.food.delivery.di.components.ActivityComponent;
import com.food.delivery.mvp.interfaces.activity.ITutorialView;
import com.food.delivery.mvp.model.FoodPackageHolder;
import com.food.delivery.mvp.presenter.BasePresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import javax.inject.Inject;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 3:40 PM
 * Company: FoodDelivery
 */
public class StoreDetailsPresenter extends BasePresenter<ITutorialView> {

    public StoreDetailsPresenter(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
    }

    @Override
    protected void onViewDetach() {

    }
}
