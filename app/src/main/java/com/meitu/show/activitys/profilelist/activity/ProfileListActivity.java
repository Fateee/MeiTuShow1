package com.meitu.show.activitys.profilelist.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.activitys.home.adapter.HomeAdapter;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.presenter.ProListPresenter;
import com.meitu.show.view.SimpleToolbar;
import com.meitu.show.viewinf.ProListViewInterface;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/2/4.
 */

public class ProfileListActivity extends BaseActivity<ProListPresenter, ProfileListActivity> implements ProListViewInterface {

    private static String URL_PARAM = "URL_PARAM";
    @BindView(R.id.swipe_grid_list)
    SwipeMenuRecyclerView swipeGridList;
    @BindView(R.id.swipe_Refresh_grid_list)
    SwipeRefreshLayout swipeRefreshGridList;
    @BindView(R.id.txt_left_title)
    TextView txtLeftTitle;
    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;

    private ProListPresenter mProListPresenter;

    private HomeAdapter mHomeAdapter;

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            // 该加载更多啦。
            mProListPresenter.getProlistMeiTuList(false, mUrlParam);

            // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
            // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
            // errorMessage是会显示到loadMoreView上的，用户可以看到。
            // mRecyclerView.loadMoreError(0, "请求网络失败");
        }
    };
    private SwipeRefreshLayout.OnRefreshListener mReefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            initData();
        }
    };
    private String mUrlParam;
    private int mItemWidth;

    public static void startActivity(Context mContext, String url) {
        Intent intent = new Intent(mContext, ProfileListActivity.class);
        intent.putExtra(URL_PARAM, url);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareData();
        initView();
        initData();
    }

    private void prepareData() {
        mUrlParam = getIntent().getStringExtra(URL_PARAM);
    }

    private void initData() {
        mProListPresenter.getProlistMeiTuList(true, mUrlParam);
    }

    private void initView() {
        txtLeftTitle.setVisibility(View.VISIBLE);
        swipeRefreshGridList.setOnRefreshListener(mReefreshListener);
        swipeGridList.useDefaultLoadMore();
        swipeGridList.setLoadMoreListener(mLoadMoreListener);
        swipeGridList.setPadding(5, 5, 5, 5);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
        swipeGridList.setLayoutManager(mGridLayoutManager);
        mHomeAdapter = new HomeAdapter<>(this);
        mItemWidth = getWindowManager().getDefaultDisplay().getWidth() / 3;
        mHomeAdapter.setItemWidth(mItemWidth);
        swipeGridList.setAdapter(mHomeAdapter);
    }

    private static final String TAG = "MainActivity";

    @Override
    protected ProListPresenter getPresenter() {
        mProListPresenter = new ProListPresenter();
        return mProListPresenter;
    }

    @Override
    public void showLoading() {
        swipeRefreshGridList.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        swipeRefreshGridList.setRefreshing(false);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void notifyListUiWithData(List<ProlistModel.ProlistContent.DataDetail> list, boolean refresh) {
//        if (list == null) return;
//        if (swipeRefreshGridList.isRefreshing()) swipeRefreshGridList.setRefreshing(false);
//        mHomeAdapter.refreshUI(list, refresh);
//        // 数据完更多数据，一定要调用这个方法。
//        // 第一个参数：表示此次数据是否为空。
//        // 第二个参数：表示是否还有更多数据。
//        swipeGridList.loadMoreFinish(list.size() == 0, true);
    }

    @Override
    public void notifyListUiWithData(List<ProlistModel.ProlistContent.DataDetail> list) {
        if (list == null) return;
        if (swipeRefreshGridList.isRefreshing()) swipeRefreshGridList.setRefreshing(false);
        mHomeAdapter.refreshUI(list, true);
        swipeGridList.loadMoreFinish(list.size() == 0, false);
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