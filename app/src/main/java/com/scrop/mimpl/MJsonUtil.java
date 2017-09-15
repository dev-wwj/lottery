package com.scrop.mimpl;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Scrop on 2017/7/25.
 */

public class MJsonUtil {

    public static String getJson(Context context,String fileName){
        StringBuilder sb = new StringBuilder();
        AssetManager am = context.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())){
                sb.append(next);
            }
        }catch( IOException e){
            e.printStackTrace();
            sb.delete(0,sb.length());
        }
        return sb.toString().trim();
    }
}
