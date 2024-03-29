package com.food.identifier.mvp.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.PaletteAsyncListener;
import android.support.v7.graphics.Palette.Swatch;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.IProductView;
import com.food.identifier.mvp.model.ProductHolder;
import com.food.identifier.mvp.presenter.activity.ProductPresenter;
import com.food.identifier.mvp.view.adapters.ProductImageAdapter;
import com.food.identifier.mvp.view.adapters.ViewPagerProductDescriptionAdapter;
import com.food.identifier.mvp.view.fragments.HistoryFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Menu.FIRST;
import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.food.identifier.mvp.presenter.activity.ImagePresenter.IMAGE_URL;

/**
 * Created by Taras Matolinets
 * Data: 9/23/2017.
 * Time: 4:24 PM
 * Company: FoodDelivery
 */
public class ProductActivity extends MvpActivity<ProductPresenter> implements IProductView, OnPageChangeListener {

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
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public ProductPresenter createPresenter() {
        return new ProductPresenter(mComponent);
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
        mNavView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigator.replaceActivityAnimation(ProductActivity.this, LoginActivity.class, android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        mNavView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(mNavView);

                switch (item.getItemId()) {
                    case R.id.nav_scan:
                        mNavigator.replaceActivityAnimation(ProductActivity.this, IdScannerActivity.class, android.R.anim.fade_in, android.R.anim.fade_out);
                        return true;
                    case R.id.nav_history:
                        mNavigator.replace(ProductActivity.this, HistoryFragment.class, R.id.fl_container, null, false);
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
        mVpProductImage.setOffscreenPageLimit(1);
        mVpProductImage.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    @Override
    public void changeToolbarColor(int color) {
        mToolbarStore.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    @Override
    public void imageClick(List<String> imageUls) {
        int item = mVpProductImage.getCurrentItem();
        String imageUrl = imageUls.get(item);

        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrl);

        mNavigator.replaceActivityAnimation(this, ImageDetailsActivity.class, bundle, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void showOrganizationView() {

    }

    @Override
    public void showDrawerItem() {
        int addProduct = 0;
        mNavView.getMenu().getItem(addProduct).setVisible(true);
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
