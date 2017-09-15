package com.scrop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.viewholder.OmitViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/7/27.
 */

public class OmitAdapter extends RecyclerView.Adapter {
    private List<Object> dates ;
    private boolean isCanBuy;

    public OmitAdapter(List<Object> dates, boolean isCanBuy) {
        this.dates = dates;
        this.isCanBuy = isCanBuy;
    }

    public void setDates(List<Object> dates) {
        this.dates = dates;
    }

    public void setCanBuy(boolean canBuy) {
        isCanBuy = canBuy;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_omit,parent,false);
        OmitViewHolder vh = new OmitViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OmitViewHolder vh = (OmitViewHolder) holder;
        vh.viewValue(null,isCanBuy);
        vh.itemView.findViewById(R.id.id_mcbfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
