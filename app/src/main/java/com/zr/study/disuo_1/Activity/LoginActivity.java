package com.zr.study.disuo_1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.zr.study.disuo_1.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

public class LoginActivity extends AppCompatActivity {
    private EditText log_phone;
    private EditText log_pswd;
    private Button btn_login;
    private TextView log_forgetpswd;
    private TextView log_reg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initEvent();

    }

    private void initView(){
        log_phone = (EditText) findViewById(R.id.actv_log_phone);
        log_pswd = (EditText) findViewById(R.id.et_log_pswd);
        btn_login = (Button) findViewById(R.id.btn_log_login);
        log_forgetpswd=(TextView)findViewById(R.id.tv_log_forgetpswd);
        log_reg= (TextView) findViewById(R.id.tv_log_reg);
    }

    private void initData(){

    }

    private void initEvent(){
        log_forgetpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });


        log_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,UserRegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                RequestParams params = new RequestParams(LOAD_URL+"/servlet/UserLoginServlet");
                final String user=log_phone.getText().toString();
                final String pswd=log_pswd.getText().toString();

                params.addQueryStringParameter("user",user);
                params.addQueryStringParameter("pswd",pswd);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //解析result
                        Log.e("TAG","onSuccess"+result);
                        if(Integer.parseInt(result)==1) {
                            SharedPreferences preferences=getSharedPreferences("userinfo",Activity.MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("user",user);
                            editor.commit();


                            startActivity(new Intent(LoginActivity.this,MainActivity.class));



                        }else if(Integer.parseInt(result)==2){
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(LoginActivity.this, "账户不存在", Toast.LENGTH_SHORT).show();
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
