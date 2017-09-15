package com.scrop.hall.selnumsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.entity.PlayTypeCheckedBean;
import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.view.selnums.SelNumsFilter;
import com.scrop.view.selnums.SelNumsStyle0Layout;
import com.scrop.youcaile.R;

import java.util.Set;

/**
 * Created by Scrop on 2017/9/8.
 */

public class SelNumsSSCBhFragment extends SelNumsFragment implements OnDataChangeListener {
    enum SNSBType{
        TYPEFILTER,
        TYPENOFILTER;
    }

    SelNumsStyle0Layout snsLayout;
    SelNumsFilter filter;

    Set<Integer> snBh = null;
    SNSBType snsbType;

    OnDataChangeListener listener;

    public SelNumsSSCBhFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sel_nums_ssc_baohao, container, false);
        initSubViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    private void initSubViews(View view) {
        snsLayout = (SelNumsStyle0Layout) view.findViewById(R.id.id_sns_bh);
        filter = (SelNumsFilter) view.findViewById(R.id.id_snf_bh);
        snsLayout.setListener(this);
        filter.setListener(this);
        snBh = snsLayout.getSelNums();
    }

    /**
     *
     * @param data NoFilter 、FilterAllSame 、FilterSameTwo 、FilterHadSame
     * @param owner
     */
    @Override
    public void dataChange(Object data, Object owner) {
        if (owner == snsLayout){
            if (listener != null){
                listener.dataChange(data,this);
            }
        }else if (owner == filter){
            if (listener != null){
                listener.dataChange(data,"FilterType");
            }
        }

    }

    public void setPlayTypeCheckedBean(PlayTypeCheckedBean playTypeCheckedBean) {
        super.setPlayTypeCheckedBean(playTypeCheckedBean);
        updateSNSType(playTypeCheckedBean);
    }

    private void updateSNSType(PlayTypeCheckedBean playTypeCheckedBean) {
        if (playTypeCheckedBean.getPlayName().indexOf("三直选") != -1){
            snsbType = SNSBType.TYPENOFILTER;
        }else {
            snsbType = SNSBType.TYPEFILTER;
        }
        updateViewType(snsbType);
    }

    private void updateViewType(SNSBType snsbType) {
        switch (snsbType){
            case TYPEFILTER:
                hiddenFilterModule(true);
                break;
            case TYPENOFILTER:
                hiddenFilterModule(false);
                break;
            default:
                break;
        }
    }

    private void hiddenFilterModule(boolean b) {
        try {
            clearAllSel();
            if (b){
                filter.setVisibility(View.INVISIBLE);
            }else{
                filter.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearAllSel() {
        snsLayout.clearAllSel();
        filter.defaultView();
    }

}
