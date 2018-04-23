package com.zr.study.disuo_1.Activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.dialog.RentDialog;
import com.zr.study.disuo_1.fragment.LeftMenuFragment;
import com.zr.study.disuo_1.fragment.MapFragment;

import org.xutils.x;


public class MainActivity extends SlidingFragmentActivity {

    private ImageView ibtn_icon_user;
    private ImageView msg;
    private RelativeLayout rl_main_bottom;
    private RelativeLayout rl_main_top;
    private RelativeLayout rl;


    private ImageView iv_main_rent;

    private ImageView ivmainmsg;

    private ImageView toparrow;
    private ImageView bottomarrow;
    SlidingMenu slidingMenu;
    private RelativeLayout.LayoutParams params;
    private boolean isOpen = false; //是否开启状态
    int height = 0;
    int oldHeight = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);
        slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//设置全屏触摸
        slidingMenu.setBehindOffset(300);//设置预留屏幕宽度

        initFragment();
        initView();

        initShowBottomHide();

        x.view().inject(this);
        initEvent();

    }


    private void initShowBottomHide() {
        //布局完成
        rl_main_bottom.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除所有监听
                rl_main_bottom.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //mLayoutHeight = relativeLayout.getHeight();
                System.out.println("得到的高度：" + oldHeight);
                //隐藏当前控件
                //relativeLayout.setPadding(0,-mLayoutHeight,0,0);
            }
        });

        //点击,开始执行动画
        bottomarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ValueAnimator valueAnimator = new ValueAnimator();
                if (isOpen) {
                    valueAnimator.setIntValues(height - 300, oldHeight);

                } else {
                    valueAnimator.setIntValues(oldHeight, height - 300);

                }
                //设置监听的值
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (int) animator.getAnimatedValue();
                        params.topMargin = value;
                        rl_main_bottom.setLayoutParams(params);

                    }
                });
                //动画执行中监听
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        //动画开始，不能点击
                        bottomarrow.setClickable(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        bottomarrow.setClickable(true);
                        if (isOpen) {
                            ibtn_icon_user.setVisibility(View.INVISIBLE);
                            msg.setVisibility(View.INVISIBLE);
                            rl.setVisibility(View.INVISIBLE);

                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                isOpen = !isOpen;
                valueAnimator.setDuration(500);
                valueAnimator.start();
                //状态更改


                if (!isOpen) {
                    ibtn_icon_user.setVisibility(View.VISIBLE);
                    msg.setVisibility(View.VISIBLE);
                    rl.setVisibility(View.VISIBLE);
                }

                //进行旋转
                ViewCompat.animate(bottomarrow).rotationBy(180f).setDuration(500).start();
            }
        });
    }

    private void initView(){
        ibtn_icon_user = (ImageView) findViewById(R.id.iv_main_user);
        rl_main_top = (RelativeLayout) findViewById(R.id.rl_main_top);
        rl_main_bottom = (RelativeLayout) findViewById(R.id.rl_main_bottom);
        bottomarrow = (ImageView) findViewById(R.id.iv_main_bottomarrow);
        msg = (ImageView) findViewById(R.id.iv_main_msg);
        rl = (RelativeLayout) findViewById(R.id.rl_main_rl);
        iv_main_rent = (ImageView) findViewById(R.id.iv_main_rent);
        ivmainmsg = (ImageView) findViewById(R.id.iv_main_msg);
        params = (RelativeLayout.LayoutParams) rl_main_bottom.getLayoutParams();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int width = metrics.widthPixels;
        height = metrics.heightPixels;
        final int densityDpi = metrics.densityDpi;
        oldHeight = height - densityDpi * 250 / 160;
        params.topMargin = oldHeight;
        rl_main_bottom.setLayoutParams(params);
    }

    private void initData(){

    }

    private void initEvent(){
        ibtn_icon_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenu.toggle();
            }
        });

//        ivmainmsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, UpcomingEventsActivity.class));
//            }
//        });


        iv_main_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RentDialog rentDialog = new RentDialog(MainActivity.this);
                rentDialog.setTitle("终点");
                rentDialog.setMessage("请输入目的地?");
                rentDialog.setYesOnclickListener("确定", new RentDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(MainActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        rentDialog.dismiss();
                    }
                });
                rentDialog.setNoOnclickListener("取消", new RentDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(MainActivity.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                        rentDialog.dismiss();
                    }
                });
                rentDialog.show();



                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (v.getId()) {
                            case R.id.btn_save:

                                break;
                        }
                    }
                };
            }
        });
    }


    private void initFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();//开启事物


        transaction.replace(R.id.fl_left_menu, new LeftMenuFragment());//用fragment替换
        transaction.replace(R.id.fl_map, new MapFragment());


        transaction.commit();//提交事物
    }


}
