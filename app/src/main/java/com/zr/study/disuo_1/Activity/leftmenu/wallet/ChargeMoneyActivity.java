package com.zr.study.disuo_1.Activity.leftmenu.wallet;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zr.study.disuo_1.R;

public class ChargeMoneyActivity extends AppCompatActivity {

    private EditText tv_chargemoney;
    private Button btn_chargemoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_money);
        initView();
        initData();
        initEvent();
    }

    private void initView(){
        tv_chargemoney= (EditText) findViewById(R.id.tv_chargemoney);
        btn_chargemoney= (Button) findViewById(R.id.btn_chargemoney);
    }
    private void initData(){}
    private void initEvent(){
        btn_chargemoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(ChargeMoneyActivity.this);
                dialog.show();
            }
        });
    }
}
