package com.food.identifier.mvp.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.PaletteAsyncListener;
import android.support.v7.graphics.Palette.Swatch;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IMainView;
import com.food.identifier.mvp.model.ItemDrawerModel;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.activity.MainActivityPresenter;
import com.food.identifier.mvp.view.adapters.ProductImageAdapter;
import com.food.identifier.mvp.view.adapters.ViewPagerProductDescriptionAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:24 PM
 * Company: FoodDelivery
 */
public class MainActivity extends MvpActivity<MainActivityPresenter> implements IMainView, OnPageChangeListener {

    public static final int DEFAULT_POSITION = 0;
    @BindView(R.id.nav_view) NavigationView mNavView;
    @BindView(R.id.toolbar_header) Toolbar mToolbarStore;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.vp_product) ViewPager mVpProduct;
    @BindView(R.id.vp_product_image) ViewPager mVpProductImage;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.col_tool_bar) CollapsingToolbarLayout mCollapsingToolBar;
    @BindView(R.id.iv_left_nav) ImageView mIvLeftNav;
    @BindView(R.id.iv_right_nav) ImageView mIvRightNav;


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
        mVpProduct.setAdapter(adapter);
    }

    @Override
    public void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
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

    @OnClick(R.id.iv_left_nav)
    public void navLeftClick() {
        mVpProductImage.arrowScroll(FOCUS_LEFT);
    }

    @OnClick(R.id.iv_right_nav)
    public void navRightClick() {
        mVpProductImage.arrowScroll(FOCUS_RIGHT);
    }

    @Override
    public void setupTabLayout() {
        mTabLayout.setupWithViewPager(mVpProduct);
    }

    @Override
    public void setCollapseToolBarColor(Bitmap resource) {
        Palette.from(resource).generate(new PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant != null) {
                    mCollapsingToolBar.setContentScrimColor(vibrant.getTitleTextColor());
                    mCollapsingToolBar.setStatusBarScrimColor(vibrant.getRgb());
                }
            }
        });
    }

    @Override
    public void setProductImageAdapter(List<String> imageUrls) {
        ProductImageAdapter adapter = new ProductImageAdapter(this, imageUrls);

        mVpProductImage.setAdapter(adapter);
        mVpProductImage.setCurrentItem(0);
        mVpProductImage.setOffscreenPageLimit(1);
        mVpProductImage.addOnPageChangeListener(this);
    }

    @Override
    public void prepareMenuAdapter(List<ItemDrawerModel> list) {

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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == DEFAULT_POSITION) {
            mIvLeftNav.setVisibility(GONE);
            mIvRightNav.setVisibility(VISIBLE);
        } else if (position == mVpProductImage.getAdapter().getCount() - 1) {
            mIvRightNav.setVisibility(GONE);
            mIvLeftNav.setVisibility(VISIBLE);
        } else {
            mIvRightNav.setVisibility(VISIBLE);
            mIvLeftNav.setVisibility(VISIBLE);
        }
        mPresenter.changeCollapseColor(this, position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
