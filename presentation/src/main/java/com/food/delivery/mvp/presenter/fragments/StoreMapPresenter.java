package com.food.delivery.mvp.presenter.fragments;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.food.delivery.R;
import com.food.delivery.di.components.FragmentComponent;
import com.food.delivery.mvp.interfaces.fragment.IStoreMapView;
import com.food.delivery.mvp.model.FoodPackageHolder;
import com.food.delivery.mvp.model.FoodPackagePresenterModel;
import com.food.delivery.mvp.presenter.BasePresenter;
import com.food.delivery.mvp.presenter.fragments.StoreListPresenter.OnPackagesLoad;
import com.fooddelivery.domain.executor.PostExecutionThread;
import com.fooddelivery.domain.executor.ThreadExecutor;
import com.fooddelivery.domain.net.IFoodDeliveryFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by taras on 11/6/2017.
 */

public class StoreMapPresenter extends BasePresenter<IStoreMapView> {
    @Inject ThreadExecutor mTreadExecutor;
    @Inject PostExecutionThread mPostExecutor;
    @Inject IFoodDeliveryFactory mFactory;
    @Inject FoodPackageHolder mFoodPackageHolder;

    private CompositeSubscription mComposeSubscription;
    private GoogleMap mGoogleMap;

    public StoreMapPresenter(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
        mComposeSubscription = new CompositeSubscription();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onViewAttach(Activity activity) {
    }

    public void setMapLocation(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng myLocation = new LatLng(mFoodPackageHolder.getLatitude(), mFoodPackageHolder.getLongitude());

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }

    @Override
    protected void onViewDetach() {
        mComposeSubscription.unsubscribe();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void loadStoreLocation(OnPackagesLoad packagesLoad) {
        List<FoodPackagePresenterModel> listFoodPackagePresenter = mFoodPackageHolder.getListFoodPackagePresenter();

        for (FoodPackagePresenterModel foodPackagePresenterModel : listFoodPackagePresenter) {
            LatLng myLocation = new LatLng(foodPackagePresenterModel.getLatitude(), foodPackagePresenterModel.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(myLocation);
            markerOptions.title(foodPackagePresenterModel.getStoreName());

            mGoogleMap.addMarker(markerOptions).setTag(foodPackagePresenterModel);
        }
    }

    public void showWindow(Marker marker) {
        FoodPackagePresenterModel foodPackagePresenterModel = (FoodPackagePresenterModel) marker.getTag();

        mView.replaceToPackageDescription();
    }

    public void createWindowInfoAdapter(GoogleMap googleMap, Activity activity) {
        googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(activity));
    }

    private class CustomInfoWindowAdapter implements InfoWindowAdapter {

        private final Activity activity;

        private ImageView ivPackage;
        private TextView tvPackageName;
        private TextView tvStoreName;
        private ConstraintLayout constraintLayout;

        CustomInfoWindowAdapter(Activity activity) {
            this.activity = activity;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            FoodPackagePresenterModel foodPackagePresenterModel = (FoodPackagePresenterModel) marker.getTag();

            View view = View.inflate(activity, R.layout.item_marker, null);
            tvStoreName = view.findViewById(R.id.tv_store_name);
            tvPackageName = view.findViewById(R.id.tv_package_name);
            ivPackage = view.findViewById(R.id.iv_package);

            Glide.with(activity).load("http://eathealthylivefit.com/wp-content/uploads/2013/02/banana-1-e1361844920191.jpg").into(ivPackage);

            if (foodPackagePresenterModel != null) {
                tvPackageName.setText(foodPackagePresenterModel.getPackageName());
                tvStoreName.setText(foodPackagePresenterModel.getStoreName());
                tvPackageName.setText(foodPackagePresenterModel.getPackageName());
            }
            return view;
        }
    }
}
