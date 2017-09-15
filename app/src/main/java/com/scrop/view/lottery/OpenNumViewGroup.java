package com.scrop.view.lottery;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.scrop.view.ballview.BorderBallView;

/**
 * Created by Scrop on 2017/8/31.
 */

public class OpenNumViewGroup extends LinearLayout {
    String gameId;
    String nums;

    public OpenNumViewGroup(Context context) {
        super(context);
    }

    public OpenNumViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void numsWithGameId(String nums, String gameId){
        this.gameId = gameId;
        this.nums = nums;
        if (gameId.equalsIgnoreCase("dlt")){

        }else if (gameId.equalsIgnoreCase("ssq")){

        }else {
            initNumsForNormal();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initNumsForNormal() {
        char[] n = nums.toCharArray() ;
        for ( char c :n){
            addView(normalBallView(c));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private View normalBallView(char ballNum){
        BorderBallView myBallView = new BorderBallView(getContext()) ;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpTopx(getContext(),26),
                dpTopx(getContext(),26));
        lp.setMargins(10,0,0,0);
        myBallView.setLayoutParams(lp);
        myBallView.setText( "0"+ String.valueOf(ballNum));
        return  myBallView;
    }

    public int dpTopx(Context context,int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
                .getDisplayMetrics());
    }

}
