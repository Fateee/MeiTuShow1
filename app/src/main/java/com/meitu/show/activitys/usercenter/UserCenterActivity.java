package com.meitu.show.activitys.usercenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.activitys.donate.DonateActivity;
import com.meitu.show.model.RegisterModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.utils.SharePreUtil;
import com.meitu.show.view.ItemView;

import butterknife.BindView;
import butterknife.OnClick;

public class UserCenterActivity extends BaseActivity {

    @BindView(R.id.nickName)
    ItemView mUserAccountV;

    @BindView(R.id.collection)
    ItemView mCollectionV;

    @BindView(R.id.buy)
    ItemView mVipV;

    @BindView(R.id.donate)
    ItemView mDonateV;

    @BindView(R.id.feedback)
    ItemView mFeedbackV;

    @BindView(R.id.about)
    ItemView mAboutV;

    @BindView(R.id.login_out)
    TextView mLoginOutV;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, UserCenterActivity.class));
    }

    @Override
    protected void initBundle() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_usercenter;
    }

    @Override
    protected void initView() {
        RegisterModel.DataBean userinfo = SharePreUtil.getInstance(getApplicationContext()).getUserInfo();
        mUserAccountV.setRightDesc(userinfo.getPhone());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({/*R.id.txt_left_title,*/ R.id.nickName, R.id.collection, R.id.buy, R.id.donate, R.id.feedback, R.id.about, R.id.login_out})
    public void onViewClick(View v) {
        switch (v.getId()) {
//            case R.id.txt_left_title:
//                finish();
//                break;
            case R.id.nickName:
                break;
            case R.id.collection:
                finish();
                break;
            case R.id.buy:
                DonateActivity.startActivity(UserCenterActivity.this);
                break;
            case R.id.donate:

                break;
            case R.id.feedback:
                break;
            case R.id.about:
                break;
            case R.id.login_out:
                SharePreUtil.getInstance(getApplicationContext()).clear();
                finish();
                break;
        }
    }
}
