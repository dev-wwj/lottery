package com.scrop.tabbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.scrop.base.BaseFragment;
import com.scrop.hall.HallFragment;
import com.scrop.igkbet.IGKbetFragment;
import com.scrop.mine.MineFragment;
import com.scrop.omit.OmitFragment;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/19.
 */

public class TabbarFragment extends BaseFragment implements  CompoundButton
        .OnCheckedChangeListener {

    private RadioButton rb0;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    private HallFragment hallFragment;
    private IGKbetFragment iGKbetFragment;
    private OmitFragment omitFragment;
    private MineFragment mineFragment;

    public TabbarFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabbar,container,false);
        initTabRb(v);
        return v;
    }

    public void initTabRb(View view){

        rb0 = (RadioButton) view.findViewById(R.id.id_rb_0);
        rb1 = (RadioButton) view.findViewById(R.id.id_rb_1);
        rb2 = (RadioButton) view.findViewById(R.id.id_rb_2);
        rb3 = (RadioButton) view.findViewById(R.id.id_rb_3);
        rb0.setOnCheckedChangeListener(this);
        rb1.setOnCheckedChangeListener(this);
        rb2.setOnCheckedChangeListener(this);
        rb3.setOnCheckedChangeListener(this);
        rb0.setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked){
            return;
        }
        switch (buttonView.getId()){
            case R.id.id_rb_0:
                switchView (0);
                break;
            case R.id.id_rb_1:
                switchView (1);
                break;
            case R.id.id_rb_2:
                switchView (2);
                break;
            case R.id.id_rb_3:
                switchView (3);
                break;
        }
    }

    private void switchView(int i) {
        FragmentTransaction transcation = getFragmentManager().beginTransaction();
        hideAllFragment(transcation);
        switch (i){
            case 0:
                if (hallFragment == null){
                    hallFragment = new HallFragment();
                    transcation.add(R.id.id_tb_content,hallFragment);
                }else {
                    transcation.show(hallFragment);
                }
            case 1:
                if (omitFragment == null){
                    omitFragment = new OmitFragment();
                    transcation.add(R.id.id_tb_content,omitFragment);
                }else {
                    transcation.show(omitFragment);
                }
            case 2:
                if (iGKbetFragment == null){
                    iGKbetFragment = new IGKbetFragment();
                    transcation.add(R.id.id_tb_content,iGKbetFragment);
                }else {
                    transcation.show(iGKbetFragment);
                }
            case 3:
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    transcation.add(R.id.id_tb_content,mineFragment);
                }else {
                    transcation.show(mineFragment);
                }
        }
        transcation.commit();
    }

    private void hideAllFragment(FragmentTransaction transcation) {
        if (hallFragment != null){
            transcation.hide(hallFragment);
        }
        if (hallFragment != null){
            transcation.hide(iGKbetFragment);
        }
        if (hallFragment != null){
            transcation.hide(omitFragment);
        }
        if (hallFragment != null){
            transcation.hide(mineFragment);
        }
    }
}


