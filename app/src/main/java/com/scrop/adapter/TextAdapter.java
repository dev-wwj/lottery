package com.scrop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.ItemTextBean;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.viewholder.TextViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/7/26.
 */

public class TextAdapter extends RecyclerView.Adapter {

    List<ItemTextBean> dates;
    private int selectedPosition = -1;

    private OnItemClickListener listener = null;

    public TextAdapter(List<ItemTextBean> dates) {
        this.dates = dates;
    }

    public void setDates(List<ItemTextBean> dates) {
        this.dates = dates;
        selectedPosition = -1;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_text,parent,false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextViewHolder viewHolder = (TextViewHolder) holder;
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
                        e.printStackTrace();
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
