package com.scrop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.ImgText;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.viewholder.ImageTextViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/7/24.
 */

public class ImgTextAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<ImgText> dates;

    private OnItemClickListener listener;

    public ImgTextAdapter(List<ImgText> dates) {
        this.dates = dates;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_imgtext,parent,false);
        return new ImageTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageTextViewHolder mHolder = (ImageTextViewHolder) holder;
//        mHolder.itemView.setTag(position);
        mHolder.viewValue(dates.get(position));
        mHolder.itemView.setTag(position);
        mHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }


    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        if (listener != null){
            listener.onItemClick(v, (Integer) v.getTag());
        }
    }
}
