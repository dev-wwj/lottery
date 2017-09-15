package com.scrop.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Scrop on 2017/7/19.
 */

public class BaseFragment extends Fragment {

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startActivityNoAnima(Intent intent){
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        animaStarContext();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        animaStarContext();
    }

    public void animaStarContext(){
        ((BaseActivity)getContext()).animaStartActivity();
    }
}
