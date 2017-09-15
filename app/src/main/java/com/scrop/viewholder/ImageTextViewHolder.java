package com.scrop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scrop.entity.ImgText;
import com.scrop.youcaile.R;

/**
 * Created by Scrop on 2017/7/24.
 */

public class ImageTextViewHolder extends RecyclerView.ViewHolder {

    public ImageTextViewHolder(View itemView) {
        super(itemView);
    }

    public void viewValue(ImgText imgText) {
        ImageView iv = (ImageView) itemView.findViewById(R.id.id_imgView);
        TextView tv = (TextView) itemView.findViewById(R.id.id_textView);
        tv.setText(imgText.getText());
        Glide.with(iv.getContext()).load(imgText.getImgId()).into(iv);
    }
}
