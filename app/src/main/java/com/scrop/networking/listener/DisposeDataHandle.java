package com.scrop.networking.listener;

/**
 * Created by Scrop on 2017/7/17.
 */

public class DisposeDataHandle {

    public DisposeDataListener listener = null;
    public Class<?> mClass = null;
    public String mSource = null;

    public DisposeDataHandle(DisposeDataListener listener){
        this.listener = listener;
    }

    public DisposeDataHandle(Class<?> mClass, DisposeDataListener listener) {
        this.mClass = mClass;
        this.listener = listener;
    }

    public DisposeDataHandle(String mSource, DisposeDataListener listener) {
        this.mSource = mSource;
        this.listener = listener;
    }
}

