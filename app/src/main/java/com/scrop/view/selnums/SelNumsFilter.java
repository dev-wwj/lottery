package com.scrop.view.selnums;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.scrop.minterface.listener.OnDataChangeListener;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/8.
 */



public class SelNumsFilter extends LinearLayout implements CompoundButton.OnCheckedChangeListener {

    enum FilterType{
        NoFilter,
        FilterAllSame,
        FilterSameTwo,
        FilterHadSame
    }

    FilterType ft;

    RadioButton rbNoFilter,rbFilterSameThree,rbFilterSameTwo,rbFilterHadSame;

    OnDataChangeListener listener;

    public SelNumsFilter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSubViews(context, attrs);
    }

    public SelNumsFilter(Context context) {
        super(context);
        initSubViews(context, null);
    }

    public FilterType getFt() {
        return ft;
    }

    public OnDataChangeListener getListener() {
        return listener;
    }

    public void setListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    private void initSubViews(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_selnums_filter,this,true);
        rbNoFilter = (RadioButton) findViewById(R.id.id_rb_nofilter);
        rbFilterSameThree = (RadioButton) findViewById(R.id.id_rb_filter_same_three);
        rbFilterSameTwo = (RadioButton) findViewById(R.id.id_rb_filter_same_two);
        rbFilterHadSame = (RadioButton) findViewById(R.id.id_rb_filter_had_same);

        rbNoFilter.setOnCheckedChangeListener(this);
        rbFilterSameThree.setOnCheckedChangeListener(this);
        rbFilterSameTwo.setOnCheckedChangeListener(this);
        rbFilterHadSame.setOnCheckedChangeListener(this);
        ft = FilterType.NoFilter;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            switch (buttonView.getId()){
                case R.id.id_rb_nofilter:
                    ft = FilterType.NoFilter;
                    break;
                case R.id.id_rb_filter_same_three:
                    ft = FilterType.FilterAllSame;
                    break;
                case R.id.id_rb_filter_same_two:
                    ft = FilterType.FilterSameTwo;
                    break;
                case R.id.id_rb_filter_had_same:
                    ft = FilterType.FilterHadSame;
                    break;
                default:
                    break;
            }
            if (listener != null){
                listener.dataChange(ft, this);
            }
        }
    }

    public void defaultView(){
        rbNoFilter.setChecked(true);
    }
}
