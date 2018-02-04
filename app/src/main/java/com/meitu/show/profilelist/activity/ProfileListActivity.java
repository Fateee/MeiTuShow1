package com.meitu.show.profilelist.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.home.adapter.HomeAdapter;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.presenter.HomePresenter;
import com.meitu.show.presenter.ProListPresenter;
import com.meitu.show.viewinf.ProListViewInterface;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/4.
 */

public class ProfileListActivity extends BaseActivity<ProListPresenter,ProfileListActivity> implements ProListViewInterface {

    private static String URL_PARAM="URL_PARAM";
    @BindView(R.id.swipe_grid_list)
    SwipeMenuRecyclerView swipeGridList;
    @BindView(R.id.swipe_Refresh_grid_list)
    SwipeRefreshLayout swipeRefreshGridList;

    private ProListPresenter mProListPresenter;

    private HomeAdapter mHomeAdapter;

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            // 该加载更多啦。
            mProListPresenter.getProlistMeiTuList(false,mUrlParam);

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

    public static void startActivity(Context mContext, String url) {
        Intent intent = new Intent(mContext,ProfileListActivity.class);
        intent.putExtra(URL_PARAM,url);
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
        mProListPresenter.getProlistMeiTuList(true,mUrlParam);
    }

    private void initView() {
        swipeRefreshGridList.setOnRefreshListener(mReefreshListener);
        swipeGridList.useDefaultLoadMore();
        swipeGridList.setLoadMoreListener(mLoadMoreListener);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        swipeGridList.setLayoutManager(mGridLayoutManager);
        mHomeAdapter = new HomeAdapter<>(this);
        swipeGridList.setAdapter(mHomeAdapter);
    }

    private static final String TAG = "MainActivity";

    @Override
    protected ProListPresenter getPresenter() {
        mProListPresenter = new ProListPresenter();
        return mProListPresenter;
    }

    @Override
    public void notifyListUiWithData(List<ProlistModel.ProlistContent.DataDetail> list, boolean refresh) {
        if (list == null) return;
        if (swipeRefreshGridList.isRefreshing()) swipeRefreshGridList.setRefreshing(false);
        mHomeAdapter.refreshUI(list, refresh);
        // 数据完更多数据，一定要调用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        swipeGridList.loadMoreFinish(list.size() == 0, true);
    }
}
