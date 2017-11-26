package com.zr.study.disuo_1.fragment;

import android.view.View;

import com.zr.study.disuo_1.R;


/**
 * Created by 土豆泥 on 2017/8/2.
 */

public class ContentFragment extends Basefragment {


    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);

        //rgGroup= (RadioGroup) view.findViewById(R.id.rg_group);
//        ViewUtils.inject(this, view);//注入view和事件
        return view;
    }

    @Override
    public void initData() {

//        }

    }
}