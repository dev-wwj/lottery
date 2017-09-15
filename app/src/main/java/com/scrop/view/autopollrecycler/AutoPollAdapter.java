package com.scrop.view.autopollrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.viewholder.TextViewHolder;
import com.scrop.youcaile.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Scrop on 2017/7/24.
 */

public class AutoPollAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private final Context mContext;
    private final List<String > mData;

    private OnItemClickListener onItemClickListener;

    public AutoPollAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_text,parent,false);
        TextViewHolder holder = new TextViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String data = mData.get(position % mData.size());
        TextViewHolder tHolder = (TextViewHolder) holder;
        tHolder.itemView.setTag(position);
        tHolder.viewText(data);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(v, ((int)v.getTag())%mData.size());
        }
    }
}
