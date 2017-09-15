package com.scrop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scrop.view.mycheckbox.MyCheckBoxForOmit;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/27.
 */

public class OmitViewHolder extends RecyclerView.ViewHolder {
    private TextView tvNums,tvNowOmit,tvMaxOmit;
    MyCheckBoxForOmit cbForOmit;
    public OmitViewHolder(View itemView) {
        super(itemView);
        initSubViews(itemView);
    }

    private void initSubViews(View itemView) {
        tvNums = (TextView) itemView.findViewById(R.id.id_tv_num);
        tvNowOmit = (TextView) itemView.findViewById(R.id.id_tv_nowomit);
        tvMaxOmit = (TextView) itemView.findViewById(R.id.id_tv_maxomit);
        cbForOmit = (MyCheckBoxForOmit) itemView.findViewById(R.id.id_mcbfo);
    }

    public void viewValue(Object o, boolean isCanBuy) {
        if (isCanBuy){
            cbForOmit.setVisibility(View.VISIBLE);
        }else {
            cbForOmit.setVisibility(View.GONE);
        }
    }
}
