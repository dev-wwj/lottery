package com.scrop.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.scrop.base.BaseActivity;
import com.scrop.youcaile.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCardInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvPickBankName;

    OptionsPickerView pickerView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_info);
        initSubviews();
    }

    private void initSubviews() {
        tvPickBankName = (TextView) findViewById(R.id.id_tv_selBank);
        tvPickBankName.setOnClickListener(this);
        initPickView();
    }

    private void initPickView() {
        pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {


            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        }).setSubmitText("确定")
        .setCancelText("取消")
                .setTitleText("开户行选择")
        .setSubCalSize(18)
                .setTitleSize(20).setCyclic(false,false,false).setOutSideCancelable(true).build();
        List<String > banks = new ArrayList<>(Arrays.asList("Tom","Jerry"));
        pickerView.setPicker(banks);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tv_selBank:
                pickViewShow();
                break;
            default:
                break;
        }
    }

    private void pickViewShow() {
        pickerView.show();
    }
}
