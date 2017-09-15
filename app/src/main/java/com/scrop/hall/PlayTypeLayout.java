package com.scrop.hall;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.scrop.adapter.TextCenterAdapter;
import com.scrop.adapter.TextFilletAdapter;
import com.scrop.entity.ItemTextBean;
import com.scrop.entity.PlayTypeCheckedBean;
import com.scrop.entity.PlayTypeObj;
import com.scrop.lottery.LotterysManager;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.minterface.listener.PlayTypeCheckedListener;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.youcaile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scrop on 2017/8/24.
 */

public class PlayTypeLayout extends LinearLayout {

    PlayTypeObj pto = null;
    List<String > playArray = new ArrayList<>();

    PlayTypeCheckedListener listener;
    PlayTypeCheckedBean checkedBean;

    int leftDefaultSel, tempLeftSel ,rightDefaultSel = -1;

    RecyclerView recyclerViewLeft,recyclerViewRight;
    public PlayTypeLayout(Context context) {
        super(context);
        initData(context);
        initSubView(context);
    }

    public void setListener(PlayTypeCheckedListener listener) {
        this.listener = listener;
    }

    public void setRightDefaultSel(int rightDefaultSel) {
        this.rightDefaultSel = rightDefaultSel;
    }

    public void setLeftDefaultSel(int leftDefaultSel) {
        this.leftDefaultSel = leftDefaultSel;
    }

    private void initData(Context context) {
       String gameId = ((LotteryPtActivity)context).gameId;
        pto = LotterysManager.getInstance().pto(gameId);
        playArray.clear();
        for (PlayTypeObj.PlayBean bean : pto.getPlay()){
            playArray.add(bean.getPlayfamily());
        }
    }
    TextCenterAdapter textCenterAdapter = null;
    TextFilletAdapter textFilletAdapter = null;

    private void initSubView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_playtype, this, true);
        recyclerViewLeft = (RecyclerView) findViewById(R.id.id_recycler_left);
        recyclerViewLeft.addItemDecoration(new RectItemDecoration(new Rect(0,1,0,0)));
        recyclerViewLeft.setLayoutManager(new GridLayoutManager(context,1,1,false));
        final List<ItemTextBean> dates = new ArrayList<>();
        for (String s:playArray){
            dates.add(new ItemTextBean(s,false));
        }
        textCenterAdapter = new TextCenterAdapter(dates);
        recyclerViewLeft.setAdapter(textCenterAdapter);

        textCenterAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                tempLeftSel = position;
                willPlayFamilySel(position);
            }
        });

        recyclerViewRight = (RecyclerView) findViewById(R.id.id_recycler_right);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

            @Override
            public int getSpanSize(int position) {
                if (textFilletAdapter.getItemViewType(position) == TextFilletAdapter.TYPE_SPACE )
                    return 2;
                return 1;
            }
        });
        List<List> date2 = dataForTextFillet(0);
        recyclerViewRight.setLayoutManager(gridLayoutManager);
        textFilletAdapter = new TextFilletAdapter(date2,context);
        recyclerViewRight.setAdapter(textFilletAdapter);
        textFilletAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                willPlaySel(position);
            }
        });


    }

    private List<List> dataForTextFillet(int position){
        List<ItemTextBean> data = new ArrayList<>();
        PlayTypeObj.PlayBean bean =  pto.getPlay().get(position);
        for (PlayTypeObj.PlayBean.PlaymembersBean pBean:bean.getPlaymembers()){
            data.add(new ItemTextBean(pBean.getPlayname(),false));
        }
        List<List> datas = new ArrayList<>();
        datas.add(data);
        return datas;
    }

    private void willPlayFamilySel(int position) {
        textFilletAdapter.setDates(dataForTextFillet(position));
        textFilletAdapter.notifyDataSetChanged();
    }

    private void willPlaySel(int position) {
        leftDefaultSel = tempLeftSel;
        rightDefaultSel = position;
        checkedFinal();
    }

    public void setDefaultSel(int leftDefaultSel,int rightDefaultSel){
        this.leftDefaultSel = leftDefaultSel;
        this.rightDefaultSel = rightDefaultSel;
        checkedBean = new PlayTypeCheckedBean();
        checkedBean.setGameId(((LotteryPtActivity)getContext()).gameId);
        textCenterAdapter.setDefaultSel(leftDefaultSel);
        textFilletAdapter.setDefaultSel(rightDefaultSel);
        checkedFinal();
    }

    private void checkedFinal(){
        checkedBean.setPlayFimaly(playArray.get(leftDefaultSel));
        checkedBean.setPlayFimalySimplified(checkedBean.getPlayFimaly().substring(0,2));
        PlayTypeObj.PlayBean bean =  pto.getPlay().get(leftDefaultSel);
        checkedBean.setPlayName(bean.getPlaymembers().get(rightDefaultSel).getPlayname());
        checkedBean.setPlayIntro(bean.getPlaymembers().get(rightDefaultSel).getPlaytext());
        if (listener != null){
            listener.playTypeChecked(checkedBean);
        }
    }

}
