package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.identifier.R;
import com.food.identifier.mvp.model.ProductCharacteristicsPresentationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taras on 11/11/2017.
 */

public class CharacteristicAdapter extends RecyclerView.Adapter<CharacteristicAdapter.CharacteristicViewHolder> {
    private final Context mContext;
    private List<ProductCharacteristicsPresentationModel> mList = new ArrayList<>();

    public CharacteristicAdapter(Context context, List<ProductCharacteristicsPresentationModel> listCharacteristics) {
        mContext = context;
        mList = listCharacteristics;
    }

    @Override
    public CharacteristicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_characteristic, parent, false);
        return new CharacteristicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacteristicViewHolder holder, int position) {
        ProductCharacteristicsPresentationModel characteristicModel = mList.get(position);

        holder.tvPackageName.setText(characteristicModel.getTitle());
        holder.tvDescription.setText(characteristicModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<ProductCharacteristicsPresentationModel> list) {
        mList = list;
        notifyDataSetChanged();
    }

    static class CharacteristicViewHolder extends ViewHolder {
        @BindView(R.id.tv_title) TextView tvPackageName;
        @BindView(R.id.tv_description) TextView tvDescription;

        public CharacteristicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
