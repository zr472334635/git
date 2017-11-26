package com.zr.study.disuo_1.XUtils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 土豆泥 on 2017/8/25.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
    }
}
