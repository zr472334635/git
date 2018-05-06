package com.zr.study.disuo_1.Activity.leftmenu.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zr.study.disuo_1.Activity.LoginActivity;
import com.zr.study.disuo_1.Activity.MainActivity;
import com.zr.study.disuo_1.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by 土豆泥 on 2017/8/19.
 */

public class UserExitActivity extends Activity {

    private Button btn_exit;


    private ImageView ibtn_back;
    private TextView mytitle;
    private Button btn_save;
    private ImageView ibtn_close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_user_exit);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);



        btn_exit = (Button) findViewById(R.id.btn_exit);



        mytitle = (TextView) findViewById(R.id.mytitle);
        mytitle.setText("设置");

        ibtn_back= (ImageView) findViewById(R.id.ibtn_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserExitActivity.this,LoginActivity.class));
                finish();
            }
        });


    }





}
