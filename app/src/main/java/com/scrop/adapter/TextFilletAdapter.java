package com.scrop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.scrop.entity.ItemTextBean;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.viewholder.TextCircularViewHolder;
import com.scrop.youcaile.R;

import java.util.List;

/**
 * Created by Scrop on 2017/8/28.
 */

public class TextFilletAdapter extends RecyclerView.Adapter {

    private int selectedPosition = -1;

    List<List> dates;
    private Context mContext;

    public static final int TYPE_SPACE=0;
    public static final int  TYPE_CONTENT=1;

    private OnItemClickListener listener = null;


    public TextFilletAdapter(List<List> dates, Context mContext) {
        this.dates = dates;
        this.mContext = mContext;
    }


    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setDates(List<List> dates) {
        this.dates = dates;
        selectedPosition = -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SPACE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.cell_line,parent,false);
            return new BaseViewHolder(view);
        }else if (viewType == TYPE_CONTENT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.cell_text_circular,parent,false);
            return new TextCircularViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if ( TYPE_CONTENT== getItemViewType(position)){
            TextCircularViewHolder holder1 = (TextCircularViewHolder) holder;
            final ItemTextBean bean = (ItemTextBean) dates.get(0).get(position);
            holder1.viewTextIsSel(bean);
            if (bean.isSelected() == true){
                selectedPosition = position;
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPosition != position){
                        try {
                            ((ItemTextBean) dates.get(0).get(selectedPosition)).setSelected(false);
                            notifyItemChanged(selectedPosition);
                        }catch (Exception e){
                        }
                        selectedPosition = position;
                        ((ItemTextBean) dates.get(0).get(position)).setSelected(true);
                        notifyItemChanged(position);
                        if (listener != null){
                            listener.onItemClick(v, selectedPosition);
                        }
                    }
                }
            });
        }else {
            BaseViewHolder holder1 = (BaseViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        int count  = dates.size();
        for (List temps :dates){
            count += temps.size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        for (List temp :dates){
            if (temp.size() > position){
                return TYPE_CONTENT;
            }else if (position - temp.size() == 0){
                return TYPE_SPACE;
            }
            position -= (temp.size() + 1);
        }
        return 0;
    }

    public void setDefaultSel(int defaultPosition){
        selectedPosition = defaultPosition;
        ((ItemTextBean) dates.get(0).get(defaultPosition)).setSelected(true);
        this.notifyItemChanged(defaultPosition);
    }

}
