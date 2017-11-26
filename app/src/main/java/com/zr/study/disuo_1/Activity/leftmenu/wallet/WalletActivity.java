package com.zr.study.disuo_1.Activity.leftmenu.wallet;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.zr.study.disuo_1.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

import static com.zr.study.disuo_1.R.id.mytitle;

public class WalletActivity extends Activity {
    private ImageView ibtn_back;
    private TextView mytitle;
    private TextView money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_wallet);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        mytitle = (TextView) findViewById(R.id.mytitle);
        mytitle.setText("我的钱包");

        ibtn_back= (ImageView) findViewById(R.id.ibtn_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        x.view().inject(this);
        money= (TextView) findViewById(R.id.tv_wallet_deposit_in);
        SharedPreferences settings = getSharedPreferences("settings", 0);
        int moneyy=settings.getInt("money",0);
        money.setText(moneyy+"元");



    }


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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                int money1=b.getInt("money");//str即为回传的值
                money.setText(money1+"元");
                break;
            default:
                break;
        }
    }
}
