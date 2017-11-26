package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.food.identifier.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by taras on 11/26/2017.
 */

public class ProductImageAdapter extends PagerAdapter {

    private final List<String> mList;
    private final Context mContext;
    private final LayoutInflater mInflater;

    public ProductImageAdapter(Context context, List<String> listUrls) {
        mContext = context;
        mList = listUrls;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = mList.get(position);
        View view = mInflater.inflate(R.layout.item_product_image, container, false);

        ButterKnife.bind(this, view);

        ImageView ivProduct = view.findViewById(R.id.iv_product);
        Glide.with(mContext).load(url).into(ivProduct);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
