package com.scrop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scrop.entity.ItemTextBean;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/24.
 */

public class TextCenterViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public TextCenterViewHolder(View itemView) {
        super(itemView);
    }

    public TextView getTextView() {
        return textView;
    }

    public void viewText(String text ){
        textView = (TextView) itemView.findViewById(R.id.id_text);
        textView.setText(text);
    }

    public void viewTextIsSel(ItemTextBean bean){
        viewText(bean.getText());
        textView.setSelected(bean.isSelected());
    }

}
