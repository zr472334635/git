package com.zr.study.disuo_1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zr.study.disuo_1.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

public class UserRegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView ureg_phone;
    private EditText ureg_pswd;
    private EditText ureg_cofpswd;
    private Button ureg_regisiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        initView();
        initData();
        initEvent();

    }



    private void initView(){
        ureg_phone= (AutoCompleteTextView) findViewById(R.id.atcv_ureg_phone);
        ureg_pswd= (EditText) findViewById(R.id.edt_ureg_pswd);
        ureg_cofpswd= (EditText) findViewById(R.id.edt_ureg_cofpswd);
        ureg_regisiter= (Button) findViewById(R.id.btn_ureg_regisiter);
    }

    private void initData(){

    }

    private void initEvent(){
        ureg_regisiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regphone=ureg_phone.getText().toString();
                String regpswd=ureg_pswd.getText().toString();
                String cofpswd=ureg_cofpswd.getText().toString();
                if(regphone.equals(" ") || regpswd.equals(" ") || cofpswd.equals(" ")){
                    Toast.makeText(UserRegisterActivity.this,"请全部填写",Toast.LENGTH_SHORT).show();
                }else{
                    upload(regphone,regpswd,cofpswd);

                }
            }
        });
    }

    public void upload(String regphone,String regpswd,String cofpswd){
        RequestParams params = new RequestParams(LOAD_URL+"/servlet/UserRegisterServlet");
        params.addQueryStringParameter("regphone",regphone);
        params.addQueryStringParameter("regpswd",regpswd);
        params.addQueryStringParameter("cofpswd",cofpswd);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                if(Integer.parseInt(result)==1){
                    Toast.makeText(UserRegisterActivity.this,"账户已注册",Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(result)==2){
                    Toast.makeText(UserRegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserRegisterActivity.this,LoginActivity.class));
                }else if(Integer.parseInt(result)==3){
                    Toast.makeText(UserRegisterActivity.this,"未注册成功",Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(result)==4){
                    Toast.makeText(UserRegisterActivity.this,"两次密码不一样",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserRegisterActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
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
}
