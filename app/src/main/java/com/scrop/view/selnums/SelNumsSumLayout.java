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

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Scrop on 2017/9/11.
 */

public class SelNumsSumLayout extends LinearLayout implements SubOnClickListener {
    private BallOmitView bov0,bov1,bov2,bov3,bov4,bov5,bov6,bov7,bov8,bov9,bov10,bov11,bov12,bov13,
    bov14,bov15,bov16,bov17,bov18,bov19,bov20,bov21,bov22,bov23,bov24,bov25,bov26,bov27;

    TextView tvOmit;

    Set<Integer> selNums = new HashSet<>();
    Boolean isHiddenOmit;

    OnDataChangeListener listener;

    public SelNumsSumLayout(Context context) {
        super(context);
        initSubViews(context,null);
    }

    public SelNumsSumLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubViews(context, attrs);
    }

    public OnDataChangeListener getListener() {
        return listener;
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    private void initSubViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_selnums_sum, this, true);
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
        bov16 =(BallOmitView) this.findViewById(R.id.id_bov_16);
        bov17 =(BallOmitView) this.findViewById(R.id.id_bov_17);
        bov18 =(BallOmitView) this.findViewById(R.id.id_bov_18);
        bov19 =(BallOmitView) this.findViewById(R.id.id_bov_19);
        bov20 =(BallOmitView) this.findViewById(R.id.id_bov_20);
        bov21 =(BallOmitView) this.findViewById(R.id.id_bov_21);
        bov22 =(BallOmitView) this.findViewById(R.id.id_bov_22);
        bov23 =(BallOmitView) this.findViewById(R.id.id_bov_23);
        bov24 =(BallOmitView) this.findViewById(R.id.id_bov_24);
        bov25 =(BallOmitView) this.findViewById(R.id.id_bov_25);
        bov26 =(BallOmitView) this.findViewById(R.id.id_bov_26);
        bov27 =(BallOmitView) this.findViewById(R.id.id_bov_27);

        bov0.setTag(0);
        bov1.setTag(1);
        bov2.setTag(2);
        bov3.setTag(3);
        bov4.setTag(4);
        bov5.setTag(5);
        bov6.setTag(6);
        bov7.setTag(7);
        bov8.setTag(8);
        bov9.setTag(9);
        bov10.setTag(10);
        bov11.setTag(11);
        bov12.setTag(12);
        bov13.setTag(13);
        bov14.setTag(14);
        bov15.setTag(15);
        bov16.setTag(16);
        bov17.setTag(17);
        bov18.setTag(18);
        bov19.setTag(19);
        bov20.setTag(20);
        bov21.setTag(21);
        bov22.setTag(22);
        bov23.setTag(23);
        bov24.setTag(24);
        bov25.setTag(25);
        bov26.setTag(26);
        bov27.setTag(27);

        for (int i= 0; i<= 27;i++){
            BallOmitView tempView = (BallOmitView) findViewWithTag(i);
            tempView.setListener(this);
            String sNum = "0";
            if (i < 10){
                sNum = "0" + String.valueOf(i);
            }else {
                sNum =  String.valueOf(i);
            }
            tempView.setsNum(sNum);
        }

    }

    @Override
    public void subOnClick(View view) {
        BallOmitView tempView = (BallOmitView)view;
        tempView.setSelected(!tempView.isSelected());
        Integer tag = (Integer) tempView.getTag();
        if (tempView.isSelected()){
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
        for (int i= 0; i<= 27;i++){
            BallOmitView tempView = (BallOmitView) findViewWithTag(i);
            tempView.setSelected(false);
        }
    }

    public void enableNumsBiggerThanEighteen(Boolean enable){
        for (int i = 19 ;i<=27 ;i++){
            BallOmitView tempView = (BallOmitView) findViewWithTag(i);
            tempView.setBallEnable(enable);
        }
    }

    public boolean isHiddenOmit() {
        return isHiddenOmit;
    }

    public void setHiddenOmit(boolean hiddenOmit) {
        isHiddenOmit = hiddenOmit;
        hiddenOmit(hiddenOmit);
    }

    private void hiddenOmit(boolean hiddenOmit) {

    }
    //隐藏遗漏
    public void hiddenOmit(Boolean isHidden){
        if (isHidden){
            tvOmit.setVisibility(INVISIBLE);
        }else {
            tvOmit.setVisibility(VISIBLE);
        }
        for (int i = 0 ; i <= 27 ;i++){
            BallOmitView bov = (BallOmitView) findViewWithTag(i);
            bov.setHiddenOmit(isHidden);
        }
    }

}
