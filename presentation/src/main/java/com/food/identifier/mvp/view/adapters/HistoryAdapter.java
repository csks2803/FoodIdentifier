package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.food.identifier.R;
import com.food.identifier.mvp.model.ProductPresentationModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by taras on 11/11/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final Context mContext;
    private List<ProductPresentationModel> mList = new ArrayList<>();

    public HistoryAdapter(Context context, List<ProductPresentationModel> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view, mList);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        ProductPresentationModel productPresentationModel = mList.get(position);

        holder.tvProductInfo.setText(productPresentationModel.getAbout());
        holder.tvProductName.setText(productPresentationModel.getTitle());
        holder.tvProductName.setText(productPresentationModel.getTitle());
        holder.tvTradeName.setText(productPresentationModel.getTradeMark());

        Glide.with(mContext).load(productPresentationModel.getImageUrls().get(0)).into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<ProductPresentationModel> list) {
        mList = list;
        notifyDataSetChanged();
    }

    static class HistoryViewHolder extends ViewHolder {
        private final List<ProductPresentationModel> listData;
        @BindView(R.id.iv_product_image) ImageView ivProductImage;
        @BindView(R.id.tv_product_info) TextView tvProductInfo;
        @BindView(R.id.tv_product_name) TextView tvProductName;
        @BindView(R.id.tv_trade_name) TextView tvTradeName;

        HistoryViewHolder(View itemView, List<ProductPresentationModel> list) {
            super(itemView);
            this.listData = list;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_history)
        public void historyItemClick() {
            ProductPresentationModel productPresentationModel = listData.get(getAdapterPosition());

            HistoryClick historyClick = new HistoryClick();
            historyClick.setProductPresentationModel(productPresentationModel);

            EventBus.getDefault().post(historyClick);
        }
    }

    public static class HistoryClick {
        private ProductPresentationModel productPresentationModel;

        public ProductPresentationModel getProductPresentationModel() {
            return productPresentationModel;
        }

        public void setProductPresentationModel(ProductPresentationModel productPresentationModel) {
            this.productPresentationModel = productPresentationModel;
        }
    }
}
