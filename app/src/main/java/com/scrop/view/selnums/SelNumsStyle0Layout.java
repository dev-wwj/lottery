package com.scrop.view.selnums;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.minterface.listener.SubOnClickListener;
import com.scrop.view.ballview.BallOmitView;
import com.scrop.youcaile.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Scrop on 2017/9/4.
 */

public class SelNumsStyle0Layout extends LinearLayout implements View.OnClickListener,
        SubOnClickListener {

    private RadioGroup radioGroup;
    private RadioButton rbAll,rbBig,rbSmall,rbOdd,rbEven,rbClean; // 全大小奇偶清

    private TextView tvDigit,tvOmit;  //位数、遗漏

    private BallOmitView bov0,bov1,bov2,bov3,bov4,bov5,bov6,bov7,bov8,bov9;

    private boolean isSingleSel;  //是否单选
    private boolean isHiddenOmit;  //是否隐藏遗漏

    Set<Integer> selNums = new HashSet<>();

    OnDataChangeListener listener;

    public SelNumsStyle0Layout(Context context) {
        super(context);
        initSubViews(context,null);
    }

    public SelNumsStyle0Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubViews(context,attrs);
    }

    public boolean isSingleSel() {
        return isSingleSel;
    }

    public void setSingleSel(boolean singleSel) {
        isSingleSel = singleSel;
    }

    public void setSelNums(Set<Integer> selNums) {
        this.selNums = selNums;
        if (listener != null){
            listener.dataChange(selNums, this);
        }
    }

    public Set<Integer> getSelNums() {
        return selNums;
    }

    public boolean isHiddenOmit() {
        return isHiddenOmit;
    }

    public void setHiddenOmit(boolean hiddenOmit) {
        isHiddenOmit = hiddenOmit;
        hiddenOmit(hiddenOmit);
    }

    public OnDataChangeListener getListener() {
        return listener;
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    private void initSubViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_selnums_style_0, this, true);

        radioGroup = (RadioGroup) findViewById(R.id.id_radioGroup);

        rbAll = (RadioButton) findViewById(R.id.id_rb_all);
        rbBig = (RadioButton) findViewById(R.id.id_rb_big);
        rbSmall = (RadioButton) findViewById(R.id.id_rb_small);
        rbOdd = (RadioButton) findViewById(R.id.id_rb_odd);
        rbEven = (RadioButton) findViewById(R.id.id_rb_even);
        rbClean = (RadioButton) findViewById(R.id.id_rb_clean);

        rbAll.setOnClickListener(this);
        rbBig.setOnClickListener(this);
        rbSmall.setOnClickListener(this);
        rbOdd.setOnClickListener(this);
        rbEven.setOnClickListener(this);
        rbClean.setOnClickListener(this);

        tvDigit = (TextView) findViewById(R.id.id_tv_digit);
        tvOmit =  (TextView) findViewById(R.id.id_tv_omit);

        bov0 = (BallOmitView) findViewById(R.id.id_bov_0);
        bov1 = (BallOmitView) findViewById(R.id.id_bov_1);
        bov2 = (BallOmitView) findViewById(R.id.id_bov_2);
        bov3 = (BallOmitView) findViewById(R.id.id_bov_3);
        bov4 = (BallOmitView) findViewById(R.id.id_bov_4);
        bov5 = (BallOmitView) findViewById(R.id.id_bov_5);
        bov6 = (BallOmitView) findViewById(R.id.id_bov_6);
        bov7 = (BallOmitView) findViewById(R.id.id_bov_7);
        bov8 = (BallOmitView) findViewById(R.id.id_bov_8);
        bov9 = (BallOmitView) findViewById(R.id.id_bov_9);

        bov0.setsNum("0");
        bov1.setsNum("1");
        bov2.setsNum("2");
        bov3.setsNum("3");
        bov4.setsNum("4");
        bov5.setsNum("5");
        bov6.setsNum("6");
        bov7.setsNum("7");
        bov8.setsNum("8");
        bov9.setsNum("9");

        bov0.setListener(this);
        bov1.setListener(this);
        bov2.setListener(this);
        bov3.setListener(this);
        bov4.setListener(this);
        bov5.setListener(this);
        bov6.setListener(this);
        bov7.setListener(this);
        bov8.setListener(this);
        bov9.setListener(this);

        bov0.setTag(0);
        bov1.setTag(1);
        bov2.setTag(2);
        bov3.setTag(3);
        bov4.setTag(4);
        bov5.setTag(5);
        bov6.setTag(6);
        bov7.setTag(7);
        bov8.setTag(8);
        bov9.setTag(9);

        if (attrs != null){
            TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.SelNumsStyle0Layout);
            String digit = a.getString(R.styleable.SelNumsStyle0Layout_digit);
            tvDigit.setText(digit);
            Boolean hiddenOmit = a.getBoolean(R.styleable.SelNumsStyle0Layout_hiddenOmit,false);
            hiddenOmit(hiddenOmit);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_rb_all:
                selAll();
                break;
            case R.id.id_rb_big:
                selBig();
                break;
            case R.id.id_rb_small:
                selSmall();
                break;
            case R.id.id_rb_odd:
                selOdd();
                break;
            case R.id.id_rb_even:
                selEven();
                break;
            case R.id.id_rb_clean:
                clearAllSel();
                break;
            default:
                break;
        }
    }

    public void selAll() {
        Log.e("sel","all");
        Set<Integer> nums = new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        selectedNums(nums );
    }

    public void selBig() {
        Log.e("sel","big");
        clear();
        Set<Integer> nums = new HashSet<>(Arrays.asList(5,6,7,8,9));
        selectedNums(nums );
    }

    public void selSmall() {
        clear();
        Set<Integer> nums = new HashSet<>(Arrays.asList(0,1,2,3,4));
        selectedNums(nums );
    }

    public void selOdd() {
        clear();
        Set<Integer> nums = new HashSet<>(Arrays.asList(1,3,5,7,9));
        selectedNums(nums );
    }

    public void selEven() {
        clear();
        Set<Integer> nums = new HashSet<>(Arrays.asList(0,2,4,6,8));
        selectedNums(nums );
    }

    public void clearAllSel(){
        clear();
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }

    private void clear() {
        Log.e("sel","clear");
        selNums.clear();
        bov0.setSelected(false);
        bov1.setSelected(false);
        bov2.setSelected(false);
        bov3.setSelected(false);
        bov4.setSelected(false);
        bov5.setSelected(false);
        bov6.setSelected(false);
        bov7.setSelected(false);
        bov8.setSelected(false);
        bov9.setSelected(false);
    }

    private void selNum(int i) {
        if (isSingleSel()){
            clear();
        }
        BallOmitView tempBov = (BallOmitView) findViewWithTag(i);
        tempBov.setSelected(!tempBov.isSelected());
        addOrRemove(tempBov);
    }

    private void addOrRemove(BallOmitView bov){
        Integer i = (Integer) bov.getTag();
        if (bov.isSelected()){
            selNums.add(i);
        }else {
            selNums.remove(i);
        }
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }

    @Override
    public void subOnClick(View view) {
        Log.e("view", String.valueOf(view));
        switch (view.getId()) {
            case R.id.id_bov_0:
                selNum(0);
                break;
            case R.id.id_bov_1:
                selNum(1);
                break;
            case R.id.id_bov_2:
                selNum(2);
                break;
            case R.id.id_bov_3:
                selNum(3);
                break;
            case R.id.id_bov_4:
                selNum(4);
                break;
            case R.id.id_bov_5:
                selNum(5);
                break;
            case R.id.id_bov_6:
                selNum(6);
                break;
            case R.id.id_bov_7:
                selNum(7);
                break;
            case R.id.id_bov_8:
                selNum(8);
                break;
            case R.id.id_bov_9:
                selNum(9);
                break;
            default:
                break;
        }
    }

    public void doSelectedNum(Integer num){
        BallOmitView bov = (BallOmitView) findViewWithTag (num);
        bov.setSelected(true);
        selNums.add(num);
    }

    // 选择某个号码
    public void selectedNum(Integer num){
        doSelectedNum(num);
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }

    // 选择某些号码
    public void selectedNums(Set<Integer> nums){
        for (Integer i :nums){
            doSelectedNum(i);
        }
        if (listener != null){
            listener.dataChange(selNums,this);
        }
    }

    //遗漏数据
    public void setOmits(List<Integer> omits){
        try {
            for (int i = 0 ; i< 10 ;i++){
                BallOmitView bov = (BallOmitView) findViewWithTag(i);
                bov.setsOmit(String.valueOf(omits.get(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //隐藏遗漏
    public void hiddenOmit(Boolean isHidden){
        if (isHidden){
            tvOmit.setVisibility(INVISIBLE);
        }else {
            tvOmit.setVisibility(VISIBLE);
        }
        for (int i = 0 ; i< 10 ;i++){
            BallOmitView bov = (BallOmitView) findViewWithTag(i);
            bov.setHiddenOmit(isHidden);
        }
    }

    //隐藏大小奇偶清
    public void hiddenDxjoq(Boolean isHidden){
        if (isHidden){
            radioGroup.setVisibility(GONE);
        }else {
            radioGroup.setVisibility(VISIBLE);
        }
    }
}
