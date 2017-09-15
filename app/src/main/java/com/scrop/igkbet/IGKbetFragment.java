package com.scrop.igkbet;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scrop.adapter.OpenNumAdapter;
import com.scrop.base.BaseFragment;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/21.
 * 开奖
 */

public class IGKbetFragment extends BaseFragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_igkbet,container,false);
        initSubViews(v);
        return v;
    }

    private void initSubViews(View v) {
        initRecyclerView(v);
    }

    private void initRecyclerView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.id_recycler);
        recyclerView.addItemDecoration(new RectItemDecoration(new Rect(2,2,2,2)));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1,1,false));

        OpenNumAdapter adapter = new OpenNumAdapter();
        recyclerView.setAdapter(adapter);
    }
}
