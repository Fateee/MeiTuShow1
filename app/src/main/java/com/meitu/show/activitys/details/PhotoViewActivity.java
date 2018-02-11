package com.meitu.show.activitys.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.R;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.view.MyImageAdapter;
import com.meitu.show.view.PhotoViewPager;
import com.meitu.show.view.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyi on 18/2/9.
 * email: huyi@codoon.com
 * author: huyi
 */

public class PhotoViewActivity extends AppCompatActivity {

    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    public static String NOWPOS = "NOWPOS";
    public static String DATALIST = "DATALIST";
    private int currentPosition;
    private MyImageAdapter adapter;
    private TextView mTvImageCount;
    private TextView mTvSaveImage;
    private ArrayList<ProlistModel.ProlistContent.DataDetail> mDataList;

    @BindView(R.id.view_pager_photo)
    PhotoViewPager mViewPager;

    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;

    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;

    @BindView(R.id.txt_left_title)
    TextView txtLeftTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        simpleToolbar.setBackgroundResource(R.color.black);
        txtLeftTitle.setVisibility(View.VISIBLE);
    }

    private void initData() {

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra(NOWPOS, 0);
        mDataList = (ArrayList<ProlistModel.ProlistContent.DataDetail>) intent.getSerializableExtra(DATALIST);
        txtMainTitle.setText((currentPosition+1)+"/"+mDataList.size());
        adapter = new MyImageAdapter(mDataList, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
//        mTvImageCount.setText(currentPosition+1 + "/" + mDataList.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                txtMainTitle.setText((currentPosition+1)+"/"+mDataList.size());
            }
        });
    }

    @OnClick(R.id.txt_left_title)
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.txt_left_title:
                finish();
                break;
        }
    }

}