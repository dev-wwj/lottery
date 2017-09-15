package com.scrop.omit;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scrop.adapter.OmitAdapter;
import com.scrop.base.BaseFragment;
import com.scrop.lottery.LotterysManager;
import com.scrop.minterface.listener.DoneActionListener;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/21.
 * 遗漏
 */

public class OmitFragment extends BaseFragment implements View.OnClickListener {
    LinearLayout llPlayType;
    ImageView imgPullDown;
    TextView tVGameName,tvPlayType,tvPlayDetail;
    Boolean isPickViewShow = false;
    PopupWindow popupWindow ;
    Button btnGoBuy;
    OmitPlayTypeView optView;

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_omit,container,false);
        initSubViews(v);
        return v;
    }

    private void initSubViews(View v) {
        initRecycler(v);
        initPalyType(v);
        initPopupWindow(v);
    }

    private void initPalyType(View v) {
        llPlayType = (LinearLayout) v.findViewById(R.id.id_ll_pt);
        imgPullDown = (ImageView) v.findViewById(R.id.id_img_pulldown);
        tVGameName = (TextView) v.findViewById(R.id.id_tv_gamename);
        tvPlayType = (TextView) v.findViewById(R.id.id_tv_pt);
        tvPlayDetail = (TextView) v.findViewById(R.id.id_tv_pd);
        llPlayType.setOnClickListener(this);
        btnGoBuy = (Button) v.findViewById(R.id.id_btn_gobuy);
    }

    //弹出
    private void initPopupWindow(View v) {
        popupWindow = new PopupWindow(this.getContext());
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.colorGray));
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight((ViewGroup.LayoutParams.MATCH_PARENT) - 80);
        optView = new OmitPlayTypeView(getContext());
        popupWindow.setContentView(optView);
//        popupWindow.setOutsideTouchable(false);
//        popupWindow.setFocusable(true);
        optView.setListener(new DoneActionListener() {
            @Override
            public void done(Boolean isFinish) {
                animaPullDismiss();
                popupWindow.dismiss();
                isPickViewShow = false;
                finishSelPlayType(optView.getGameid(),optView.getPlayType(),optView.getPlayName());
            }
        });
        finishSelPlayType(optView.getGameid(),optView.getPlayType(),optView.getPlayName());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_ll_pt){
            if (isPickViewShow){
                animaPullDismiss();
                popupWindow.dismiss();
                finishSelPlayType(optView.getGameid(),optView.getPlayType(),optView.getPlayName());
            }else {
                animaPullShow();
                popupWindow.showAsDropDown(llPlayType);
            }
            isPickViewShow = !isPickViewShow;
        }
    }

    public void animaPullShow(){
        Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_pull);
        rotate.setFillAfter(!rotate.getFillAfter());  //动画结束后不恢复
        imgPullDown.startAnimation(rotate);
    }

    public void animaPullDismiss(){
        Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_pull_renew);
        rotate.setFillAfter(!rotate.getFillAfter());
        imgPullDown.startAnimation(rotate);
    }

    //Recycler
    OmitAdapter omitAdapter;
    private void initRecycler(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recycler);
        recyclerView.addItemDecoration(new RectItemDecoration(new Rect(2,2,2,2)));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1,1,false));
        omitAdapter = new OmitAdapter(null,true);
        recyclerView.setAdapter(omitAdapter);
    }

    private void finishSelPlayType(String gameId,String playType,String playName){
        Log.i("101",gameId + playType + playName);
        tVGameName.setText(LotterysManager.gameNameForId(gameId));
        tvPlayType.setText(playType);
        tvPlayDetail.setText(playName);
        if (LotterysManager.isOmitPlayTypeCanBuy(playName)){
            btnGoBuy.setVisibility(View.VISIBLE);
        }else {
            btnGoBuy.setVisibility(View.GONE);
        }
        omitAdapter.setCanBuy(LotterysManager.isOmitPlayTypeCanBuy(playName));
        omitAdapter.notifyDataSetChanged();
    }

    /*
    -------------------分割线----------------------
     */

    private void beginNetworking() {
        System.out.print(optView.getGameid() + "---" + optView.getPlayType() + "-----" + optView
                .getPlayName());
    }
}
