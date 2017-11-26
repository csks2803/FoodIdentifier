package com.food.identifier.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.food.identifier.R;
import com.food.identifier.mvp.model.ItemDrawerModel;

import java.util.List;

/**
 * Created by taras on 11/27/2017.
 */

public class MenuAdapter extends ArrayAdapter<ItemDrawerModel> {
    private final List<ItemDrawerModel> mListItemDrawer;
    private final LayoutInflater mLayoutInflater;

    public MenuAdapter(@NonNull Context context, int resource, List<ItemDrawerModel> listItemDrawer) {
        super(context, resource);
        mLayoutInflater = LayoutInflater.from(context);
        mListItemDrawer = listItemDrawer;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemDrawerModel itemDrawer = mListItemDrawer.get(position);
        MenuHolder holder;

        if (convertView == null) {
            holder = new MenuHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_navigation, parent, false);

            holder.tvTitle = convertView.findViewById(R.id.tv_title);

            convertView.setTag(holder);
        } else {
            holder = (MenuHolder) convertView.getTag();
        }

        holder.tvTitle.setText(itemDrawer.getTitle());

        return convertView;
    }

    private static class MenuHolder {
        TextView tvTitle;
    }
}
