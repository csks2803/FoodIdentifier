package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.food.identifier.R;
import com.food.identifier.mvp.view.fragments.ProductAboutFragment;
import com.food.identifier.mvp.view.fragments.ProductCharacteristicFragment;
import com.food.identifier.mvp.view.fragments.ProductFeedbackFragment;

/**
 * Created by taras on 11/6/2017.
 */

public class ViewPagerProductDescriptionAdapter extends FragmentStatePagerAdapter {

    private static final int ITEM_COUNT = 3;

    private static final int ABOUT_PRODUCT = 0;
    private static final int DESCRIPTION_PRODUCT = 1;
    private static final int FEEDBACK_PRODUCT = 2;

    private final Context mContext;

    public ViewPagerProductDescriptionAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case ABOUT_PRODUCT:
                return new ProductAboutFragment();
            case DESCRIPTION_PRODUCT:
                return new ProductCharacteristicFragment();
            case FEEDBACK_PRODUCT:
                return new ProductFeedbackFragment();
            default:
                return new ProductAboutFragment();
        }
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case ABOUT_PRODUCT:
                return mContext.getString(R.string.about);
            case DESCRIPTION_PRODUCT:
                return mContext.getString(R.string.description);
            case FEEDBACK_PRODUCT:
                return mContext.getString(R.string.feedback);
            default:
                return mContext.getString(R.string.about);
        }
    }
}
