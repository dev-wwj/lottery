package com.scrop.hall.selnumsfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.PlayTypeCheckedBean;
import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.view.selnums.SelNumsStyle0Layout;
import com.scrop.youcaile.R;

import java.util.HashSet;
import java.util.Set;

public class SelNumsSSCBdwFragment extends SelNumsFragment implements OnDataChangeListener {

    SelNumsStyle0Layout snLayout;

    Set<Integer> sns = new HashSet<>();

    OnDataChangeListener listener;

    public SelNumsSSCBdwFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sel_nums_ssc_bdw, container, false);
        initSubViews(view);
        return view;
    }

    private void initSubViews(View view) {
        snLayout = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns);
        snLayout.setListener(this);
        sns = snLayout.getSelNums();
    }

    public OnDataChangeListener getListener() {
        return listener;
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (playTypeCheckedBean != null){
            setPlayTypeCheckedBean(playTypeCheckedBean);
        }
    }

    @Override
    public void dataChange(Object data, Object owner) {
        Log.e("snW", String.valueOf(sns));
        notiDataChange();
    }

    private void notiDataChange() {
        if (listener != null){
            listener.dataChange(sns,this);
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void setPlayTypeCheckedBean(PlayTypeCheckedBean playTypeCheckedBean) {
        super.setPlayTypeCheckedBean(playTypeCheckedBean);
        updateSNSType(playTypeCheckedBean);
    }

    private void updateSNSType(PlayTypeCheckedBean playTypeCheckedBean) {
        sns.clear();
    }

    public void subViewAllVisible(){
        snLayout.setVisibility(View.VISIBLE);
    }

    public void clearAllSel(){
        snLayout.clearAllSel();
    }
}
