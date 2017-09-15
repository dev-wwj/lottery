package com.scrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/8/11.
 */

public class MenuLayout extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    RadioButton rb0,rb1,rb2;

    OnItemClickListener listener;
    public MenuLayout(Context context) {
        super(context);
        initSubviews(context,null);
    }

    public MenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubviews(context,attrs);
    }

    private void initSubviews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_menu, this, true);
        rb0 = (RadioButton) findViewById(R.id.id_rb_0);
        rb1 = (RadioButton) findViewById(R.id.id_rb_1);
        rb2 = (RadioButton) findViewById(R.id.id_rb_2);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuLayout);
            rb0.setText(a.getString(R.styleable.MenuLayout_item0));
            rb1.setText(a.getString(R.styleable.MenuLayout_item1));
            rb2.setText(a.getString(R.styleable.MenuLayout_item2));
            a.recycle();
        }
        rb0.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked){
            return;
        }
        switch (buttonView.getId()){
            case R.id.id_rb_0:
                onItemChecked(buttonView,0);
                break;
            case R.id.id_rb_1:
                onItemChecked(buttonView,1);
                break;
            case R.id.id_rb_2:
                onItemChecked(buttonView,2);
                break;
            default:
                break;
        }
    }

    private void onItemChecked(CompoundButton buttonView, int i) {
        if (listener != null){
            listener.onItemClick(buttonView,i);
        }
    }

    public void setDefaultChecked(int index){
        if (index == 0){
            rb0.setChecked(true);
        }
        if (index == 1){
            rb0.setChecked(true);
        }
        if (index == 2){
            rb0.setChecked(true);
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
