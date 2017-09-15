package com.scrop.networking.listener;

/**
 * Created by Scrop on 2017/7/18.
 */

public interface DisposeDownloadListener extends DisposeDataListener{
    public void onProgress(int progress);
}
