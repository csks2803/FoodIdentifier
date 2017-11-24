package com.food.delivery.mvp.view.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.food.delivery.R;
import com.food.delivery.mvp.view.fragments.StoreListFragment;
import com.food.delivery.mvp.view.fragments.StoreMapFragment;

/**
 * Created by taras on 11/6/2017.
 */

public class ViewPagerProductDescriptionAdapter extends FragmentStatePagerAdapter {

    private static final int ITEM_COUNT = 2;
    public static final int MAP_POSITION = 1;
    private final Context mContext;

    public ViewPagerProductDescriptionAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MAP_POSITION:
                return new StoreMapFragment();
            default:
                return new StoreListFragment();
        }
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return mContext.getString(R.string.map);
            default:
                return mContext.getString(R.string.stores);
        }
    }
}
