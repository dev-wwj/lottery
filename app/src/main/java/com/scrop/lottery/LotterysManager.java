package com.scrop.lottery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.scrop.base.MyApplication;
import com.scrop.entity.LotterysObj;
import com.scrop.entity.OmitPlayTypeObj;
import com.scrop.entity.PlayTypeObj;
import com.scrop.mimpl.MJsonUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Scrop on 2017/7/25.
 * 裁判信息管理
 */

public  class LotterysManager {

    Context context;
    static LotterysObj lotterysObj;
    static OmitPlayTypeObj opts;
    static ArrayList<PlayTypeObj> ptos;
    static List<String> omitCanBuys = Arrays.asList("012路比","012路形态","奇偶比","奇偶形态","大小比","大小形态","单胆",
            "和值","第1位","第2位",
            "第3位","第4位","第5位","和值玩法");


    private static class LotterysManagerHolder{
        private static final LotterysManager INSTANCE = new LotterysManager(MyApplication
                .getContextObject());
    }

    public static final LotterysManager getInstance(){
        return LotterysManagerHolder.INSTANCE;
    }

    public LotterysManager(Context context) {
        this.context = context;
        lotterys();
    }

    // ------------ 彩票信息 ------------
    private void lotterys() {
        String tempStr = readLotterysInfo(context);
        Gson gson = new GsonBuilder().create();
        this.lotterysObj = gson.fromJson(tempStr, LotterysObj.class);
        this.opts = gson.fromJson(readOmitPlayType(context),OmitPlayTypeObj.class);
        Type type = new TypeToken<ArrayList<PlayTypeObj>>(){}.getType();
        this.ptos = gson.fromJson(readwfpt(context),type);
        Log.e("ptos", String.valueOf(this.ptos));
    }

    public String readLotterysInfo(Context context){
        return  MJsonUtil.getJson(context,"lotteryinfo.json");
    }


    public static String gameNameForId(String gameId){
          for (LotterysObj.LotteryBean bean : lotterysObj.getCp()){
              if (bean.getGameId().equalsIgnoreCase(gameId)){
                  return bean.getName();
              }
          }
        return null;
    }

    public LotterysObj getLotterysObj() {
        return lotterysObj;
    }

    public void setLotterysObj(LotterysObj lotterysObj) {
        this.lotterysObj = lotterysObj;
    }

    public LotterysObj.LotteryBean lotteryWithId(String lotteryId){

        List<LotterysObj.LotteryBean> lotteryBeanList = lotterysObj.getCp();
        for (LotterysObj.LotteryBean bean : lotteryBeanList){
            if (lotteryId.equalsIgnoreCase(bean.getGameId())){
                return bean;
            }
        }
        return null;
    }

    //遗漏玩法、
    public String readOmitPlayType(Context context){
        return  MJsonUtil.getJson(context,"omitplaytype.json");
    }

    public static OmitPlayTypeObj getOpts() {
        return opts;
    }

    //遗漏玩法中是否可买
    @NonNull
    public static Boolean isOmitPlayTypeCanBuy(String pTemp){
        if (omitCanBuys.contains(pTemp)){
            return  false;
        }
        return true;
    }

    //玩法
    public String readwfpt(Context context){
        Log.e("wfpt:",MJsonUtil.getJson(context,"wfpt.json"));
        return MJsonUtil.getJson(context,"wfpt.json");
    }

    // 根据彩种选择玩法
    public PlayTypeObj pto(String gameId){
        for (PlayTypeObj obj :ptos){
            if (obj.getGameid().contains(gameId)){
                return obj;
            }
        }
        return null;
    }

}
