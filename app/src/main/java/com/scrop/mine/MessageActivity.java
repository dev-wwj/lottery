package com.scrop.mine;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.scrop.adapter.MessageAdapter;
import com.scrop.base.BaseActivity;
import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.navi.NaviLayout;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/8/10.
 */

public class MessageActivity extends BaseActivity {
    PtrClassicFrameLayout ptrClassicFrameLayout;
    RecyclerView recyclerView;
    NaviLayout naviLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initSubViews();
    }

    private void initSubViews() {
        naviLayout = (NaviLayout) findViewById(R.id.id_navi);
        naviLayout.setListener(new NaviActionListener() {
            @Override
            public void btnBackClickListener(View view) {
                finish();
            }

            @Override
            public void textTitleClickListener(View view) {

            }
        });

        initPtrClassic();
        initRecycler();
    }

    int pageindex = -1;
    private void initPtrClassic() {
        ptrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.id_ptrClassic);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageindex = 1;
            }
        });

        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                pageindex ++;

            }
        });
    }

    MessageAdapter adapter;
    RecyclerAdapterWithHF adapterWithHF;
    private void initRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.id_recycler);
        recyclerView.addItemDecoration(new RectItemDecoration(new Rect(2,2,2,2)));
        recyclerView.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,
                false));
        adapter = new MessageAdapter();
        adapterWithHF = new RecyclerAdapterWithHF(adapter);
        recyclerView.setAdapter(adapterWithHF);
    }

}
