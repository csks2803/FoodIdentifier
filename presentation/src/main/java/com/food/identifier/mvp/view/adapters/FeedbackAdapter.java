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
import com.food.identifier.mvp.model.FeedbackPresentationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taras on 11/11/2017.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {
    private final Context mContext;
    private List<FeedbackPresentationModel> mList = new ArrayList<>();

    public FeedbackAdapter(Context context, List<FeedbackPresentationModel> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        FeedbackPresentationModel feedbackDomainModel = mList.get(position);

        holder.tvFeedback.setText(feedbackDomainModel.getFeedback());
        holder.tvUserName.setText(feedbackDomainModel.getUserName());

        Glide.with(mContext).load(feedbackDomainModel.getProfileUrl()).into(holder.ivUser);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<FeedbackPresentationModel> list) {
        mList = list;
        notifyDataSetChanged();
    }

    static class FeedbackViewHolder extends ViewHolder {
        @BindView(R.id.iv_user) ImageView ivUser;
        @BindView(R.id.tv_feedback) TextView tvFeedback;
        @BindView(R.id.tv_user_name) TextView tvUserName;

        public FeedbackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
