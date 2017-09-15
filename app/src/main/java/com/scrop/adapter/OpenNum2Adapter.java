package com.scrop.adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.viewholder.OpenNum2ViewHolder;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/4.
 */

public class OpenNum2Adapter extends RecyclerView.Adapter {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_opennum_style2,parent,
                false);
        OpenNum2ViewHolder vh = new OpenNum2ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OpenNum2ViewHolder onvHolder = (OpenNum2ViewHolder) holder;
        onvHolder.viewValue(null);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
