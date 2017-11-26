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

import com.zr.study.disuo_1.Activity.MainActivity;
import com.zr.study.disuo_1.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by 土豆泥 on 2017/8/19.
 */

public class UserExitActivity extends Activity {

    private EditText user;
    private EditText pswd;
    private Button login;
    private TextView forget_pwd;

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


        user = (EditText) findViewById(R.id.user);
        pswd = (EditText) findViewById(R.id.pswd);
        login = (Button) findViewById(R.id.login);
        forget_pwd=(TextView)findViewById(R.id.forget_pwd);


        mytitle = (TextView) findViewById(R.id.mytitle);
        mytitle.setText("设置");

        ibtn_back= (ImageView) findViewById(R.id.ibtn_back);
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserExitActivity.this,MainActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ibtn_close= (ImageView) findViewById(R.id.ibtn_close);
                ibtn_close.setVisibility(View.GONE);

                RequestParams params = new RequestParams("http://192.168.12.1:8080/disuo/servlet/UserLoginServlet");
                params.addQueryStringParameter("user",user.getText().toString());
                params.addQueryStringParameter("pswd",pswd.getText().toString());
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //解析result
                        Log.e("TAG","onSuccess"+result);
                         if(Integer.parseInt(result)==1) {
                            Toast.makeText(UserExitActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(UserExitActivity.this,MainActivity.class));

                        }else if(Integer.parseInt(result)==2){
                            Toast.makeText(UserExitActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }else
                            {
                            Toast.makeText(UserExitActivity.this, "账户不存在", Toast.LENGTH_SHORT).show();
                        }

                    }
                    //请求异常后的回调方法
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("TAG","onError"+ex.getMessage());
                    }
                    //主动调用取消请求的回调方法
                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.e("TAG","onCancelled"+cex.getMessage());
                    }
                    @Override
                    public void onFinished() {
                        Log.e("TAG","onFinished");
                    }
                });

            }
        });
    }





}
