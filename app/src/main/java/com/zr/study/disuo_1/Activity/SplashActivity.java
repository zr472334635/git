package com.zr.study.disuo_1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.zr.study.disuo_1.Activity.leftmenu.user.UserExitActivity;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.Utils.PreUtils;


public class SplashActivity extends Activity {

    RelativeLayout rlRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rlRoot=(RelativeLayout)findViewById(R.id.rl_root);

        stzrtAnim();
    }

    private void stzrtAnim(){

        AnimationSet set=new AnimationSet(false);//动画集合


        //旋转动画
        RotateAnimation rotate=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(1000);
        rotate.setFillAfter(true);//保持动画状态


        //缩放动画
        ScaleAnimation scale=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(1000);
        scale.setFillAfter(true);

        AlphaAnimation alpha=new AlphaAnimation(0,1);
        alpha.setDuration(1000);
        alpha.setFillAfter(true);


        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //跳转到新手引导页
               jumpNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rlRoot.startAnimation(set);
    }

    private void jumpNextPage(){
        boolean userGuide = PreUtils.getBoolean(this,"is_user_guide_showed",false);
        if(!userGuide){
            startActivity(new Intent(SplashActivity.this,GuideActivity.class));

        }else {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        }
        finish();

    }
}
