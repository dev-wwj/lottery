package com.scrop.hall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.adapter.ImgTextAdapter;
import com.scrop.base.BaseActivity;
import com.scrop.base.BaseFragment;
import com.scrop.constant.ConstantConfig;
import com.scrop.constant.Constants;
import com.scrop.entity.ImgText;
import com.scrop.entity.IndexPicResBean;
import com.scrop.entity.LotterysObj;
import com.scrop.entity.NewsResBean;
import com.scrop.lottery.LotterysManager;
import com.scrop.mimpl.PropertiesConfig;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.networking.CommonOkHttpClient;
import com.scrop.networking.listener.DisposeDataHandle;
import com.scrop.networking.listener.DisposeDataListener;
import com.scrop.networking.request.CommonRequest;
import com.scrop.networking.request.RequestParams;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.tool.imageloader.GlideImageLoader;
import com.scrop.tool.resource.MyResource;
import com.scrop.view.autopollrecycler.AutoPollAdapter;
import com.scrop.view.autopollrecycler.AutoPollRecyclerView;
import com.scrop.web.NotiWebActivity;
import com.scrop.youcaile.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Scrop on 2017/7/21.
 */

public class HallFragment extends BaseFragment {
    private AutoPollRecyclerView recyclerViewNotify;
    private RecyclerView recyclerView;
    private Banner banner;
    private List<LotterysObj.LotteryBean> lotterys;
    public HallFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hall,container,false);
        initSubView(v);
        return v;
    }

    private void initSubView(View v) {
        initBanner(v);
        initRecyclerView(v);
        indexPic();
        selNews();
    }

    private void initRecyclerViewNotify(List<String > newsTitle) {
        recyclerViewNotify = (AutoPollRecyclerView) this.getView().findViewById(R.id
                .id_recycler_notify);
        AutoPollAdapter adapter = new AutoPollAdapter(this.getContext(),newsTitle);
        recyclerViewNotify.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL,false));
        recyclerViewNotify.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                loadNotiDetail(position);
            }
        });
        if (true)
            recyclerViewNotify.start();
    }

    //-----通知栏选中-----
//    @Override
//    public void onItemClick(View view, int position) {
////        Toast.makeText(this.getContext(),"---" + position,Toast.LENGTH_SHORT).show();
//    }

    //-----BannerView----
    private void initBanner(View v) {
        List<String> imageUrls = new ArrayList<>();
        banner = (Banner) v.findViewById(R.id.id_banner);
    }

    private void initRecyclerView(View v){
        recyclerView = (RecyclerView) v.findViewById(R.id.id_recycler);
        recyclerView.addItemDecoration(new RectItemDecoration(new Rect(2,2,2,2)));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2,1,false));
//        lotterys = (new LotterysManager(getContext())).getLotterysObj().getCp();
        lotterys = LotterysManager.getInstance().getLotterysObj().getCp();
        List<ImgText> dates = new ArrayList<>();
        for (LotterysObj.LotteryBean bean : lotterys){
            Integer sId = MyResource.getImageResourceId(bean.getLogo());
            dates.add(new ImgText(sId,bean.getName()));
        }
        ImgTextAdapter adapter = new ImgTextAdapter(dates);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                loadLotteryPlay(position);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void indexPic(){
        Map<String ,Object> mapParams = new HashMap<>();
        mapParams.put("action","mobile_indexpic");
        BaseActivity activity = (BaseActivity) getActivity();
        CommonOkHttpClient.post(CommonRequest.createPostRequest(ConstantConfig.URL_SERVICE,new
                RequestParams(mapParams)),new
                DisposeDataHandle(IndexPicResBean.class,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                loadIndexPic(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
            }
        }));

    }

    private void loadIndexPic(Object responseObj) {
        IndexPicResBean bean = (IndexPicResBean) responseObj;
        String indexPics = bean.getValue().getIndexpic();
        //分割支付串
        String[] pics = indexPics.split("\\|");
        List<String> imgUrls = new ArrayList<>();
        BaseActivity activity = (BaseActivity) getActivity();
        String imgLoc = new PropertiesConfig(){
            @Override
            protected Context getContext() {
                return HallFragment.this.getContext();
            }

            @Override
            protected String getPropertyFileName() {
                return "config.properties";
            }
        }.getString(Constants.HOST );
        for (String s : pics){
            Log.e("split",s);
            imgUrls.add(imgLoc + "/static/upload/indexpic/" + s);
        }
        banner.setImages(imgUrls).setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(Transformer.Default).setImageLoader(new GlideImageLoader()).start();
    }

    private void selNews(){
        Map<String ,Object> mapParams = new HashMap<>();
        mapParams.put("action","selNews");
        mapParams.put("pageindex",1);
        mapParams.put("pagesize",15);
        BaseActivity activity = (BaseActivity) getActivity();
        CommonOkHttpClient.post(CommonRequest.createPostRequest(ConstantConfig.URL_SERVICE,new
                RequestParams(mapParams)),new
                DisposeDataHandle(NewsResBean.class,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                loadNewses(responseObj);
            }

            @Override
            public void onFailure(Object responseObj) {
                Log.i("onFailure" ,responseObj.toString());
            }
        }));
    }

    List<NewsResBean.NewsValue.NewsBean> newsBeanes = null;

    private void loadNewses(Object responseObj) {
        newsBeanes =( (NewsResBean) responseObj).getValue()
                .getResult();
        List<String > newsTitle = new ArrayList<>();
        for (NewsResBean.NewsValue.NewsBean bean :newsBeanes){
            newsTitle.add(bean.getNews_title());
        }
        initRecyclerViewNotify(newsTitle);
    }

    //加载通知栏详情页面
    private void loadNotiDetail(int position) {
        String newsId = newsBeanes.get(position).getNews_id();
        String service_host = new PropertiesConfig(){
            @Override
            protected Context getContext() {
                return HallFragment.this.getContext();
            }
            @Override
            protected String getPropertyFileName() {
                return "config.properties";
            }
        }.getString(Constants.HOST );
        String url =  service_host + "/mb/news/" + newsId + ".html?isapp=1";
        webLoad(this.getContext(),url);
    }

    private void webLoad(Context context ,String url){
        Intent i = new Intent(context , NotiWebActivity.class);
        i.putExtra("url",url);
        startActivity(i);
    }

    //加载彩票玩法页面
    private void loadLotteryPlay(int position) {
        LotterysObj.LotteryBean bean = lotterys.get(position);
        Intent i = new Intent(this.getContext(),LotteryPtActivity.class);
        i.putExtra("gameId",bean.getGameId());
        startActivity(i);
    }
}
