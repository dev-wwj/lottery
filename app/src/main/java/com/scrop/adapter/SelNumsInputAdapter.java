package com.scrop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.minterface.MyAction;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.viewholder.SelNumsInputViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/9/13.
 */

public class SelNumsInputAdapter  extends RecyclerView.Adapter{

    private List<String> dates = null;
    private OnItemClickListener listener = null;

    public SelNumsInputAdapter(List<String> dates, OnItemClickListener listener) {
        this.dates = dates;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_selnums_input,parent,
                false);
        SelNumsInputViewHolder viewHolder = new SelNumsInputViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SelNumsInputViewHolder viewHolder = (SelNumsInputViewHolder) holder;
        viewHolder.setAction(new MyAction() {
            @Override
            public void onAction(Object object) {
                SelNumsInputViewHolder sHolder = (SelNumsInputViewHolder) object;
                if (listener != null){
                    listener.onItemClick(sHolder.itemView,sHolder.getPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
    
}
