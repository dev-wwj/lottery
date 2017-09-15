package com.scrop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.scrop.minterface.MyAction;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/9/13.
 */

public class SelNumsInputViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    ImageButton imageButton;
    MyAction action;

    public SelNumsInputViewHolder(View itemView) {
        super(itemView);
        initSubviews(itemView);
    }

    public MyAction getAction() {
        return action;
    }

    public void setAction(MyAction action) {
        this.action = action;
    }

    private void initSubviews(final View itemView) {
        textView = (TextView) itemView.findViewById(R.id.id_text);
        imageButton = (ImageButton) itemView.findViewById(R.id.id_ib_del);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null){
                    action.onAction(SelNumsInputViewHolder.this);
                }
            }
        });
    }

}
