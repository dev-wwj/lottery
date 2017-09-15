package com.scrop.hall.selnumsfragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.scrop.adapter.SelNumsInputAdapter;
import com.scrop.minterface.listener.OnItemClickListener;
import com.scrop.tool.decoration.RectItemDecoration;
import com.scrop.youcaile.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scrop on 2017/9/13.
 */

public class SelNumsInputFragment extends SelNumsFragment implements View.OnClickListener {
    EditText editText;
    Button button;
    RecyclerView recyclerView;

    public SelNumsInputFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_sel_nums_input,container,false);
        initSubViews(view);
        return view;
    }

    private void initSubViews(View view) {
        editText = (EditText) view.findViewById(R.id.id_et);
        button   = (Button) view.findViewById(R.id.id_btn);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_rv);
        button.setOnClickListener(this);

        initRecycelerView();
    }

    @Override
    public void onClick(View v) {

    }

    SelNumsInputAdapter adapter = null;
    List<String > datas = new ArrayList<>();
    private void initRecycelerView() {
        recyclerView.addItemDecoration(new RectItemDecoration(new Rect(2,2,2,2)));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),1,1,false));
        for (int i = 0; i < 10; i++){
            datas.add("item" + String .valueOf(i));
        }
        adapter = new SelNumsInputAdapter(datas, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("position", String.valueOf(position));
                datas.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}

