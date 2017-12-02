package com.food.identifier.mvp.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.food.identifier.R;
import com.food.identifier.mvp.interfaces.activity.IImageView;
import com.food.identifier.mvp.presenter.activity.ImagePresenter;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.Window.FEATURE_ACTION_BAR_OVERLAY;

/**
 * Created by Otentico Limited on 11/21/2016.
 * Image zoom screen
 */

public class ImageDetailsActivity extends MvpActivity<ImagePresenter> implements IImageView {

    @BindView(R.id.toolbar_header) Toolbar mToolbar;
    @BindView(R.id.image) PhotoView mImage;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    public static final String EMPTY_TITLE = "";

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        requestWindowFeature(FEATURE_ACTION_BAR_OVERLAY);

        setContentView(R.layout.activity_image_details);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public ImagePresenter createPresenter() {
        return new ImagePresenter(createActivityComponent());
    }

    public void hideProgress() {
        mProgressBar.setVisibility(GONE);
    }

    @Override
    public void loadImage(String url) {
        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                hideProgress();
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                hideProgress();
                return false;
            }
        }).into(mImage);
    }

    @Override
    public void configureToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_close_white);
        mToolbar.setTitle(EMPTY_TITLE);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
