package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.food.identifier.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by taras on 11/26/2017.
 */

public class ProductImageAdapter extends PagerAdapter implements OnClickListener {

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
        final String url = mList.get(position);
        final View view = mInflater.inflate(R.layout.item_product_image, container, false);

        ButterKnife.bind(this, view);

        final ImageView ivProduct = view.findViewById(R.id.iv_product);
        final ProgressBar prLoadImage = view.findViewById(R.id.pr_image_loading);
        loadGlideImage(url, ivProduct, prLoadImage);
        ivProduct.setOnClickListener(this);

        container.addView(view);

        return view;
    }

    private void loadGlideImage(String url, ImageView ivProduct, final ProgressBar prLoadImage) {
        ivProduct.setTag(url);
        Glide.with(mContext).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                prLoadImage.setVisibility(GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                prLoadImage.setVisibility(GONE);
                return false;
            }
        }).into(ivProduct);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_product:
                String url = (String) view.getTag();

                ProductImageClick productImageClick = new ProductImageClick();
                productImageClick.setUrl(url);

                EventBus.getDefault().post(productImageClick);
                break;
        }
    }

    public static class ProductImageClick {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
