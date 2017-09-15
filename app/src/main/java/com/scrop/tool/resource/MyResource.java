package com.scrop.tool.resource;

import com.scrop.youcaile.R;

import java.lang.reflect.Field;

/**
 * Created by Scrop on 2017/7/25.
 */

public class MyResource {

    /**
     * 通过反射机制 由图片名获取图片ID
     * @param name
     * @return
     */
    public static int getImageResourceId(String name){
        R.mipmap mipmaps = new  R.mipmap();
        int resId = 0x7f02000b;   //默认的id
        try {
            Field field = R.mipmap.class.getField(name);
            resId = (int)field.get(mipmaps);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return resId;
    }

}
