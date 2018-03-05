package com.zr.study.disuo_1.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zr.study.disuo_1.R;


public class RentDialog extends Dialog {

    Activity context;
    private Button btn_confirm;
    private Button btn_cancle;
    private EditText edt_destination;
    private TextView tv_rentDialogTitle;
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本

    private View.OnClickListener mClickListener;

    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public RentDialog(Context context) {
        super(context);
    }

    public RentDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RentDialog(Context context, boolean cancelable
            , OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        setContentView(R.layout.rent_input);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            tv_rentDialogTitle.setText(titleStr);
        }
        if (messageStr != null) {
            edt_destination.setHint(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            btn_confirm.setText(yesStr);
        }
        if (noStr != null) {
            btn_cancle.setText(noStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        edt_destination = findViewById(R.id.edt_destination);
        tv_rentDialogTitle = findViewById(R.id.tv_rentDialogTitle);
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_cancle = findViewById(R.id.btn_cancle);
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }
}