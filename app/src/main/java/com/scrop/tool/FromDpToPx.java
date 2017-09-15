package com.scrop.tool;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Scrop on 2017/9/14.
 */

public class FromDpToPx {
    Context context;

    public static int toPx(Context context, int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources()
                .getDisplayMetrics());
    }
}
