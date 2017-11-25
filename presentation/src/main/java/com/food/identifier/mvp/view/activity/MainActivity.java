package com.food.identifier.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IMainView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.activity.MainActivityPresenter;
import com.food.identifier.mvp.view.adapters.ViewPagerProductDescriptionAdapter;
import com.food.identifier.mvp.view.fragments.ProductFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:24 PM
 * Company: FoodDelivery
 */
public class MainActivity extends MvpActivity<MainActivityPresenter> implements IMainView {

    @BindView(R.id.nav_view) NavigationView mNavView;
    @BindView(R.id.toolbar_header) Toolbar mToolbarStore;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.vp_product) ViewPager vpProduct;

    @Inject ProductHolder mProductHolder;

    private ActivityComponent mComponent;

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(mComponent);
    }

    @Override
    public void replace() {
        mNavigator.replace(this, ProductFragment.class, R.id.fl_main_splash, null, false);
    }

    @Override
    public void setupComponent() {
        super.setupComponent();
        mComponent = createActivityComponent();
        mComponent.inject(this);
    }

    @Override
    public void initToolBar() {
        setSupportActionBar(mToolbarStore);
    }

    @Override
    public void setAdapter() {
        ViewPagerProductDescriptionAdapter adapter = new ViewPagerProductDescriptionAdapter(this, getSupportFragmentManager());
        vpProduct.setAdapter(adapter);
    }

    @Override
    public void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setNavigationListener() {
        mNavView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_scan:
                        mNavigator.replaceActivity(MainActivity.this, IdScannerActivity.class);
                        return true;
                    case R.id.nav_history:
                        // mNavigator.replace(MainActivity.this, HistoryFragment.class, R.id.fl_container, null, false);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                mDrawerLayout.openDrawer(mNavView);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavView)) {
            mDrawerLayout.closeDrawer(mNavView);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public ActivityComponent getComponent() {
        return mComponent;
    }

}
