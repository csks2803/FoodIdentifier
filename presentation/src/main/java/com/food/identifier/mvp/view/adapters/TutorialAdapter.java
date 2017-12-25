package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.food.identifier.R;
import com.food.identifier.mvp.view.fragments.TutorialScreenFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Taras Matolinets
 * Data: 9/24/2017.
 * Time: 1:56 PM
 * Company: FoodDelivery
 */
public class TutorialAdapter extends FragmentStatePagerAdapter {
    private List<String> mArrayList;

    public TutorialAdapter(Context context, FragmentManager fm) {
        super(fm);
        mArrayList = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.array_tutorial_desc)));
    }

    @Override
    public Fragment getItem(int position) {
        String description = mArrayList.get(position);
        return TutorialScreenFragment.getInstance(description, position);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    public void updateData(int item) {
        mArrayList.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
