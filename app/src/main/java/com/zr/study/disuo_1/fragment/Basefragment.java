package com.zr.study.disuo_1.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * Created by 土豆泥 on 2017/8/2.
 */

public abstract class Basefragment extends Fragment {

    public Activity mActivity;

    // Fragment创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    //Fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initViews();
    }

    //依附的activity创建完成
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    //子类必须实现初始化
    public abstract View initViews();

    //初始化数据
    public void initData() {

    }
}

