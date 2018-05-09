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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewActivityAdapter extends ArrayAdapter {

    private final int resourceId;


    private TextView tv_start_time;
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

        tv_start_time = view.findViewById(R.id.tv_start_time);
        tv_valocknum = view.findViewById(R.id.tv_valocknum);
        tv_vafee = view.findViewById(R.id.tv_vafee);
        ll_viewact = view.findViewById(R.id.ll_viewact);

        String str = rentInfoEntity.getStartTime();

        try {

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sd.parse(str);
            String time = sd.format(d);

            tv_start_time.setText(time);
            tv_vafee.setText("￥" + rentInfoEntity.getFee());
            tv_valocknum.setText("地锁号：" + rentInfoEntity.getPLNo());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }
}
