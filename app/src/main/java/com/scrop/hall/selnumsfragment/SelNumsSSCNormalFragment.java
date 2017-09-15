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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SelNumsSSCNormalFragment extends SelNumsFragment implements OnDataChangeListener {
    enum SNSType{
        FiveStart,
        BeforeFour,
        BehindFour,
        BeforeThree,
        MiddleThree,
        BehindThree,
        BeforeTwo,
        BehindTwo
    }
    SelNumsStyle0Layout snLayoutW,snLayoutQ,snLayoutB,snLayoutS,snLayoutG;

    Set<Integer> snW,snQ,snB,snS,snG = null;

    List<Set<Integer>> selNums = new ArrayList<>();

    SNSType snsType;

    OnDataChangeListener listener;

    public SelNumsSSCNormalFragment() {
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
        if (playTypeCheckedBean.getPlayName().indexOf("五星") != -1){
            snsType = SNSType.FiveStart;
            selNums.addAll(Arrays.asList(snW,snQ,snB,snS,snG));
        }else if (playTypeCheckedBean.getPlayName().indexOf("前四") != -1){
            snsType = SNSType.BeforeFour;
            selNums.addAll(Arrays.asList(snW,snQ,snB,snS));
        }else if (playTypeCheckedBean.getPlayName().indexOf("后四") != -1){
            snsType = SNSType.BehindFour;
            selNums.addAll(Arrays.asList(snQ,snB,snS,snG));

        }else if (playTypeCheckedBean.getPlayName().indexOf("前三") != -1){
            snsType = SNSType.BeforeThree;
            selNums.addAll(Arrays.asList(snW,snQ,snB));

        }else if (playTypeCheckedBean.getPlayName().indexOf("中三") != -1){
            snsType = SNSType.MiddleThree;
            selNums.addAll(Arrays.asList(snQ,snB,snS));

        }else if (playTypeCheckedBean.getPlayName().indexOf("后三") != -1){
            snsType = SNSType.BehindThree;
            selNums.addAll(Arrays.asList(snB,snS,snG));

        }else if (playTypeCheckedBean.getPlayName().indexOf("前二") != -1){
            snsType = SNSType.BeforeTwo;
            selNums.addAll(Arrays.asList(snW,snQ));
        }else if (playTypeCheckedBean.getPlayName().indexOf("后二") != -1){
            snsType = SNSType.BehindTwo;
            selNums.addAll(Arrays.asList(snS,snG));
        }
        updateViewType(snsType);
    }

    private void updateViewType(SNSType type) {
        try{
            clearAllSel();
            subViewAllVisible();
            switch (type){
                case FiveStart:
                    break;
                case BeforeFour:
                    snLayoutG.setVisibility(View.GONE);
                    break;
                case BehindFour:
                    snLayoutW.setVisibility(View.GONE);
                    break;
                case BeforeThree:
                    snLayoutS.setVisibility(View.GONE);
                    snLayoutG.setVisibility(View.GONE);
                    break;
                case MiddleThree:
                    snLayoutW.setVisibility(View.GONE);
                    snLayoutG.setVisibility(View.GONE);
                    break;
                case BehindThree:
                    snLayoutW.setVisibility(View.GONE);
                    snLayoutQ.setVisibility(View.GONE);
                    break;
                case BeforeTwo:
                    snLayoutB.setVisibility(View.GONE);
                    snLayoutS.setVisibility(View.GONE);
                    snLayoutG.setVisibility(View.GONE);
                    break;
                case BehindTwo:
                    snLayoutW.setVisibility(View.GONE);
                    snLayoutQ.setVisibility(View.GONE);
                    snLayoutB.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
