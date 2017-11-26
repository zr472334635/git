package com.zr.study.disuo_1.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zr.study.disuo_1.Activity.MainActivity;
import com.zr.study.disuo_1.Activity.Map.BaseMapActivity;
import com.zr.study.disuo_1.Activity.leftmenu.CustomerActivity;
import com.zr.study.disuo_1.Activity.leftmenu.InviteActivity;
import com.zr.study.disuo_1.Activity.leftmenu.JourneyActivity;
import com.zr.study.disuo_1.Activity.leftmenu.user.UserActivity;
import com.zr.study.disuo_1.Activity.leftmenu.wallet.WalletActivity;
import com.zr.study.disuo_1.R;


/**
 * Created by 土豆泥 on 2017/8/2.
 */

public class LeftMenuFragment extends Basefragment {
    private TextView journey;
    private TextView purse;
    private TextView friend;
    private TextView query;
    private ImageView user;
    private LinearLayout ll;



    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        journey= (TextView)view.findViewById(R.id.tv_menu_journey);
        purse= (TextView) view.findViewById(R.id.tv_menu_purse);
        friend= (TextView) view.findViewById(R.id.tv_menu_friend);
        query= (TextView) view.findViewById(R.id.tv_menu_query);
        user= (ImageView) view.findViewById(R.id.iv_menu_user);
        ll= (LinearLayout) view.findViewById(R.id.ll);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_menu_user:
                        startActivity(new Intent(mActivity, UserActivity.class));
                        break;
                    case R.id.tv_menu_journey:
                        startActivity(new Intent(mActivity, JourneyActivity.class));
                        break;
                    case R.id.tv_menu_purse:
                        startActivity(new Intent(mActivity, WalletActivity.class));
                        break;
                    case R.id.tv_menu_friend:
                        startActivity(new Intent(mActivity, InviteActivity.class));
                        break;
                    case R.id.tv_menu_query:
                        startActivity(new Intent(mActivity, CustomerActivity.class));
                        break;
//                    case R.id.btn_rental:
//                        startActivity(new Intent(mActivity, BaseMapActivity.class));
//                        break;

                }

            }

        };
        user.setOnClickListener(listener);
        journey.setOnClickListener(listener);
        purse.setOnClickListener(listener);
        friend.setOnClickListener(listener);
        query.setOnClickListener(listener);


        return view;
    }

    @Override
    public void initData() {



    }


}
