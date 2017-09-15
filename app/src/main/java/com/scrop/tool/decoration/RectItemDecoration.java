package com.scrop.tool.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Scrop on 2017/7/25.
 * RecyclerView 矩形分割线
 */

public class RectItemDecoration extends RecyclerView.ItemDecoration {

    private Rect rect;

    public RectItemDecoration(Rect rect) {
        this.rect = rect;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(this.rect);
    }
}
