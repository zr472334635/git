package com.zr.study.disuo_1.Activity.leftmenu.user;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zr.study.disuo_1.Activity.MainActivity;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.bean.UserEntity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Calendar;

import static com.zr.study.disuo_1.global.GlobalContants.LOAD_URL;

public class UserActivity extends Activity {

    private TextView startDateTime;

    private ImageView ibtn_back;
    private TextView mytitle;
    private ImageView ibtn_set;

    private LinearLayout llusernickname;
    private LinearLayout llusersex;
    private LinearLayout lluserbirthday;
    private LinearLayout lluseridentity;
    private LinearLayout llusertele;
    private LinearLayout lluserwechat;
    private LinearLayout lluserqq;
    private TextView user_nickname_in;
    private TextView user_sex_in;
    private TextView user_birth_in;
    private TextView user_identity_in;
    private TextView user_phone_in;
    private TextView user_weixin_in;
    private TextView user_qq_in;
    private String titleString;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);


        SharedPreferences preferences=getSharedPreferences("userinfo",Activity.MODE_PRIVATE);
        String user=preferences.getString("user","");
        String phone=preferences.getString("user","");

        upload(user);

        initViews();
        initData();
        initEvent();

        mytitle.setText("个人信息");




        setListeners(phone);

    }



    private void setListeners(final String phone) {
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ll_user_nickname:
                        final EditText editText=new EditText(UserActivity.this);
                        editText.setText(user_nickname_in.getText());
                        new AlertDialog.Builder(UserActivity.this).setTitle("修改昵称").setIcon(android.R.drawable.ic_dialog_info).setView(
                                editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name= String.valueOf(editText.getText());
                                upload(name,"name",phone);
                                user_nickname_in.setText(name);

                            }
                        }).setNegativeButton("取消", null).show();
                        break;

                    case R.id.ll_user_sex:
                        final String[] strings={ "男", "女" };
                        new AlertDialog.Builder(UserActivity.this).setTitle("修改性别").setIcon(android.R.drawable.ic_dialog_info).setSingleChoiceItems(
                        strings, 0, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        user_sex_in.setText(strings[which]);
                                        upload(strings[which],"sex",phone);

                                        dialog.dismiss();}
                                }).setNegativeButton("取消", null).show();
                        break;
                    case  R.id.ll_user_birthday:
                        final Calendar ca = Calendar.getInstance();
                        final int iYear = ca.get(Calendar.YEAR);
                        final int iMonth = ca.get(Calendar.MONTH);
                        final int iDay = ca.get(Calendar.DAY_OF_MONTH);
                        final DatePickerDialog mDialog = new DatePickerDialog(UserActivity.this, null,
                                iYear, iMonth, iDay);
                        mDialog.setCancelable(true);
                        mDialog.setCanceledOnTouchOutside(true);
                        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatePicker datePicker = mDialog.getDatePicker();
                                        int year = datePicker.getYear();
                                        int month = datePicker.getMonth();
                                        int day = datePicker.getDayOfMonth();
                                        user_birth_in.setText(new StringBuffer().append(year).append("-").append(month+1).append("-").append(day).append(" "));
                                        String date= (String) user_birth_in.getText();
                                        upload(date,"birthday",phone);
                                    }
                                });
                        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d("Picker", "Cancel!");
                                    }
                                });
                        mDialog.show();
                        break;
                    case R.id.ll_user_identity:
                        new AlertDialog.Builder(UserActivity.this).setTitle("身份列表").setItems(
                        new String[] { "未认证用户", "认证用户" }, null).setNegativeButton(
                        "确定", null).show();

                        break;

                    case R.id.ll_user_wechat:
                        final EditText editText2=new EditText(UserActivity.this);
                        editText2.setText(user_weixin_in.getText());
                        new AlertDialog.Builder(UserActivity.this).setTitle("修改微信").setIcon(android.R.drawable.ic_dialog_info).setView(
                                editText2).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name= String.valueOf(editText2.getText());
                                user_weixin_in.setText(name);
                                upload(name,"Wechat",phone);

                            }
                        }).setNegativeButton("取消", null).show();
                        break;
                    case R.id.ll_user_qq:
                        final EditText editText3=new EditText(UserActivity.this);
                        editText3.setText(user_qq_in.getText());
                        new AlertDialog.Builder(UserActivity.this).setTitle("修改QQ").setIcon(android.R.drawable.ic_dialog_info).setView(
                                editText3).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name= String.valueOf(editText3.getText());
                                user_qq_in.setText(name);
                                upload(name,"QQ",phone);

                            }
                        }).setNegativeButton("取消", null).show();
                        break;



                }
            }
        };
        llusernickname.setOnClickListener(listener);
        llusersex.setOnClickListener(listener);
        lluserbirthday.setOnClickListener(listener);
        lluseridentity.setOnClickListener(listener);
        llusertele.setOnClickListener(listener);
        lluserwechat.setOnClickListener(listener);
        lluserqq.setOnClickListener(listener);
    }

    private void initViews() {
        llusernickname= (LinearLayout) findViewById(R.id.ll_user_nickname);
        llusersex= (LinearLayout) findViewById(R.id.ll_user_sex);
        lluserbirthday= (LinearLayout) findViewById(R.id.ll_user_birthday);
        lluseridentity= (LinearLayout) findViewById(R.id.ll_user_identity);
        llusertele= (LinearLayout) findViewById(R.id.ll_user_tele);
        lluserwechat= (LinearLayout) findViewById(R.id.ll_user_wechat);
        lluserqq= (LinearLayout) findViewById(R.id.ll_user_qq);
        user_nickname_in= (TextView) findViewById(R.id.tv_user_nickname_in);
        user_sex_in= (TextView) findViewById(R.id.tv_user_sex_in);
        user_birth_in= (TextView) findViewById(R.id.tv_user_birth_in);
        user_identity_in= (TextView) findViewById(R.id.tv_user_identity_in);
        user_phone_in= (TextView) findViewById(R.id.tv_user_phone_in);
        user_weixin_in= (TextView) findViewById(R.id.tv_user_weixin_in);
        user_qq_in= (TextView) findViewById(R.id.tv_user_qq_in);
        mytitle = (TextView) findViewById(R.id.mytitle);
        ibtn_back= (ImageView) findViewById(R.id.ibtn_back);
        ibtn_set= (ImageView) findViewById(R.id.ibtn_set);


    }

    private void initData(){
        if(titleString!=null){
            mytitle.setText(titleString);
        }
    }

    private void initEvent(){
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this,MainActivity.class));
            }
        });


        ibtn_set.setVisibility(View.VISIBLE);
        ibtn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this,UserExitActivity.class));
            }
        });


        startDateTime = (TextView) findViewById(R.id.tv_user_birth_in);
    }

    public void upload(String user){
        RequestParams params = new RequestParams(LOAD_URL+"/servlet/UserServlet");
        params.addQueryStringParameter("user",user);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                Log.e("TAG","onSuccess"+result);
                Gson gson=new Gson();
                UserEntity userinfo=gson.fromJson(result,UserEntity.class);
                user_nickname_in.setText(userinfo.getNickname());
                user_sex_in.setText(userinfo.getSex());
                user_birth_in.setText(userinfo.getBirthday());
                user_identity_in.setText(userinfo.getIdentity());
                user_phone_in.setText(userinfo.getTelephone());
                user_weixin_in.setText(userinfo.getWechat());
                user_qq_in.setText(userinfo.getQq());


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


    public void upload(String value,String name,String phone){
        RequestParams params = new RequestParams(LOAD_URL+"/servlet/ChangeInfoServlet");
        params.addQueryStringParameter("value",value);
        params.addQueryStringParameter("name",name);
        params.addQueryStringParameter("phone",phone);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result

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
