package com.meitu.show.activitys.donate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.model.BuyVipModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.view.SimpleToolbar;

import java.util.ArrayList;

import butterknife.BindView;

public class DonateActivity extends BaseActivity {

    @BindView(R.id.rv_vip_list)
    RecyclerView mRvViplist;

    @BindView(R.id.txt_left_title)
    TextView txtLeftTitle;

    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;

    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;

    ArrayList mVipData = new ArrayList();

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, DonateActivity.class));
    }

    @Override
    protected void initBundle() {
        BuyVipModel threeDaysModel = new BuyVipModel("VIP一周试用（享受%s天）", 7, 4.99, BuyVipModel.PRODUCT);
        BuyVipModel monthModel = new BuyVipModel("VIP月度会员（享受%s天）", 30, 16.99, BuyVipModel.PRODUCT);
        BuyVipModel quarterModel = new BuyVipModel("VIP季度会员（享受%s天）", 90, 39.99, BuyVipModel.PRODUCT);
        BuyVipModel halfModel = new BuyVipModel("VIP半年会员（享受%s天）", 180, 59.99, BuyVipModel.PRODUCT);
        BuyVipModel yearModel = new BuyVipModel("VIP年度会员（享受%s天）", 365, 99.99, BuyVipModel.PRODUCT);
        BuyVipModel foreverModel = new BuyVipModel("VIP白金永久会员(无限制)", 900000, 188.88, BuyVipModel.PRODUCT);
        BuyVipModel infoModel = new BuyVipModel("", -1, 0, BuyVipModel.INFO);

        mVipData.add(threeDaysModel);
        mVipData.add(monthModel);
        mVipData.add(quarterModel);
        mVipData.add(halfModel);
        mVipData.add(yearModel);
        mVipData.add(foreverModel);
        mVipData.add(infoModel);
    }

    @Override
    protected int getContentView() {
        return R.layout.act_pay;
    }

    @Override
    protected void initView() {
        txtLeftTitle.setVisibility(View.VISIBLE);
        txtMainTitle.setText("赞助成为VIP");
        LinearLayoutManager mGridLayoutManager = new LinearLayoutManager(this);
        mRvViplist.setLayoutManager(mGridLayoutManager);
        DonateAdapter mDonateAdapter = new DonateAdapter(this, mVipData);
        mRvViplist.setAdapter(mDonateAdapter);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDatas() {

    }
}
