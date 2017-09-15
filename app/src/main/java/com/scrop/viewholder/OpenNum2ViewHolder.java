package com.scrop.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scrop.view.ballview.MyBallView;
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
        LinearLayout ll = (LinearLayout) itemView.findViewById(R.id.id_ll_openNum);
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

    public void viewValue(Object o) {

    }

    public int dpTopx(Context context, int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources()
                .getDisplayMetrics());
    }

}
