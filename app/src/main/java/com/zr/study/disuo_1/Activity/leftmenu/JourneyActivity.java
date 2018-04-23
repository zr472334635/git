package com.zr.study.disuo_1.Activity.leftmenu;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zr.study.disuo_1.R;

import java.util.ArrayList;

public class JourneyActivity extends Activity {
    private ImageView ibtn_back;
    private TextView mytitle;
    private ListView listView;
    private String[] date = {"2017-09-15", "2017-09-15", "2017-09-15", "2017-09-15", "2017-09-15"};
    private String[] time = {"19:38:34", "19:38:34", "19:38:34", "19:38:34", "19:38:34"};
    private int[] money = {0, 1, 2, 3, 4};
    private String[] num = {"30163927", "30163927", "30163927", "30163927", "30163927"};
    private ArrayList<Journey> arrayList;

    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_money;
    private TextView tv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_journey);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        mytitle = (TextView) findViewById(R.id.mytitle);
        mytitle.setText("我的行程");

        ibtn_back = (ImageView) findViewById(R.id.ibtn_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initViews();
        initData();
        adapterManage();
    }

    private void adapterManage() {
        listView.setAdapter(new MyAdapter());
    }

    private void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < date.length; i++) {
            Journey journey = new Journey();
            journey.date = date[i];
            journey.time = time[i];
            journey.money = money[i];
            journey.num = num[i];
            arrayList.add(journey);

        }
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.lv_journey_lv);

    }

    class Journey {
        private String date;
        private String time;
        private int money;
        private String num;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(JourneyActivity.this, R.layout.item_journey, null);

            }
            tv_date = (TextView) convertView.findViewById(R.id.tv_journey_time);
            tv_time = (TextView) convertView.findViewById(R.id.tv_journey_time1);
            tv_money = (TextView) convertView.findViewById(R.id.tv_journey_money);
            tv_num = (TextView) convertView.findViewById(R.id.tv_journey_num);

            tv_date.setText(arrayList.get(position).date);
            tv_time.setText(arrayList.get(position).time);
            tv_money.setText(arrayList.get(position).money + ".00");
            tv_num.setText(arrayList.get(position).num);


            return convertView;

        }
    }
}
