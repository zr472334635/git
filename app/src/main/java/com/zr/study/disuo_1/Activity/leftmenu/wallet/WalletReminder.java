package com.zr.study.disuo_1.Activity.leftmenu.wallet;

/**
 *
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zr.study.disuo_1.R;

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
                
            }
        });
    }
}
