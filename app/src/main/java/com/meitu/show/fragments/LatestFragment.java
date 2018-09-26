package com.meitu.show.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.BaseFragment;
import com.meitu.show.R;
import com.meitu.show.activitys.home.adapter.HomeAdapter;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.presenter.HomePresenter;
import com.meitu.show.view.SimpleToolbar;
import com.meitu.show.viewinf.HomeViewInterface;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;

public class LatestFragment extends BaseFragment<HomePresenter, LatestFragment> implements HomeViewInterface {

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

    private HomePresenter mHomePresenter;

    private HomeAdapter mHomeAdapter;

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            // 该加载更多啦。
            mHomePresenter.getHomeMeiTuList(false);

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

    public static LatestFragment getInstance(String schema) {
        LatestFragment fragment = new LatestFragment();
        Bundle args = new Bundle();
        args.putString(schema, schema);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.common_mainlist;
    }

    @Override
    protected HomePresenter getPresenter() {
        mHomePresenter = new HomePresenter();
        return mHomePresenter;
    }

    @Override
    protected void initData() {
        mHomePresenter.getHomeMeiTuList(true);
    }

    @Override
    protected void initViews(View view) {
        simpleToolbar.setVisibility(View.GONE);
        swipeRefreshGridList.setOnRefreshListener(mReefreshListener);
        swipeGridList.useDefaultLoadMore();
        swipeGridList.setLoadMoreListener(mLoadMoreListener);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        swipeGridList.setLayoutManager(mGridLayoutManager);
        mHomeAdapter = new HomeAdapter(getActivity());
        swipeGridList.setAdapter(mHomeAdapter);

        mHomePresenter.getAppNewestInfo();
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
    public void notifyHomeUiWithData(List<PoMeiTuModel.ContentBean> list, boolean refresh) {
        if (list == null) return;
        if (swipeRefreshGridList.isRefreshing()) swipeRefreshGridList.setRefreshing(false);
        mHomeAdapter.refreshUI(list, refresh);
        // 数据完更多数据，一定要调用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        swipeGridList.loadMoreFinish(list.size() == 0, true);
    }
}
