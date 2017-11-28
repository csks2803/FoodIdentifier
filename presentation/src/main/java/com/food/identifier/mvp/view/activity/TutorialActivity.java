package com.food.identifier.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.food.identifier.R;
import com.food.identifier.di.components.ActivityComponent;
import com.food.identifier.mvp.interfaces.activity.ITutorialView;
import com.food.identifier.mvp.presenter.activity.TutorialPresenter;
import com.food.identifier.mvp.view.adapters.TutorialAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by Taras Matolinets
 * Data: 9/9/2017.
 * Time: 3:46 PM
 * Company: FoodDelivery
 */
public class TutorialActivity extends MvpActivity<TutorialPresenter> implements ITutorialView {
    @BindView(R.id.vp_tutorial) ViewPager mVpTutorial;
    @BindView(R.id.indicator) CircleIndicator mIndicator;
    @BindView(R.id.bt_skip) Button mButtonSkip;
    @BindView(R.id.bt_done) Button mButtonDone;
    @BindView(R.id.iv_next) ImageView mIvNext;

    private ActivityComponent mComponent;

    @Override
    protected void onInit(@Nullable Bundle bundle) {
        setContentView(R.layout.activity_tutorial);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupComponent() {
        mComponent = createActivityComponent();
        mComponent.inject(this);
    }

    @NonNull
    @Override
    public TutorialPresenter createPresenter() {
        return new TutorialPresenter(mComponent);
    }

    @Override
    public void replace() {
        mNavigator.replaceActivityFlag(this, IdScannerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    public void createAdapter() {
        TutorialAdapter adapter = new TutorialAdapter(this, getSupportFragmentManager());
        mVpTutorial.setAdapter(adapter);
        mVpTutorial.addOnPageChangeListener(this);
        mIndicator.setViewPager(mVpTutorial);
    }

    @OnClick({R.id.iv_next, R.id.bt_skip, R.id.bt_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_next:
                int nextItem = 0;

                if (nextItem < mVpTutorial.getAdapter().getCount() - 1) {
                    nextItem = 1;
                }
                nextItem = nextItem + mVpTutorial.getCurrentItem();

                mVpTutorial.setCurrentItem(nextItem);
                break;
            case R.id.bt_skip:
            case R.id.bt_done:
                mPresenter.replaceToStoreList();
                break;
        }
        mPresenter.saveTutorialVisit(this);
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
        switch (position) {
            case 0:
            case 1:
            case 2:
                mIvNext.setVisibility(VISIBLE);
                mButtonDone.setVisibility(GONE);
                mButtonSkip.setVisibility(VISIBLE);
                break;
            case 3:
                mIvNext.setVisibility(GONE);
                mButtonDone.setVisibility(VISIBLE);
                mButtonSkip.setVisibility(INVISIBLE);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
