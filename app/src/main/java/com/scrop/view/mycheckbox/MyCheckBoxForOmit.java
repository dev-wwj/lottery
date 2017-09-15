package com.scrop.view.mycheckbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/27.
 */

public class MyCheckBoxForOmit extends Button {

    public MyCheckBoxForOmit(Context context) {
        super(context);
    }

    public MyCheckBoxForOmit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int verticalCenter = getHeight() / 2;
        int horizontalCenter = getWidth() / 2;
        int circleRadius = 30;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.getResources().getColor(R.color.colorGreen));
        if (isSelected()){
            canvas.drawCircle(horizontalCenter,verticalCenter,circleRadius,paint);
        }
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawCircle(horizontalCenter, verticalCenter,circleRadius + 10,paint);
    }
}
