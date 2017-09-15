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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelNumsSSCDwdFragment extends SelNumsFragment implements OnDataChangeListener {

    SelNumsStyle0Layout snLayoutW,snLayoutQ,snLayoutB,snLayoutS,snLayoutG;

    Set<Integer> snW,snQ,snB,snS,snG = null;

    List<Set<Integer>> selNums = new ArrayList<>();

    OnDataChangeListener listener;

    public SelNumsSSCDwdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sel_nums_ssc_normal, container, false);
        initSubViews(view);
        return view;
    }

    private void initSubViews(View view) {
        snLayoutW = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns_w);
        snLayoutQ = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns_q);
        snLayoutB = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns_b);
        snLayoutS = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns_s);
        snLayoutG = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns_g);

        snLayoutW.setListener(this);
        snLayoutQ.setListener(this);
        snLayoutB.setListener(this);
        snLayoutS.setListener(this);
        snLayoutG.setListener(this);

        snW = snLayoutW.getSelNums();
        snQ = snLayoutQ.getSelNums();
        snB = snLayoutB.getSelNums();
        snS = snLayoutS.getSelNums();
        snG = snLayoutG.getSelNums();
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
        Log.e("snW", String.valueOf(snW));
        notiDataChange();
    }

    private void notiDataChange() {
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void setPlayTypeCheckedBean(PlayTypeCheckedBean playTypeCheckedBean) {
        super.setPlayTypeCheckedBean(playTypeCheckedBean);
        updateSNSType(playTypeCheckedBean);
    }

    private void updateSNSType(PlayTypeCheckedBean playTypeCheckedBean) {
        selNums.clear();
    }


    public void subViewAllVisible(){
        snLayoutW.setVisibility(View.VISIBLE);
        snLayoutQ.setVisibility(View.VISIBLE);
        snLayoutB.setVisibility(View.VISIBLE);
        snLayoutS.setVisibility(View.VISIBLE);
        snLayoutG.setVisibility(View.VISIBLE);
    }

    public void clearAllSel(){
        snLayoutW.clearAllSel();
        snLayoutQ.clearAllSel();
        snLayoutB.clearAllSel();
        snLayoutS.clearAllSel();
        snLayoutG.clearAllSel();
    }
}
