package com.scrop.entity;

/**
 * Created by Sc    List<ItemTextBean> dates;
 rop on 2017/7/27.
 */

public class ItemTextBean extends SelectedBean {
    private String text;

    public ItemTextBean(String text ,boolean isSelected) {
        this.text = text;
        setSelected(isSelected);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
