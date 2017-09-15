package com.scrop.view.bubblecontent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.scrop.menum.BubbleTopAction;
import com.scrop.minterface.listener.BubbleSNTopListener;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/14.
 */

public class BubbleSNTopLayout extends LinearLayout implements View.OnClickListener {

    Button btnGoChart,btnRecentDraw,btnHiddenOmit,btnPlaytypeIntro;

    BubbleSNTopListener listener;

    public BubbleSNTopLayout(Context context) {
        super(context);
        initSubViews(context,null);
    }

    public BubbleSNTopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubViews(context,attrs);
    }

    private void initSubViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_bubble_sn_top,this,true);
        btnGoChart = (Button) findViewById(R.id.id_btn_gochart);
        btnRecentDraw = (Button) findViewById(R.id.id_btn_recent_draw);
        btnHiddenOmit = (Button) findViewById(R.id.id_btn_hidden_omit);
        btnPlaytypeIntro = (Button) findViewById(R.id.id_btn_playType_intro);
        btnGoChart.setOnClickListener(this);
        btnRecentDraw.setOnClickListener(this);
        btnHiddenOmit.setOnClickListener(this);
        btnPlaytypeIntro.setOnClickListener(this);
    }

    public BubbleSNTopListener getListener() {
        return listener;
    }

    public void setListener(BubbleSNTopListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        BubbleTopAction action = BubbleTopAction.ACTION_NONE;
        switch (v.getId()){
            case R.id.id_btn_gochart:
                action = BubbleTopAction.ACTION_GO_CHART;
                break;
            case R.id.id_btn_recent_draw:
                action = BubbleTopAction.ACTION_GO_RECENT_DRAW;
                break;
            case R.id.id_btn_hidden_omit:
                action = BubbleTopAction.ACTION_HIDDEN_OMIT;
                break;
            case R.id.id_btn_playType_intro:
                action = BubbleTopAction.ACTION_GO_PLAYTYPE_INTRO;
                break;
            default:
                action = BubbleTopAction.ACTION_NONE;
                break;
        }
        if (listener != null){
            listener.onAction(action);
        }
    }
}
