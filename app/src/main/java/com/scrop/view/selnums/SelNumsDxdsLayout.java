package com.scrop.view.selnums;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.minterface.listener.SubOnClickListener;
import com.scrop.view.ballview.BallOmitView;
import com.scrop.youcaile.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Scrop on 2017/9/12.
 */

public class SelNumsDxdsLayout extends LinearLayout implements SubOnClickListener {
    TextView tvOmit;
    BallOmitView bov0,bov1,bov2,bov3,bov4,bov5,bov6,bov7,bov8,bov9,bov10,bov11,bov12,bov13,bov14,
            bov15;

    Set<Integer> selNums = new HashSet<>();

    OnDataChangeListener listener;

    public SelNumsDxdsLayout(Context context) {
        super(context);
    }

    public SelNumsDxdsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubViews(context,attrs);
    }

    public OnDataChangeListener getListener() {
        return listener;
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    private void initSubViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_selnums_dxds,this,true);
        tvOmit = (TextView) this.findViewById(R.id.id_tv_omit);
        bov0 =(BallOmitView) this.findViewById(R.id.id_bov_0);
        bov1 =(BallOmitView) this.findViewById(R.id.id_bov_1);
        bov2 =(BallOmitView) this.findViewById(R.id.id_bov_2);
        bov3 =(BallOmitView) this.findViewById(R.id.id_bov_3);
        bov4 =(BallOmitView) this.findViewById(R.id.id_bov_4);
        bov5 =(BallOmitView) this.findViewById(R.id.id_bov_5);
        bov6 =(BallOmitView) this.findViewById(R.id.id_bov_6);
        bov7 =(BallOmitView) this.findViewById(R.id.id_bov_7);
        bov8 =(BallOmitView) this.findViewById(R.id.id_bov_8);
        bov9 =(BallOmitView) this.findViewById(R.id.id_bov_9);
        bov10 =(BallOmitView) this.findViewById(R.id.id_bov_10);
        bov11 =(BallOmitView) this.findViewById(R.id.id_bov_11);
        bov12 =(BallOmitView) this.findViewById(R.id.id_bov_12);
        bov13 =(BallOmitView) this.findViewById(R.id.id_bov_13);
        bov14 =(BallOmitView) this.findViewById(R.id.id_bov_14);
        bov15 =(BallOmitView) this.findViewById(R.id.id_bov_15);

        bov0.setTag(0);
        bov1.setTag(1);
        bov2.setTag(2);
        bov3.setTag(3);
        bov4.setTag(10);
        bov5.setTag(11);
        bov6.setTag(12);
        bov7.setTag(13);
        bov8.setTag(20);
        bov9.setTag(21);
        bov10.setTag(22);
        bov11.setTag(23);
        bov12.setTag(30);
        bov13.setTag(31);
        bov14.setTag(32);
        bov15.setTag(33);

        List<String > dxds = new ArrayList<>(Arrays.asList("大","小","单","双"));
        for (int i = 0; i<=3 ;i++ ){
            for (int j = 0; j<=3 ;j++){
                Integer tempTag = i * 10 + j;
                BallOmitView tempView = (BallOmitView) findViewWithTag(tempTag);
                tempView.setListener(this);
                String temps = dxds.get(i) + dxds.get(j);
                tempView.setsNum(temps);
            }
        }
    }

    @Override
    public void subOnClick(View view) {
        BallOmitView bov = (BallOmitView) view;
        bov.setSelected( !bov.isSelected());
        Integer tag = (Integer) bov.getTag();
        if (bov.isSelected()){
            selNums.add(tag);
        }else {
            selNums.remove(tag);
        }
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }

    public void clearAllSel(){
        selNums.clear();
        for (int i = 0; i<=3 ;i++ ){
            for (int j = 0; j<=3 ;j++){
                Integer tempTag = i * 10 + j;
                BallOmitView tempView = (BallOmitView) findViewWithTag(tempTag);
                tempView.setSelected(false);
            }
        }
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }
}
