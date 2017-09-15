package com.scrop.view.ballview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scrop.minterface.listener.SubOnClickListener;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/5.
 */

public class BallOmitView extends LinearLayout implements View.OnClickListener {

    private boolean isSelected;

    private SelBallView sbv;
    private TextView tvOmit;

    private String sNum;  // 号码
    private String sOmit; // 遗漏
    private boolean isHiddenOmit; // 隐藏遗漏

    private Boolean isBallEnable;

    SubOnClickListener listener;

    public BallOmitView(Context context) {
        super(context);
        initSubviews(context,null);
    }

    public BallOmitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubviews(context,attrs);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        sbv.setSelected(selected);
    }

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
        sbv.setText(sNum);
    }

    public String getsOmit() {
        return sOmit;
    }

    public void setsOmit(String sOmit) {
        this.sOmit = sOmit;
        tvOmit.setText(sOmit);
    }

    public boolean isHiddenOmit() {
        return isHiddenOmit;
    }

    public void setHiddenOmit(boolean hiddenOmit) {
        isHiddenOmit = hiddenOmit;
        if (hiddenOmit){
            tvOmit.setVisibility(GONE);
        }else {
            tvOmit.setVisibility(VISIBLE);
        }
    }

    public Boolean getBallEnable() {
        return isBallEnable;
    }

    // 按钮不可点击
    public void setBallEnable(Boolean ballEnable) {
        isBallEnable = ballEnable;
        sbv.setEnabled(ballEnable);
    }

    public SubOnClickListener getListener() {
        return listener;
    }

    public void setListener(SubOnClickListener listener) {
        this.listener = listener;
    }

    private void initSubviews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_ballomit, this, true);
        sbv = (SelBallView) findViewById(R.id.id_sbv);
        tvOmit = (TextView) findViewById(R.id.id_tv_omit);
        sbv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("onClick","click");
        Log.e("onClick", String.valueOf(listener));

        if (listener != null){
            listener.subOnClick(this);
        }
    }

}
