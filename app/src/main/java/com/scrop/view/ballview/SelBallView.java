package com.scrop.view.ballview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;

import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/5.
 */

public class SelBallView extends Button {
    Drawable ballColor = getResources().getDrawable(R.drawable.shape_ball_blue);

    public SelBallView(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public SelBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViews(Context context, AttributeSet attrs) {
        this.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelBallView);
            this.setText(a.getString(R.styleable.SelBallView_snum));
            //reference
            Drawable drawable = a.getDrawable(R.styleable.SelBallView_ballColor);
            this.setBackground(drawable);
        }
    }



}
