package com.scrop.hall.selnumsfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.PlayTypeCheckedBean;
import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.view.selnums.SelNumsDxdsLayout;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/12.
 */

public class SelNumsSSCDxdsFragment extends SelNumsFragment implements OnDataChangeListener {

    SelNumsDxdsLayout dxdsLayout;

    public SelNumsSSCDxdsFragment() {
    }

    @Override
    public void setPlayTypeCheckedBean(PlayTypeCheckedBean playTypeCheckedBean) {
        super.setPlayTypeCheckedBean(playTypeCheckedBean);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_nums_ssc_dxds,container,false);
        initSubViews(view);
        return view;
    }

    private void initSubViews(View view) {
        dxdsLayout = (SelNumsDxdsLayout) view.findViewById(R.id.id_sns_dxds);
        dxdsLayout.setListener(this);
    }

    @Override
    public void dataChange(Object data, Object owner) {
        Log.e("dxds",data.toString());
    }
}
