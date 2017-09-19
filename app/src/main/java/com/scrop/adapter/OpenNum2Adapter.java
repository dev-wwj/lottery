package com.scrop.adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.OpenNumResBean;
import com.scrop.viewholder.OpenNum2ViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/9/4.
 */

public class OpenNum2Adapter extends RecyclerView.Adapter {

    List<OpenNumResBean.ValueBean.ResultBean> onbs = null;
    String gameId = null;

    public OpenNum2Adapter(List<OpenNumResBean.ValueBean.ResultBean> onbs, String gameId) {
        this.onbs = onbs;
        this.gameId = gameId;
    }

    public List<OpenNumResBean.ValueBean.ResultBean> getOnbs() {
        return onbs;
    }

    public void setOnbs(List<OpenNumResBean.ValueBean.ResultBean> onbs) {
        this.onbs = onbs;
    }

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
        onvHolder.viewValue(onbs.get(position),gameId);
    }

    @Override
    public int getItemCount() {
        return onbs.size();
    }
}
