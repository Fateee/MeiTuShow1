package com.meitu.show.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.BaseFragment;
import com.meitu.show.R;
import com.meitu.show.activitys.home.adapter.HomeAdapter;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.view.SimpleToolbar;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment {

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

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.common_mainlist;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initViews(View view) {
        txtMainTitle.setTextColor(getResources().getColor(R.color.black));
        swipeRefreshGridList.setOnRefreshListener(mReefreshListener);
        swipeGridList.useDefaultLoadMore();
        swipeGridList.setLoadMoreListener(mLoadMoreListener);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        swipeGridList.setLayoutManager(mGridLayoutManager);
        mHomeAdapter = new HomeAdapter(getActivity());
        swipeGridList.setAdapter(mHomeAdapter);
    }
}
