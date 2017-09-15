package com.scrop.hall;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scrop.adapter.SelNumsResAdapter;
import com.scrop.base.BaseActivity;
import com.scrop.minterface.listener.NaviActionListener;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.navi.NaviLayout;
import com.scrop.tool.decoration.DashedItemDecoration;
import com.scrop.youcaile.R;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {
    NaviLayout naviLayout;
    TextView tvIssue,tvTimer;
    Button btnCustomSelNums, btnRandomOne,btnClearList;
    RecyclerView recyclerView;
    EditText etIssue,etTimes;
    TextView tvBetCount;
    Button btnSure;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initSubviews();
    }

    private void initSubviews() {
        naviLayout = (NaviLayout)findViewById(R.id.id_navi);
        naviLayout.setListener(new NaviActionListener() {
            @Override
            public void btnBackClickListener(View view) {
                finish();
            }

            @Override
            public void textTitleClickListener(View view) {

            }
        });

        tvIssue = (TextView) findViewById(R.id.id_tv_issue);
        tvTimer = (TextView) findViewById(R.id.id_tv_timer);

        btnCustomSelNums = (Button) findViewById(R.id.id_btn_custom_sn);
        btnRandomOne = (Button) findViewById(R.id.id_btn_random_one);
        btnClearList = (Button) findViewById(R.id.id_btn_clear_list);

        initRecycler();

        etIssue = (EditText) findViewById(R.id.id_et_issue);
        etTimes = (EditText) findViewById(R.id.id_et_times);

        tvBetCount = (TextView) findViewById(R.id.id_tv_bet_count);
        btnSure = (Button) findViewById(R.id.id_btn_sure);

        btnCustomSelNums.setOnClickListener(this);
        btnRandomOne.setOnClickListener(this);
        btnClearList.setOnClickListener(this);
        btnSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    // recycler ----
    SelNumsResAdapter adapter;
    private void initRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.id_recycler);
        recyclerView.addItemDecoration(new DashedItemDecoration());
        recyclerView.setLayoutManager(new GridLayoutManager(this,1,1,false));
        adapter = new SelNumsResAdapter(null, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }


}
