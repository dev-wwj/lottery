package com.scrop.constant;

import android.content.Context;

import com.scrop.mimpl.PropertiesConfig;

/**
 * Created by Scrop on 2017/9/18.
 */

public  class ConstantConfig {
    Context context;

    public static String URL_SERVICE =  null;

    public ConstantConfig(final Context context) {
        this.context = context;

        PropertiesConfig propertiesConfig = new PropertiesConfig() {
            @Override
            protected Context getContext() {
                return context;
            }
            @Override
            protected String getPropertyFileName() {
                return "config.properties";
            }
        };
        this.URL_SERVICE = (propertiesConfig.getString(Constants.HOST ) + propertiesConfig.getString
                (Constants.PATH));
    }


}
