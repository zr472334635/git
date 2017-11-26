package com.zr.study.disuo_1.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 土豆泥 on 2017/8/2.
 */

public class PreUtils {

    public static final String PREF_NAME="config";

    public static boolean getBoolean(Context ctx,String key,boolean defaultValue){
        SharedPreferences sp= ctx.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        return sp.getBoolean("key",defaultValue);
    }

    public static void setBoolean(Context ctx,String key,boolean defaultValue){
        SharedPreferences sp= ctx.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        sp.edit().putBoolean("key",defaultValue).commit();
    }


}
