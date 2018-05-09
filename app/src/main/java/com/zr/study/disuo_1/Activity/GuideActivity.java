package com.zr.study.disuo_1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.zr.study.disuo_1.Activity.leftmenu.user.UserExitActivity;
import com.zr.study.disuo_1.R;
import com.zr.study.disuo_1.Utils.PreUtils;

import java.util.ArrayList;

/**
 * Created by 土豆泥 on 2017/8/1.
 */

public class GuideActivity extends Activity {

    private static  final int[] mImageIds=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private ViewPager vpGuide;
    private ArrayList<ImageView> mImageViewList;
    private LinearLayout llPointGroup;//引导原点的父控件
    private int mPointWidth;
    private View viewRedPoint;
    private Button btnStart;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题

        setContentView(R.layout.activity_guide);




        initViews();
        initData();
        initEvent();
        vpGuide.setAdapter(new GuideAdapter());

        vpGuide.setOnPageChangeListener(new GudiePageListener());
    }


    private void initViews(){
        vpGuide= (ViewPager) findViewById(R.id.vpGuide);
        llPointGroup= (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint=findViewById(R.id.view_red_point);
        btnStart= (Button) findViewById(R.id.btn_start);
        mImageViewList=new ArrayList<ImageView>();

        //初始化引导页的3个页面
        for (int i = 0; i < mImageIds.length; i++) {

            ImageView image=new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);//设置引导页背景
            mImageViewList.add(image);
        }

        //初始化引导页的小圆点
        for (int i = 0; i <mImageIds.length ; i++) {
            View point=new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);//设置引导页默认圆点

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(30,30);
            if(i>0){
                params.leftMargin=30;
            }
            point.setLayoutParams(params);

            llPointGroup.addView(point);//将圆点添加给线性布局
        }

        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("layout结束");
                llPointGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mPointWidth=llPointGroup.getChildAt(1).getLeft()-llPointGroup.getChildAt(0).getLeft();
                System.out.println(mPointWidth);
            }
        });
    }

    private void initData(){

    }

    private void initEvent(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新sp,表示已经展示了新手引导

                PreUtils.setBoolean(GuideActivity.this,"is_user_guide_showed",true);
                //跳转主页面
                startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    class GuideAdapter extends PagerAdapter {
        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mImageIds.length;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class GudiePageListener implements ViewPager.OnPageChangeListener {


        //滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            System.out.println("当前位置"+position+";百分比"+positionOffset+";移动距离"+positionOffsetPixels);
            int len= (int) (mPointWidth*positionOffset)+position*mPointWidth;
            RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();//获取当前红点的布局参数
            params.leftMargin=len;//设置左边距
            viewRedPoint.setLayoutParams(params);
        }


        //被选中事件
        @Override
        public void onPageSelected(int position) {

            if (position==mImageIds.length-1){
                btnStart.setVisibility(View.VISIBLE);
            }else {
                btnStart.setVisibility(View.INVISIBLE);
            }
        }


        //滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


}
