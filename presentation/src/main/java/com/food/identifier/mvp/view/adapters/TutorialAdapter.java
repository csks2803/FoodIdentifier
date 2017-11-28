package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.food.identifier.R;
import com.food.identifier.mvp.view.fragments.TutorialScreenFragment;


/**
 * Created by Taras Matolinets
 * Data: 9/24/2017.
 * Time: 1:56 PM
 * Company: FoodDelivery
 */
public class TutorialAdapter extends FragmentStatePagerAdapter {

    private static final int ITEM_COUNT = 4;
    private final String[] mArrayList;

    public TutorialAdapter(Context context, FragmentManager fm) {
        super(fm);
        mArrayList = context.getResources().getStringArray(R.array.array_tutorial_desc);
    }

    @Override
    public Fragment getItem(int position) {
        String description = mArrayList[position];
        return TutorialScreenFragment.getInstance(description);
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }
}
