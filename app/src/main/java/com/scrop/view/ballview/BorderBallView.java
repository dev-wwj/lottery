package com.scrop.view.ballview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/8/31.
 */

public class BorderBallView extends TextView {

    Drawable border = getResources().getDrawable(R.drawable.shape_ball_border_green);

    public BorderBallView(Context context) {
        super(context);
        initSubView(context);
    }

    public BorderBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubView(context);
    }

    private void initSubView(Context context) {
        setBackgroundDrawable(border);
        setPadding(3,3,3,3);
        setTextSize(16);
        setGravity(Gravity.CENTER);
        setTextColor(getResources().getColor(R.color.colorGreen));
    }

    public void setBallColor(Drawable border) {
        this.border = border;
        this.setBackgroundDrawable(border);
    }

    public void redBall(){
        Drawable borderRed = getResources().getDrawable(R.drawable.shape_ball_red);
        setBackgroundDrawable(borderRed);
        setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void blueBall(){
        Drawable borderRed = getResources().getDrawable(R.drawable.shape_ball_blue);
        setBackgroundDrawable(borderRed);
        setTextColor(getResources().getColor(R.color.colorWhite));
    }
}
