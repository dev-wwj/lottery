package com.scrop.hall.selnumsfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.PlayTypeCheckedBean;
import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.view.selnums.SelNumsSumLayout;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/11.
 */

public class SelNumsSSCSumFragment extends SelNumsFragment implements OnDataChangeListener {

    SelNumsSumLayout snsSumLayout;

    public SelNumsSSCSumFragment() {

    }

    @Override
    public void setPlayTypeCheckedBean(PlayTypeCheckedBean playTypeCheckedBean) {
        super.setPlayTypeCheckedBean(playTypeCheckedBean);
        updateSNSType(playTypeCheckedBean);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sel_nums_ssc_sum, container, false);
        initSubViews(view);
        return view;
    }

    private void initSubViews(View v) {
        snsSumLayout = (SelNumsSumLayout) v.findViewById(R.id.id_sns_sum);
        snsSumLayout.setListener(this);
    }

    private void clearAllSel() {
        snsSumLayout.clearAllSel();
    }

    private void updateSNSType(PlayTypeCheckedBean playTypeCheckedBean) {
        try {
            clearAllSel();
            if (playTypeCheckedBean.getPlayName().indexOf("二") == -1){
                snsSumLayout.enableNumsBiggerThanEighteen(true);
            }else {
                snsSumLayout.enableNumsBiggerThanEighteen(false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dataChange(Object data, Object owner) {

    }
}
