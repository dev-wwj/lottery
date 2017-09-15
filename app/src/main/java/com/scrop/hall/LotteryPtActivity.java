package com.scrop.hall;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;

import com.scrop.base.BaseActivity;
import com.scrop.youcaile.R;

public class LotteryPtActivity extends BaseActivity {
    RelativeLayout relativeLayout;

    LotteryPlayFragment lotteryPlayFragment;

    String gameId = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_pt);
        gameId = getIntent().getStringExtra("gameId");
        relativeLayout = (RelativeLayout) findViewById(R.id.id_content);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (lotteryPlayFragment == null){
            lotteryPlayFragment = new LotteryPlayFragment();
        }
        transaction.add(R.id.id_content,lotteryPlayFragment).commit();
    }
}
