package com.scrop.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.scrop.view.ballview.MyBallView;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/28.
 */

public class OpenNumViewHolder extends RecyclerView.ViewHolder {
    public OpenNumViewHolder(View itemView) {
        super(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void viewValue(Object obj){
        LinearLayout ll = (LinearLayout) itemView.findViewById(R.id.id_ll_ball);
        ll.removeAllViews();
        for (int i= 0 ;i<5 ;i++){
            MyBallView mbView = new MyBallView(ll.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dpTopx(ll.getContext()
                    ,30),dpTopx(ll.getContext(),30));
            lp.setMargins(15,0,0, 0);
            mbView.setLayoutParams( lp);
            mbView.setText("0" + i);
            ll.addView(mbView);
        }
    }

    public int dpTopx(Context context,int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources()
                .getDisplayMetrics());
    }

}
