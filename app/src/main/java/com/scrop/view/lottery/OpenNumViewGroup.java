package com.scrop.view.lottery;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.scrop.constant.ConstantsLottery;
import com.scrop.lottery.LotterysManager;
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
        Log.e("gameId",gameId);
        Log.e("nums",nums);
        if (ConstantsLottery.DLT.equalsIgnoreCase(LotterysManager.kindOfLotteryOpenNums(gameId))){
            initNumsForDLT();
        }else if (ConstantsLottery.SSQ.equalsIgnoreCase(LotterysManager.kindOfLotteryOpenNums(gameId))){
            initNumsForSSQ();
        }else {
            initNumsForNormal();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initNumsForNormal() {
        String[] ns = nums.split(",");
//        char[] n = nums.toCharArray() ;
        for ( String  s :ns){
            addView(normalBallView(s));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private View normalBallView(String  ballNum){
        BorderBallView myBallView = new BorderBallView(getContext()) ;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpTopx(getContext(),26),
                dpTopx(getContext(),26));
        lp.setMargins(10,0,0,0);
        myBallView.setLayoutParams(lp);
        myBallView.setText(String.valueOf(ballNum));
        return  myBallView;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initNumsForSSQ() {
        String[] nss = nums.split("\\|");
        String[] nsR = nss[0].split(",");
        String nsb = nss[1];
        for (String  s:nsR){
            BorderBallView myBallView = (BorderBallView) normalBallView(s);
            myBallView.redBall();
            addView(myBallView);
        }
        BorderBallView myBallView = (BorderBallView) normalBallView(nsb);
        myBallView.blueBall();
        addView(myBallView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initNumsForDLT() {
        String[] nss = nums.split("#");
        String[] nsR = nss[0].split(",");
        String[] nsb = nss[1].split(",");
        for (String  s:nsR){
            BorderBallView myBallView = (BorderBallView) normalBallView(s);
            myBallView.redBall();
            addView(myBallView);
        }
        for (String  s:nsb){
            BorderBallView myBallView = (BorderBallView) normalBallView(s);
            myBallView.blueBall();
            addView(myBallView);
        }

    }

    public int dpTopx(Context context,int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
                .getDisplayMetrics());
    }

}
