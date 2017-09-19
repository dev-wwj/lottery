package com.scrop.hall;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scrop.adapter.OpenNum2Adapter;
import com.scrop.base.BaseActivity;
import com.scrop.base.BaseFragment;
import com.scrop.constant.ConstantConfig;
import com.scrop.entity.OpenNumResBean;
import com.scrop.entity.PlayTypeCheckedBean;
import com.scrop.hall.selnumsfragment.SelNumsFragment;
import com.scrop.hall.selnumsfragment.SelNumsInputFragment;
import com.scrop.hall.selnumsfragment.SelNumsSSCBdwFragment;
import com.scrop.hall.selnumsfragment.SelNumsSSCBhFragment;
import com.scrop.hall.selnumsfragment.SelNumsSSCDwdFragment;
import com.scrop.hall.selnumsfragment.SelNumsSSCDxdsFragment;
import com.scrop.hall.selnumsfragment.SelNumsSSCNormalFragment;
import com.scrop.hall.selnumsfragment.SelNumsSSCSumFragment;
import com.scrop.menum.BubbleBottomAction;
import com.scrop.menum.BubbleTopAction;
import com.scrop.minterface.listener.BubbleSNBottomListener;
import com.scrop.minterface.listener.BubbleSNTopListener;
import com.scrop.minterface.listener.NaviAllActionListener;
import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.minterface.listener.PlayTypeCheckedListener;
import com.scrop.navi.NaviLayout;
import com.scrop.networking.CommonOkHttpClient;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.listener.DisposeDataListener;
import com.scrop.networking.request.CommonRequest;
import com.scrop.networking.request.RequestParams;
import com.scrop.tool.FromDpToPx;
import com.scrop.tool.popupwindow.MyPopupWindow;
import com.scrop.view.bubblecontent.BubbleSNBottomLayout;
import com.scrop.view.bubblecontent.BubbleSNTopLayout;
import com.scrop.view.lottery.OpenNumViewGroup;
import com.scrop.youcaile.R;
import com.yuyh.library.BubblePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class LotteryPlayFragment extends BaseFragment implements View.OnTouchListener, OnDataChangeListener, View.OnClickListener {

    NaviLayout naviLayout;
    MyPopupWindow popupWindow ;
    PlayTypeLayout playTypeLayout; // 玩法选择

    TextView tvIssue;
    OpenNumViewGroup openNumViewGroup;
    RecyclerView openNumsRecyclerView;

    Button btnShake ;                // 摇一摇
    TextView tvSnMsg;                // 选号提示
    GridLayout selNumsContentLayout;  // 选号容器

    // 结算部分
    Button btnClear, btnSNRandom, btnSettle ;  //清空、机选、结算

    public LotteryPlayFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lottery_play, container, false);
        initSubViews(view);
        serverData();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initSubViews(View view) {
        naviLayout = (NaviLayout) view.findViewById(R.id.id_navi);
        naviLayout.setAllActionListener(new NaviAllActionListener() {
            @Override
            public void btnRightClickListener(View view) {
                Log.i("玩法选择：" , "走势图");
                topBubbleShow(view);
            }

            @Override
            public void btnBackClickListener(View view) {
                ((BaseActivity) getContext()).finish();
            }

            @Override
            public void textTitleClickListener(View view) {
                playTypeShow();
            }
        });

        initPlayTypeView(this.getContext());

        tvIssue = (TextView) view.findViewById(R.id.id_tv_issue);
        openNumViewGroup = (OpenNumViewGroup) view.findViewById(R.id.id_onvg);
//        openNumViewGroup.numsWithGameId("1234567","null");

        initOpenNums(view);
        initSelNumContent(view);
        initOpenNumsRecyclerView(view);

        btnClear = (Button) view.findViewById(R.id.id_btn_del);
        btnSNRandom = (Button) view.findViewById(R.id.id_sn_random);
        btnSettle = (Button) view.findViewById(R.id.id_btn_settle);

        btnClear.setOnClickListener(this);
        btnSNRandom.setOnClickListener(this);
        btnSettle.setOnClickListener(this);

        playTypeLayout.setDefaultSel(0,0);

        initTopbubble(getContext());

    }


    private void initPlayTypeView(final Context context) {
        popupWindow = new MyPopupWindow(context);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.colorLightGray));
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        playTypeLayout = new PlayTypeLayout(context);
        popupWindow.setContentView(playTypeLayout);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        playTypeLayout.setListener(new PlayTypeCheckedListener() {
            @Override
            public void playTypeChecked(PlayTypeCheckedBean bean) {
                String title = bean.getPlayFimalySimplified() + "-" + bean.getPlayName();
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                Log.e("title",title);
                naviLayout.titleText(title);
                upDateSelNumsContent(context,bean);
            }
        });
    }

    private void playTypeShow() {
        popupWindow.showAsDropDown(naviLayout);
    }

    // 滑动选号界面
    LinearLayout llOpenNum,llDisOpenNums,llSelNumsContent;
    private int lastY,lastTopMargin;
    private void initOpenNums(View view) {
        llOpenNum = (LinearLayout) view.findViewById(R.id.id_ll_openNum);
        llDisOpenNums = (LinearLayout) view.findViewById(R.id.id_ll_dis_openNums);
    }

    RelativeLayout.LayoutParams  normalParams, showParams;
    Boolean isShow = false;
    private void initSelNumContent(View view) {
        llSelNumsContent = (LinearLayout) view.findViewById(R.id.id_ll_selNums_content);
        llSelNumsContent.setOnTouchListener(this);
        normalParams =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout
                .LayoutParams.WRAP_CONTENT);
        normalParams.addRule(RelativeLayout.BELOW,R.id.id_ll_openNum);
        showParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout
                .LayoutParams.WRAP_CONTENT);
        showParams.addRule(RelativeLayout.BELOW,R.id.id_ll_dis_openNums);
        btnShake = (Button) view.findViewById(R.id.id_btn_shake);
        tvSnMsg = (TextView) view.findViewById(R.id.id_tv_sn_msg);
        selNumsContentLayout  = (GridLayout) view.findViewById(R.id.id_selNums_content);
    }

    public RelativeLayout.LayoutParams buildFinalParams(boolean isShow){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams
                .MATCH_PARENT,
                RelativeLayout
                .LayoutParams.MATCH_PARENT);
        if (isShow){
            params.addRule(RelativeLayout.BELOW,R.id.id_ll_openNum);
        }else {
            params.addRule(RelativeLayout.BELOW,R.id.id_ll_dis_openNums);
        }
        return params;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int viewMove = (int) v.getY();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getY();
                lastTopMargin = v.getTop();
                break;
            case MotionEvent.ACTION_MOVE:
//                v.offsetTopAndBottom((int)(event.getY() - lastY));
                ViewGroup.MarginLayoutParams mlp =
                        (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                int offY = y - lastY;
//                Log.e("v.getTop()", String.valueOf(v.getTop() + offY));
//                Log.e("offY", String.valueOf(offY));
//                Log.e("lastTopMargin", String.valueOf(lastTopMargin));

                if (isShow){
                    if (offY < 0){
//                        Log.e("v.getTop()", String.valueOf(v.getTop()));
                        mlp.topMargin =  v.getTop() + offY - lastTopMargin;
                    }
                }else {
                    if (offY > 0){
                        mlp.topMargin = v.getTop() + offY;
                    }
                }
                v.setLayoutParams(mlp);
                break;
            case MotionEvent.ACTION_UP:
                Log.e("viewMove", String.valueOf(viewMove));
                if (isShow == false){
                    if (viewMove > 200){
                        isShow = true;
                        v.setLayoutParams(buildFinalParams(false));
                    }else {
                        v.setLayoutParams(buildFinalParams(true));
                    }
                }else {
                    if ( lastTopMargin - viewMove  > 200){
                        isShow = false;
                        v.setLayoutParams(buildFinalParams(true));
                    }else {
                        v.setLayoutParams(buildFinalParams(false));
                    }

                }
                break;
        }
        return true;
    }

    // 开奖号码（隐藏部分）
    private OpenNum2Adapter on2adapter;
    private void initOpenNumsRecyclerView(View view) {
        openNumsRecyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_opennum);
        openNumsRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1,1,false));
        on2adapter = new OpenNum2Adapter(new ArrayList<OpenNumResBean.ValueBean.ResultBean>(){},
                ((LotteryPtActivity)getActivity() ).gameId);
        openNumsRecyclerView.setAdapter(on2adapter);
    }

    // 选号显示部分 --------------------------------------------------------------------------------------------------------------------------------------------
    SelNumsFragment selNumsFragment;
    SelNumsSSCNormalFragment sscNormalFragment;  // 时时彩 普通
    SelNumsSSCBhFragment  sscBhFragment;         // 时时彩 包号
    SelNumsSSCSumFragment sscSumFragment;        // 时时彩 和值
    SelNumsSSCDxdsFragment sscDxdsFragment;      // 时时彩 大小单双
    SelNumsSSCDwdFragment sscDwdFragment;        // 时时彩 定位胆
    SelNumsSSCBdwFragment sscBdwFragment;        // 时时彩 不定位胆
    SelNumsInputFragment  inputFragment;         // 上传文件
    private void upDateSelNumsContent(Context context, PlayTypeCheckedBean bean) {
        tvSnMsg.setText( bean.getPlayIntro());
        SelNumsFragment tempSelNumsFragment = null;
        FragmentTransaction transaction = ((LotteryPtActivity) context).getSupportFragmentManager()
                .beginTransaction();
        hideAllFragment(transaction);
        if (bean.getPlayFimalySimplified().equalsIgnoreCase("普通")){
            if (sscNormalFragment == null){
                sscNormalFragment = new SelNumsSSCNormalFragment();
                sscNormalFragment.setListener(this);
                transaction.add(R.id.id_selNums_content,sscNormalFragment);
            }else {
                transaction.show(sscNormalFragment);
            }
            tempSelNumsFragment = sscNormalFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("包号")){
            if (sscBhFragment == null){
                sscBhFragment = new SelNumsSSCBhFragment();
                sscBhFragment.setListener(this);
                transaction.add(R.id.id_selNums_content,sscBhFragment);
            }else {
                transaction.show(sscBhFragment);
            }
            tempSelNumsFragment = sscBhFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("和值")){
            if (sscSumFragment == null){
                sscSumFragment = new SelNumsSSCSumFragment();
                transaction.add(R.id.id_selNums_content,sscSumFragment);
            }else {
                transaction.show(sscSumFragment);
            }
            tempSelNumsFragment = sscSumFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("大小")){
            if (sscDxdsFragment == null){
                sscDxdsFragment = new SelNumsSSCDxdsFragment();
                transaction.add(R.id.id_selNums_content,sscDxdsFragment);
            }else {
                transaction.show(sscDxdsFragment);
            }
            tempSelNumsFragment = sscDxdsFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("定位")){
            if (sscDwdFragment == null){
                sscDwdFragment = new SelNumsSSCDwdFragment();
                transaction.add(R.id.id_selNums_content,sscDwdFragment);
            }else {
                transaction.show(sscDwdFragment);
            }
            tempSelNumsFragment = sscDwdFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("不定")){
            if (sscBdwFragment == null){
                sscBdwFragment = new SelNumsSSCBdwFragment();
                transaction.add(R.id.id_selNums_content,sscBdwFragment);
            }else {
                transaction.show(sscBdwFragment);
            }
            tempSelNumsFragment = sscBdwFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("不定")){
            if (sscBdwFragment == null){
                sscBdwFragment = new SelNumsSSCBdwFragment();
                transaction.add(R.id.id_selNums_content,sscBdwFragment);
            }else {
                transaction.show(sscBdwFragment);
            }
            tempSelNumsFragment = sscBdwFragment;
        }else if (bean.getPlayFimalySimplified().equalsIgnoreCase("上传")){
            if (inputFragment == null){
                inputFragment = new SelNumsInputFragment();
                transaction.add(R.id.id_selNums_content,inputFragment);
            }else {
                transaction.show(inputFragment);
            }
            tempSelNumsFragment = inputFragment;
        }else {
            if (selNumsFragment == null){
                selNumsFragment = new SelNumsFragment();
                transaction.add(R.id.id_selNums_content,selNumsFragment);
            }else {
                transaction.show(selNumsFragment);
            }
            tempSelNumsFragment = selNumsFragment;
        }
        transaction.commit();
        tempSelNumsFragment.setPlayTypeCheckedBean(bean);
    }

    private void hideAllFragment(FragmentTransaction transaction ){
        try {
            if (sscNormalFragment != null){
                transaction.hide(sscNormalFragment);
            }
            if (sscBhFragment != null){
                transaction.hide(sscBhFragment);
            }
            if (sscSumFragment != null){
                transaction.hide(sscSumFragment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dataChange(Object data, Object owner) {
        if (owner == sscNormalFragment){
            Log.e("selDate", String.valueOf(data));
        }else if (owner == sscBhFragment){
            Log.e("selDate", String.valueOf(data));
        }else if (owner.toString().equalsIgnoreCase("FilterType")){
            //NoFilter 、FilterAllSame 、FilterSameTwo 、FilterHadSame
            Log.e("selDate", String.valueOf(data));
        }
    }

    // 气泡窗口 -----------
    BubblePopupWindow topBubblePopupWindow,bottomBubblePopupWindow;
    private void initTopbubble(Context context){
        topBubblePopupWindow = new BubblePopupWindow(context);
        BubbleSNTopLayout viewTop = new BubbleSNTopLayout(context);
        topBubblePopupWindow.setBubbleView(viewTop);
        bottomBubblePopupWindow = new BubblePopupWindow(context);
        BubbleSNBottomLayout viewBottom = new BubbleSNBottomLayout(context);
        bottomBubblePopupWindow.setBubbleView(viewBottom);
        viewTop.setListener(new BubbleSNTopListener() {
            @Override
            public void onAction(BubbleTopAction action) {
                switch (action){
                    case ACTION_NONE:
                        break;
                    case ACTION_GO_CHART:
                        //走势图
                        break;
                    case ACTION_GO_RECENT_DRAW:
                        //近期开奖
                        break;
                    case ACTION_HIDDEN_OMIT:
                        //隐藏遗漏
                        break;
                    case ACTION_GO_PLAYTYPE_INTRO:
                        //玩法介绍
                        break;
                    default:
                        break;
                }
            }
        });

        viewBottom.setListener(new BubbleSNBottomListener() {
            @Override
            public void onAction(BubbleBottomAction action) {
                switch (action){
                    case ACTION_NONE:
                        break;
                    case ACTION_RANDOM_ONE:
                        //随机一注
                        Log.e("acion","随机一注");
                        break;
                    case ACTION_RANDOM_FIVE:
                        //随机五注
                        Log.e("acion","随机五注");

                        break;
                    case ACTION_RANDOM_TEN:
                        //随机十注
                        Log.e("acion","随机十注");
                        break;
                    default:
                        break;
                }

            }
        });

    }

    private void topBubbleShow(View view){
        topBubblePopupWindow.show(view, Gravity.BOTTOM, FromDpToPx.toPx(getContext(),100));
    }

    private void bottomBubbleShow(View view){
        bottomBubblePopupWindow.show(view, Gravity.TOP, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_btn_del:
                break;
            case R.id.id_sn_random:
                bottomBubbleShow(v);
                break;
            case R.id.id_btn_settle:
                prepareBalance();
                break;
            default:
                break;
        }
    }

    //结算
    private void prepareBalance() {
        if (true){
            startBalance();
        }else {

        }
    }

    private void startBalance() {
        Intent i = new Intent(getContext(),BalanceActivity.class);
        startActivity(i);
    }


    //  获取数据  --------------------------------------------------------------------------
    private void serverData(){
         getOpenNum();
    }

    private void getOpenNum() {
        Map<String ,Object> mapParams = new HashMap<String ,Object>(){
            {
                put("action","getOpenNum");
                put("gameid",((LotteryPtActivity) getActivity()).gameId);
                put("pageindex",1);
                put("pagesize",10);
            }
        };
        CommonOkHttpClient.post(CommonRequest.createPostRequest(ConstantConfig.URL_SERVICE,new
                RequestParams(mapParams)),new
                DisposeDataHandle(OpenNumResBean.class,new DisposeDataListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onSuccess(Object responseObj) {
//                loadNewses(responseObj);
                openNumsReload(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
            }
        }));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void openNumsReload(Object responseObj) {
        OpenNumResBean bean = (OpenNumResBean) responseObj;
        openNumViewGroup.numsWithGameId(bean.getValue().getResult().get(0).getNum(),(
                (LotteryPtActivity)
                getActivity())
                .gameId);
        tvIssue.setText(bean.getValue().getResult().get(0).getTerm() + " 期");
//        Log.e("openNums", (String) responseObj);
        on2adapter.setOnbs(bean.getValue().getResult());
        on2adapter.notifyDataSetChanged();
    }


}
