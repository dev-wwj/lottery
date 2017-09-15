package com.scrop.entity;

/**
 * Created by Scrop on 2017/7/24.
 */

public class ImgText {
    private Integer imgId;
    private String text;

    public ImgText(Integer imgId, String text) {
        this.imgId = imgId;
        this.text = text;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
