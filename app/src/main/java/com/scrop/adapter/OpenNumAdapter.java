package com.scrop.adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.viewholder.OpenNumViewHolder;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/28.
 */

public class OpenNumAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_opennum,parent,
                false);
        OpenNumViewHolder vh = new OpenNumViewHolder(view);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OpenNumViewHolder onvHolder = (OpenNumViewHolder) holder;
        onvHolder.viewValue(null);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
