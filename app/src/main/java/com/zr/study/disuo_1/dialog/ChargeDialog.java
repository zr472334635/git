package com.zr.study.disuo_1.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zr.study.disuo_1.R;

public class ChargeDialog extends Dialog {


    private EditText edt_chargemoney;

    private TextView tv_chargeDialogTitle;
    private Button btn_diacharconfirm;
    private Button btn_diacharcancle;

    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本

    private View.OnClickListener mClickListener;

    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

    private ChargeDialog.onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private ChargeDialog.onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

    public ChargeDialog(@NonNull Context context) {
        super(context);
    }

    public ChargeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChargeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        setContentView(R.layout.dialog_charge);

        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    private void initView(){
        edt_chargemoney=findViewById(R.id.edt_chargemoney);
        btn_diacharconfirm=findViewById(R.id.btn_diacharconfirm);
        btn_diacharcancle=findViewById(R.id.btn_diacharcancle);
        tv_chargeDialogTitle=findViewById(R.id.tv_chargeDialogTitle);

    }

    private void initData(){
        //如果用户自定了title和message
        if (titleStr != null) {
            tv_chargeDialogTitle.setText(titleStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            btn_diacharconfirm.setText(yesStr);
        }
        if (noStr != null) {
            btn_diacharcancle.setText(noStr);
        }
    }

    private void initEvent(){
        btn_diacharconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        btn_diacharcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, ChargeDialog.onNoOnclickListener onNoOnclickListener) {
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
    public void setYesOnclickListener(String str, ChargeDialog.onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
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
