package com.zr.study.disuo_1.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zr.study.disuo_1.R;

public class ReturnDialog extends Dialog {
    private TextView tv_direturntitle;
    private TextView tv_diareturnst;
    private TextView tv_diareturnbet;
    private TextView tv_diareturnbt;
    private TextView tv_diareturnfee;

    private Button direturncancle;

    private String direturntitle;
    private String diareturnst;
    private String diareturnet;
    private String diareturnbt;
    private String diareturnfee;

    private String noStr;


    public ReturnDialog.onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器


    public ReturnDialog(Context context) {
        super(context);
    }

    public ReturnDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ReturnDialog(Context context, boolean cancelable
            , OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        setContentView(R.layout.dialog_return);

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
        tv_direturntitle=findViewById(R.id.tv_direturntitle);
        tv_diareturnst=findViewById(R.id.tv_diareturnst);
        tv_diareturnbet=findViewById(R.id.tv_diareturnet);
        tv_diareturnbt=findViewById(R.id.tv_diareturnbt);
        tv_diareturnfee=findViewById(R.id.tv_diareturnfee);

        direturncancle=findViewById(R.id.btn_diareturncancle);

    }

    private void initData(){


        if (direturntitle != null) {
            tv_direturntitle.setText(direturntitle);
        }
        if (diareturnst != null) {
            tv_diareturnst.setText(diareturnst);
        }
        if (diareturnet != null) {
            tv_diareturnbet.setText(diareturnet);
        }
        if (diareturnbt != null) {
            tv_diareturnbt.setText(diareturnbt);
        }
        if (diareturnfee != null) {
            tv_diareturnfee.setText(diareturnfee);
        }
        if(noStr!=null){
            direturncancle.setText(noStr);
        }


    }

    private void initEvent() {
        //设置取消按钮被点击后，向外界提供监听
        direturncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }


    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param Sdireturntitle
     */
    public void setDireturntitle(String Sdireturntitle) {
        direturntitle = Sdireturntitle;
    }

    /**
     * @param Sdiareturnst
     */
    public void setDiareturnst(String Sdiareturnst) {
        diareturnst = Sdiareturnst;
    }

    /**
     * @param Sdiareturnet
     */
    public void setDiareturnet(String Sdiareturnet) {
        diareturnet = Sdiareturnet;
    }

    /**
     * @param Sdiareturnbt
     */
    public void setDiareturnbt(String Sdiareturnbt) {
        diareturnbt = Sdiareturnbt;
    }

    /**
     * @param Sdiareturnfee
     */
    public void setDiareturnfee(String Sdiareturnfee) {
        diareturnfee = Sdiareturnfee;
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, ReturnDialog.onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */

    public interface onNoOnclickListener {
        public void onNoClick();
    }
}
