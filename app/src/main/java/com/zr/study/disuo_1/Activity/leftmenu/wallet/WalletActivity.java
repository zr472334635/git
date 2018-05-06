package com.zr.study.disuo_1.Activity.leftmenu.wallet;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.UserEntity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import static com.zr.study.disuo_1.R.id.mytitle;
import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

public class WalletActivity extends Activity {
    private ImageView ibtn_back;
    private TextView mytitle;
    private TextView tv_wallet_reminder_in;
    private TextView tv_wallet_redpacket_in;
    private TextView tv_wallet_oupon_in;
    private TextView tv_wallet_deposit_in;

    public static WalletActivity _instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_wallet);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        SharedPreferences preferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        String user = preferences.getString("user", "");

        download(user);

        initView();
        initData();
        initEvent();

        x.view().inject(this);

        _instance=this;








    }

    private void initView(){
        mytitle =  findViewById(R.id.mytitle);
        ibtn_back=  findViewById(R.id.ibtn_back);
        tv_wallet_reminder_in= findViewById(R.id.tv_wallet_reminder_in);
        tv_wallet_redpacket_in=  findViewById(R.id.tv_wallet_redpacket_in);
        tv_wallet_oupon_in=  findViewById(R.id.tv_wallet_oupon_in);
        tv_wallet_deposit_in=  findViewById(R.id.tv_wallet_deposit_in);

    }
    private void initData(){
        mytitle.setText("我的钱包");
    }
    private void initEvent(){ibtn_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });}


    @Event(value = {R.id.b_reminder, R.id.b_coupon, R.id.b_cardprotector, R.id.b_redpacket, R.id.b_deposit})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.b_cardprotector:
                startActivity(new Intent(WalletActivity.this, WalletCardProtector.class));
                break;
            case R.id.b_coupon:
                startActivity(new Intent(WalletActivity.this, WalletCoupon.class));
                break;
            case R.id.b_deposit:
                startActivityForResult(new Intent(WalletActivity.this, WalletDeposit.class),0);
                break;
            case R.id.b_redpacket:
                startActivity(new Intent(WalletActivity.this, WalletRedPacket.class));
                break;
            case R.id.b_reminder:
                startActivity(new Intent(WalletActivity.this, WalletReminder.class));
                break;
        }


    }

    public void download(String user) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/UserServlet");
        params.addQueryStringParameter("user", user);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("TAG", "onSuccess" + result);
                Gson gson = new Gson();
                UserEntity userinfo = gson.fromJson(result, UserEntity.class);
                tv_wallet_reminder_in.setText(userinfo.getBalance()+"元");
                tv_wallet_deposit_in.setText(userinfo.getCashPledge()+"元");


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
