package com.zr.study.disuo_1.Activity.leftmenu.function;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.LockPointEntity;
import com.zr.study.disuo_1.bean.UserEntity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

public class RentalActivity extends AppCompatActivity {

    private TextView tv_locknumber;
    private TextView tv_position;
    private Button btn_appoint;
    private Button btn_cancleappoint;
    private Button btn_rent;
    private Button btn_return;

    String PLNo;
    String DamagedCondition;
    String Takenup;
    String ExpiringDate;
    String Profit;
    String Booked;
    String Longitude;
    String Latitude;
    String Position;

    String lockNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

        lockNo =getIntent().getStringExtra("plno");

        initView();
        initData();
        initEvent();
        download(lockNo);

    }

    private void initView(){
        tv_locknumber= (TextView) findViewById(R.id.tv_locknumber);
        tv_position= (TextView) findViewById(R.id.tv_position);
        btn_appoint= (Button) findViewById(R.id.btn_appoint);
        btn_cancleappoint= (Button) findViewById(R.id.btn_cancleappoint);
        btn_rent= (Button) findViewById(R.id.btn_rent);
        btn_return= (Button) findViewById(R.id.btn_return);

    }

    private void initData(){
        tv_locknumber.setText("No."+lockNo);
    }

    private void initEvent(){

    }

    public void download(String plno) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/LockInfoServlet");
        params.addQueryStringParameter("plno", plno);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("TAG", "onSuccess" + result);
                Gson gson = new Gson();
                LockPointEntity lockinfo = gson.fromJson(result, LockPointEntity.class);
                PLNo=lockinfo.getPLNo();
                DamagedCondition=lockinfo.getDamagedCondition();
                Takenup=lockinfo.getTakenup();
                ExpiringDate=lockinfo.getExpiringDate();
                Profit=lockinfo.getProfit();
                Booked=lockinfo.getBooked();
                Longitude=lockinfo.getBooked();
                Latitude=lockinfo.getLatitude();
                Position=lockinfo.getPosition();
                tv_position.setText(Position);
                if(DamagedCondition.equals("damaged")){

                }
                if (Takenup.equals("Yes")){
                    btn_rent.setText("已被借用");
                    btn_rent.setEnabled(false);
                    btn_return.setVisibility(View.INVISIBLE);
                }



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


    public void upload(String value, String name, String phone) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/ChangeInfoServlet");
        params.addQueryStringParameter("value", value);
        params.addQueryStringParameter("name", name);
        params.addQueryStringParameter("phone", phone);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result

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
