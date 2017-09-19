package com.scrop.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scrop.constant.ConstantsLottery;
import com.scrop.entity.OpenNumResBean;
import com.scrop.lottery.LotterysManager;
import com.scrop.view.ballview.BorderBallView;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/4.
 */

public class OpenNum2ViewHolder extends RecyclerView.ViewHolder {
    TextView tvIssue;
    LinearLayout llOpenNum;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public OpenNum2ViewHolder(View itemView) {
        super(itemView);
        initSubViews(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initSubViews(View itemView) {
        llOpenNum = (LinearLayout) itemView.findViewById(R.id.id_ll_openNum);
        tvIssue = (TextView) itemView.findViewById(R.id.id_tv_issue);
    }


    public void viewValue(OpenNumResBean.ValueBean.ResultBean resultBean, String  gameId) {
        llOpenNum.removeAllViews();

        if (ConstantsLottery.SSQ.equalsIgnoreCase(LotterysManager.kindOfLotteryOpenNums(gameId))){
            //双色球
            loadBallForSSQ(resultBean.getNum());

        }else if(ConstantsLottery.DLT.equalsIgnoreCase(LotterysManager.kindOfLotteryOpenNums(gameId)
        )){
            loadBallForDLT(resultBean.getNum());
        }else {
            loadBallNormal(resultBean.getNum());
        }

        tvIssue.setText(resultBean.getTerm() + " 期");
    }

    private void loadBallNormal(String num) {
        String[] tempOns = num.split(",");
        Context context = llOpenNum.getContext();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpTopx(context,26),
                dpTopx(context,26));
        lp.setMargins(15,0,0,0);
        for (String s: tempOns ){
            BorderBallView ballView  = buildBallView(context,s,0);
            ballView.setLayoutParams(lp);
            llOpenNum.addView(ballView);
        }
    }


    private void loadBallForSSQ(String num) {
        String[] temps = num.split("\\|");
        String[] nsR = temps[0].split(",");
        String nsb = temps[1];
        Context context = llOpenNum.getContext();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpTopx(context,24),
                dpTopx(context,24));
        lp.setMargins(8,0,0,0);
        for (String s : nsR){
            BorderBallView ballView  = buildBallView(context,s,1);
            ballView.setLayoutParams(lp);
            llOpenNum.addView(ballView);
        }
        BorderBallView ballView  = buildBallView(context,nsb,2);
        ballView.setLayoutParams(lp);
        llOpenNum.addView(ballView);
    }

    private void loadBallForDLT(String num) {
        String[] temps = num.split("#");
        String[] nsR = temps[0].split(",");
        String[] nsB = temps[1].split(",");
        Context context = llOpenNum.getContext();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpTopx(context,24),
                dpTopx(context,24));
        lp.setMargins(8,0,0,0);
        for (String s : nsR){
            BorderBallView ballView  = buildBallView(context,s,1);
            ballView.setLayoutParams(lp);
            llOpenNum.addView(ballView);
        }
        for (String s : nsB){
            BorderBallView ballView  = buildBallView(context,s,2);
            ballView.setLayoutParams(lp);
            llOpenNum.addView(ballView);
        }

    }

    private BorderBallView buildBallView(Context context,String num, int type){
        BorderBallView myBallView = new BorderBallView(context) ;
        myBallView.setText(num);
        if (type == 0){
            return myBallView;
        }else if (type == 1){
            myBallView.redBall();
            return myBallView;
        }else if (type == 2){
            myBallView.blueBall();
            return myBallView;
        }
        return null;

    }

    public int dpTopx(Context context, int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources()
                .getDisplayMetrics());
    }

}
