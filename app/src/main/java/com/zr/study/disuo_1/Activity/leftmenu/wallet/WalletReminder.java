package com.zr.study.disuo_1.Activity.leftmenu.wallet;

/**
 *
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.dialog.ChargeDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

public class WalletReminder extends Activity {
    private ImageView ibtn_back;
    private TextView mytitle;
    private ImageView ibtn_close;
    private Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_wallet_reminder);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);


        initView();
        initData();
        initEvent();

    }

    private void initView() {
        mytitle = findViewById(R.id.mytitle);
        ibtn_back = findViewById(R.id.ibtn_back);
        ibtn_close = findViewById(R.id.ibtn_close);
        btn_pay = findViewById(R.id.btn_pay);
    }

    private void initData() {
        mytitle.setText("余额");
    }

    private void initEvent() {
        SharedPreferences preferences = getSharedPreferences("userinfo", Activity.MODE_PRIVATE);
        final String user = preferences.getString("user", "");
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ibtn_close.setVisibility(View.GONE);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                final ChargeDialog chargeDialog = new ChargeDialog(WalletReminder.this);
                chargeDialog.setTitle("充值");

                chargeDialog.setYesOnclickListener("确定", new ChargeDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {


                        EditText et=chargeDialog.findViewById(R.id.edt_chargemoney);
                        String money = et.getText().toString();
                        upload(user, money);
                        chargeDialog.dismiss();

                        Toast.makeText(WalletReminder.this, "充值成功", Toast.LENGTH_LONG).show();

                    }
                });
                chargeDialog.setNoOnclickListener("取消", new ChargeDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(WalletReminder.this, "点击了--取消--按钮", Toast.LENGTH_LONG).show();
                        chargeDialog.dismiss();

                    }
                });


                chargeDialog.show();
            }

        });
    }

    public void upload(String Phone, String Money) {
        RequestParams params = new RequestParams(LOAD_URL + "/servlet/ChargeMoneyServlet");
        params.addQueryStringParameter("phone", Phone);
        params.addQueryStringParameter("money", Money);


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
