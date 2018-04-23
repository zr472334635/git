package com.zr.study.disuo_1.Activity.leftmenu.function;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.LockPointEntity;
import com.zr.study.disuo_1.bean.RentInfoEntity;
import com.zr.study.disuo_1.dialog.ReturnDialog;

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

    String PhoneRent;
    String PLNoRent;
    String RentNo;
    String Fee;
    String BookTime;
    String StartTime;
    String EndTime;
    String Renewal;

    String renttime;

    String lockNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

        SharedPreferences preferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        String phone = preferences.getString("user", "");

        lockNo = getIntent().getStringExtra("plno");

        initView();
        initData();
        initEvent(phone);
        download(lockNo);

    }

    private void initView() {
        tv_locknumber = (TextView) findViewById(R.id.tv_locknumber);
        tv_position = (TextView) findViewById(R.id.tv_position);
//        btn_appoint= (Button) findViewById(R.id.btn_appoint);
//        btn_cancleappoint= (Button) findViewById(R.id.btn_cancleappoint);
        btn_rent = (Button) findViewById(R.id.btn_rent);
        btn_return = (Button) findViewById(R.id.btn_return);

    }

    private void initData() {
        tv_locknumber.setText("No." + lockNo);
    }

    private void initEvent(final String phone) {
        btn_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadrent(PLNo, phone, "Yes");

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadreturn(PLNo, phone, "No", renttime);
            }
        });
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
                PLNo = lockinfo.getPLNo();
                DamagedCondition = lockinfo.getDamagedCondition();
                Takenup = lockinfo.getTakenup();
                ExpiringDate = lockinfo.getExpiringDate();
                Profit = lockinfo.getProfit();
                Booked = lockinfo.getBooked();
                Longitude = lockinfo.getBooked();
                Latitude = lockinfo.getLatitude();
                Position = lockinfo.getPosition();
                tv_position.setText("位置：" + Position);


                btn_appoint.setVisibility(View.INVISIBLE);
                btn_cancleappoint.setVisibility(View.INVISIBLE);

//                if(DamagedCondition.equals("damaged")){
//
//                }
                if (Takenup.equals("Yes")) {
                    btn_rent.setText("已被租用");
                    btn_rent.setVisibility(View.INVISIBLE);
                    btn_return.setVisibility(View.VISIBLE);

                }
                if (Takenup.equals("No")) {
                    btn_rent.setText("租车位");
                    btn_rent.setVisibility(View.VISIBLE);
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


    public void uploadrent(String PLNo, String phone, String confirm) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/RentLockServlet");
        params.addQueryStringParameter("plno", PLNo);
        params.addQueryStringParameter("phone", phone);
        params.addQueryStringParameter("confirm", confirm);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                btn_rent.setVisibility(View.INVISIBLE);
                btn_return.setVisibility(View.VISIBLE);
                renttime = result;

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

    public void uploadreturn(String PLNo, String phone, String confirm, String renttime) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/ReturnLockServlet");
        params.addQueryStringParameter("plno", PLNo);
        params.addQueryStringParameter("phone", phone);
        params.addQueryStringParameter("confirm", confirm);
        params.addQueryStringParameter("renttime", renttime);


        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                btn_rent.setVisibility(View.VISIBLE);
                btn_return.setVisibility(View.INVISIBLE);
                btn_rent.setText("租车位");

                Gson gson = new Gson();
                RentInfoEntity rentinfo = gson.fromJson(result, RentInfoEntity.class);


                PhoneRent = rentinfo.getPhone();
                PLNoRent = rentinfo.getPLNo();
                RentNo = rentinfo.getRentNo();
                Fee = rentinfo.getFee();
                BookTime = rentinfo.getBookTime();
                StartTime = rentinfo.getStartTime();
                EndTime = rentinfo.getEndTime();
                Renewal = rentinfo.getRenewal();

                final ReturnDialog returnDialog = new ReturnDialog(RentalActivity.this);
                returnDialog.setTitle("行程");
                returnDialog.setDireturntitle("行程");
                returnDialog.setDiareturnst(StartTime);
                returnDialog.setDiareturnbet(EndTime);
                returnDialog.setDiareturnbt(BookTime);
                returnDialog.setDiareturnfee(Fee);
                returnDialog.setNoOnclickListener("确定", new ReturnDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(RentalActivity.this, "点击了--取消--按钮", Toast.LENGTH_LONG).show();
                        returnDialog.dismiss();

                    }
                });
                returnDialog.show();

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
