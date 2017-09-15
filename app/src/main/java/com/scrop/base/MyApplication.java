package com.scrop.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Scrop on 2017/8/3.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContextObject(){
        return context;
    }
}


