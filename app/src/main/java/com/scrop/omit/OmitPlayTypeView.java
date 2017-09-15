package com.scrop.omit;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.scrop.adapter.TextAdapter;
import com.scrop.entity.ItemTextBean;
import com.scrop.entity.OmitPlayTypeObj;
import com.scrop.lottery.LotterysManager;
import com.scrop.minterface.listener.DoneActionListener;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.youcaile.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Scrop on 2017/7/26.
 */

public class OmitPlayTypeView extends LinearLayout {
    RecyclerView rv0,rv1,rv2;
    OmitPlayTypeObj opts = LotterysManager.getOpts();

    public OmitPlayTypeView(Context context) {
        super(context);
        View v = LayoutInflater.from(context).inflate(R.layout.layout_omit_playtype, this);
        initSubViews(v);
    }

    DoneActionListener listener;

    public void setListener(DoneActionListener listener) {
        this.listener = listener;
    }

    TextAdapter ta0,ta1,ta2;
    OmitPlayTypeObj.OptBean oob;    //选中的彩票
    OmitPlayTypeObj.OptBean.PlayBean oopb;   //选中的玩法

    private void initSubViews(View view) {
        rv0 = (RecyclerView) view.findViewById(R.id.id_recycler_0);
        rv1 = (RecyclerView) view.findViewById(R.id.id_recycler_1);
        rv2 = (RecyclerView) view.findViewById(R.id.id_recycler_2);
        RectItemDecoration rd = new RectItemDecoration(new Rect(2, 2, 2, 2));
        rv0.addItemDecoration(rd);
        rv1.addItemDecoration(rd);
        rv2.addItemDecoration(rd);
        GridLayoutManager gm = new GridLayoutManager(this.getContext(), 1, 1, false);
        GridLayoutManager gm1 = new GridLayoutManager(this.getContext(), 1, 1, false);
        GridLayoutManager gm2 = new GridLayoutManager(this.getContext(), 1, 1, false);

        rv0.setLayoutManager(gm);
        rv1.setLayoutManager(gm1);
        rv2.setLayoutManager(gm2);

        List<ItemTextBean> gameName = new ArrayList<>();
        for (int i = 0 ;i<opts.getOpt().size() ;i++) {
            OmitPlayTypeObj.OptBean ob = opts.getOpt().get(i);
            if (i == 0){
                gameName.add(new ItemTextBean(ob.getGamename(),true));
                gameid = opts.getOpt().get(0).getGameid();
            }else {
                gameName.add(new ItemTextBean(ob.getGamename(),false));
            }

        }
        ta0 = new TextAdapter(gameName);
        ta1 = new TextAdapter(oopToss(0));
        ta2 = new TextAdapter(oopbToss(0));
        rv0.setAdapter(ta0);
        rv1.setAdapter(ta1);
        rv2.setAdapter(ta2);

        ta0.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                gameid = opts.getOpt().get(position).getGameid();
                ta1.setDates(oopToss(position));
                ta2.setDates(oopbToss(0));
            }
        });
        ta1.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ta2.setDates(oopbToss(position));
                playType =  oob.getPlay().get(position).getPlaytype();
            }
        });
        ta2.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                playName = oopb.getPlayname().get(position);
                if (listener != null){
                    listener.done(true);
                }
            }
        });
    }

    public List<ItemTextBean > oopToss(int position){
        List<ItemTextBean > templs = new ArrayList<>();
        oob = opts.getOpt().get(position);
        for (int i= 0; i < oob.getPlay().size();i++){
            OmitPlayTypeObj.OptBean.PlayBean pb = oob.getPlay().get(i);
            if (i == 0){
                templs.add(new ItemTextBean(pb.getPlaytype(),true));
                playType =  oob.getPlay().get(0).getPlaytype();
            }else {
                templs.add(new ItemTextBean(pb.getPlaytype(),false));
            }
        }
        return templs;
    }

    public List<ItemTextBean > oopbToss(int position){
        List<ItemTextBean > templs = new ArrayList<>();
        oopb = oob.getPlay().get(position);
        for (int i = 0; i< oopb.getPlayname().size(); i++){
            String tempStr = oopb.getPlayname().get(i);
            if (i == 0){
                templs.add(new ItemTextBean(tempStr,true));
                playName = oopb.getPlayname().get(0);
            }else {
                templs.add(new ItemTextBean(tempStr,false));
            }
        }
        return  templs;
    }

    // 选择结果
    String gameid,playType,playName;

    public String getGameid() {
        return gameid;
    }

    public String getPlayType() {
        return playType;
    }

    public String getPlayName() {
        return playName;
    }
}
