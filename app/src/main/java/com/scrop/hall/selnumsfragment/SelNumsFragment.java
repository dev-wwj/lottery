package com.scrop.hall.selnumsfragment;

import android.support.v4.app.Fragment;

import com.scrop.entity.PlayTypeCheckedBean;

/**
 * Created by Scrop on 2017/9/7.
 */

public class SelNumsFragment extends Fragment {
    PlayTypeCheckedBean playTypeCheckedBean;

    public PlayTypeCheckedBean getPlayTypeCheckedBean() {
        return playTypeCheckedBean;
    }

    public void setPlayTypeCheckedBean(PlayTypeCheckedBean playTypeCheckedBean) {
        this.playTypeCheckedBean = playTypeCheckedBean;
    }
}
