package com.zr.study.disuo_1.Activity.leftmenu.wallet;

/**
 * Created
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zr.study.disuo_1.R;

public class WalletDeposit extends Activity {
    private ImageView ibtn_back;
    private TextView mytitle;
    private ImageView ibtn_close;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private ImageView choose1;
    private ImageView choose2;
    private Button pay;
    private int choose=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_wallet_deposit);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        mytitle = (TextView) findViewById(R.id.mytitle);
        mytitle.setText("押金");

        ibtn_back= (ImageView) findViewById(R.id.ibtn_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ibtn_close= (ImageView) findViewById(R.id.ibtn_close);
        ibtn_close.setVisibility(View.GONE);
        initViews();
        setListeners();
    }

    private void setListeners() {
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose1.setVisibility(View.VISIBLE);
                choose2.setVisibility(View.INVISIBLE);
                choose=99;

            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose2.setVisibility(View.VISIBLE);
                choose1.setVisibility(View.INVISIBLE);
                choose=199;

            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WalletDeposit.this,"choose="+choose,Toast.LENGTH_SHORT).show();
                //Context ctx = WalletDeposit.this;  //获取SharedPreferences对象
                SharedPreferences settings = getSharedPreferences("settings", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("money", choose); //存入int类型数据
                editor.commit();//提交数据
                Bundle bundle=new Bundle();
                int money=choose;
                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle.putInt("money", money);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initViews() {
        rl1= (RelativeLayout) findViewById(R.id.rl1);
        rl2= (RelativeLayout) findViewById(R.id.rl2);
        choose1= (ImageView) findViewById(R.id.choose1);
        choose2= (ImageView) findViewById(R.id.choose2);
        pay= (Button) findViewById(R.id.pay);

    }

}
