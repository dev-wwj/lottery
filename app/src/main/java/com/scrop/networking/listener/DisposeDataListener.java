package com.scrop.networking.listener;

/**
 * Created by Scrop on 2017/7/17.
 */

public interface DisposeDataListener {

    public void onSuccess(Object responseObj);
    public void onFailure(Object responseObj);

}
