package com.zr.study.disuo_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.RentInfoEntity;

import java.util.List;

public class ViewActivityAdapter extends ArrayAdapter {

    private final int resourceId;


    private TextView tv_vatime;
    private TextView tv_valocknum;
    private TextView tv_vafee;
    private LinearLayout ll_viewact;

    public ViewActivityAdapter(Context context, int textViewResourceId, List<RentInfoEntity> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        RentInfoEntity rentInfoEntity = (RentInfoEntity) getItem(position); // 获取当前项的Fruit实例

        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象

        tv_vatime= view.findViewById(R.id.tv_vatime);
        tv_valocknum= view.findViewById(R.id.tv_valocknum);
        tv_vafee= view.findViewById(R.id.tv_vafee);
        ll_viewact= view.findViewById(R.id.ll_viewact);

        tv_vatime.setText(rentInfoEntity.getBookTime());
        tv_vafee.setText(rentInfoEntity.getFee());
        tv_valocknum.setText(rentInfoEntity.getPLNo());
        return view;
    }
}
