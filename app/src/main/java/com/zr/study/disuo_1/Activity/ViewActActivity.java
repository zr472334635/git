package com.zr.study.disuo_1.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zr.study.disuo_1.Adapter.ViewActivityAdapter;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.RentInfoEntity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;


public class ViewActActivity extends AppCompatActivity {

    String[] Phone;
    String[] PLNo;
    String[] RentNo;
    String[] Fee;
    String[] BookTime;
    String[] StartTime;
    String[] EndTime;
    String[] Renewal;

    private List<RentInfoEntity> rentinfo = new ArrayList<RentInfoEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_act);




        SharedPreferences preferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        String phone = preferences.getString("user", "");
        download(phone);
        initView();
//        initData();
//        initEvent();


    }

    private void initView(){
        Phone = new String[100];
        PLNo = new String[100];
        RentNo = new String[100];
        Fee = new String[100];
        BookTime = new String[100];
        StartTime = new String[100];
        EndTime = new String[100];
        Renewal = new String[100];
    }
//
//    private void initData(){}
//
//    private void initEvent(){}


    public void download(String phone) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/ViewActivityServlet");
        params.addQueryStringParameter("user", phone);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(result).getAsJsonArray();
                //解析result
                Log.e("TAG", "onSuccess" + result);
                Gson gson = new Gson();
                ArrayList<RentInfoEntity> list = new ArrayList<>();
                for (JsonElement model : jsonArray) {
                    RentInfoEntity rentInfoEntity = gson.fromJson(model, RentInfoEntity.class);
                    list.add(rentInfoEntity);
                }


                for (int i = 0; i < list.size(); i++) {
                    Phone[i] = list.get(i).getPhone();
                    PLNo[i] = list.get(i).getPLNo();
                    RentNo[i] = list.get(i).getRentNo();
                    Fee[i] = list.get(i).getFee();
                    BookTime[i] = list.get(i).getBookTime();
                    StartTime[i] = list.get(i).getStartTime();
                    EndTime[i] = list.get(i).getEndTime();
                    Renewal[i] = list.get(i).getRenewal();

                    RentInfoEntity a= new RentInfoEntity(Fee[i],StartTime[i],PLNo[i]);
                    rentinfo.add(a);

                }

                ViewActivityAdapter adapter = new ViewActivityAdapter(ViewActActivity.this, R.layout.adapter_activity_view, rentinfo);
                ListView listView = (ListView) findViewById(R.id.list_view);
                listView.setAdapter(adapter);




            }

            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError" + ex.getMessage());
            }

            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished");
            }
        });
    }
}
