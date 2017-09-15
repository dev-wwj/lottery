package com.scrop.navi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.minterface.listener.NaviAllActionListener;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/21.
 */

public class NaviLayout extends RelativeLayout implements View.OnClickListener {
    public Button btnBack,btnRight;
    public TextView textTitle;
    public NaviActionListener listener = null;
    public NaviAllActionListener allActionListener = null;

    public NaviLayout(Context context) {
        super(context);
        initSubView(context,null);
    }

    public NaviLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubView(context, attrs);
    }

    public void initSubView(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.layout_navi, this, true);
        btnBack = (Button) findViewById(R.id.id_btn_back);
        btnRight = (Button) findViewById(R.id.id_btn_right);
        textTitle = (TextView) findViewById(R.id.id_tv_title);
        btnBack.setOnClickListener(this);
        textTitle.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NaviLayout);
            textTitle.setText(a.getString(R.styleable.NaviLayout_title));
            boolean hidden = a.getBoolean(R.styleable.NaviLayout_backHidden,true);
            if (hidden){
                btnBack.setVisibility(INVISIBLE);
            }else {
                btnBack.setVisibility(VISIBLE);
            }
            boolean rigthBtnHidden = a.getBoolean(R.styleable.NaviLayout_btnRightHidden,true);
            if (rigthBtnHidden){
                btnRight.setVisibility(INVISIBLE);
            }else {
                btnRight.setVisibility(VISIBLE);
                //reference
                Drawable drawable = a.getDrawable(R.styleable.NaviLayout_btnRigthSrc);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                btnRight.setCompoundDrawables(drawable,null,null,null);
            }
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setListener(NaviActionListener listener) {
        this.listener = listener;
    }

    public void setAllActionListener(NaviAllActionListener allActionListener) {
        this.allActionListener = allActionListener;
    }

    public void titleText(String text){
        textTitle.setText(text);
    }
    @Override
    public void onClick(View v) {
        if (listener != null){

            if (v.getId() == R.id.id_btn_back){
                listener.btnBackClickListener(v);
            }
            if (v.getId() == R.id.id_tv_title){
                listener.textTitleClickListener(v);
            }
        }
        if (allActionListener != null){
            if (v.getId() == R.id.id_btn_back){
                allActionListener.btnBackClickListener(v);
            }
            if (v.getId() == R.id.id_tv_title){
                allActionListener.textTitleClickListener(v);
            }
            if (v.getId() == R.id.id_btn_right){
                allActionListener.btnRightClickListener(v);
            }
        }
    }
}











