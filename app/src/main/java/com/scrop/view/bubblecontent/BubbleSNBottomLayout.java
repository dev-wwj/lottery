package com.scrop.view.bubblecontent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.scrop.menum.BubbleBottomAction;
import com.scrop.minterface.listener.BubbleSNBottomListener;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/14.
 */

public class BubbleSNBottomLayout extends LinearLayout implements View.OnClickListener {

    BubbleSNBottomListener listener;
    public BubbleSNBottomLayout(Context context) {
        super(context);
        initSubViews(context,null);
    }

    public BubbleSNBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubViews(context,attrs);
    }

    public BubbleSNBottomListener getListener() {
        return listener;
    }

    public void setListener(BubbleSNBottomListener listener) {
        this.listener = listener;
    }

    Button btnOne,btnFive,btnTen;

    private void initSubViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_bubble_sn_bottom,this,true);
        btnOne = (Button) findViewById(R.id.id_btn_random_one);
        btnFive = (Button) findViewById(R.id.id_btn_random_five);
        btnTen = (Button) findViewById(R.id.id_btn_random_Ten);

        btnOne.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnTen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        BubbleBottomAction action = BubbleBottomAction.ACTION_NONE;
        switch (v.getId()){
            case R.id.id_btn_random_one:
                action = BubbleBottomAction.ACTION_RANDOM_ONE;
                break;
            case R.id.id_btn_random_five:
                action = BubbleBottomAction.ACTION_RANDOM_FIVE;
                break;
            case R.id.id_btn_random_Ten:
                action = BubbleBottomAction.ACTION_RANDOM_TEN;
                break;
            default:
                break;
        }
        if (listener != null){
            listener.onAction(action);
        }
    }
}
