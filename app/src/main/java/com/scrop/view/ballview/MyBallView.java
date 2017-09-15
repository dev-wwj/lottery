package com.scrop.view.ballview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/28.
 */

public class MyBallView extends TextView {

    Drawable ballColor = getResources().getDrawable(R.drawable.shape_ball_blue);


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public MyBallView(Context context) {
        super(context);
        initSubView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public MyBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setBallColor(Drawable ballColor) {
        this.ballColor = ballColor;
        setBackground(ballColor);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initSubView(Context context) {
        setBackground(ballColor);
        setPadding(8,8,8,8);
        setTextSize(18);
        setGravity(Gravity.CENTER);
        setTextColor(getResources().getColor(R.color.colorWhite));
    }

}
