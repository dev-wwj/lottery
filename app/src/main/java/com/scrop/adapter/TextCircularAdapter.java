package com.scrop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.ItemTextBean;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.viewholder.TextCircularViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/8/25.
 */

public class TextCircularAdapter extends RecyclerView.Adapter {

    private int selectedPosition = -1;

    private OnItemClickListener listener = null;

    List<ItemTextBean> dates;


    public TextCircularAdapter(List<ItemTextBean> dates) {
        this.dates = dates;
    }

    public void setDates(List<ItemTextBean> dates) {
        this.dates = dates;
        selectedPosition = -1;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_text_circular,parent,
                false);
        return new TextCircularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextCircularViewHolder viewHolder = (TextCircularViewHolder) holder;
        viewHolder.viewTextIsSel(dates.get(position));
        if (dates.get(position).isSelected() == true){
            selectedPosition = position;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != position){
                    try {
                        dates.get(selectedPosition).setSelected(false);
                        notifyItemChanged(selectedPosition);
                    }catch (Exception e){
                    }
                    selectedPosition = position;
                    dates.get(selectedPosition).setSelected(true);
                    notifyItemChanged(position);
                }
                if (listener != null){
                    listener.onItemClick(v, selectedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}
