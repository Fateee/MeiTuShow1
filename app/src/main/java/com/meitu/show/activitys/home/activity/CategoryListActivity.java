package com.meitu.show.activitys.home.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.meitu.show.BaseActivity;
import com.meitu.show.R;
import com.meitu.show.activitys.home.adapter.HomeAdapter;
import com.meitu.show.activitys.profilelist.activity.ProfileListActivity;
import com.meitu.show.model.CommonContentBean;
import com.meitu.show.presenter.CategoryListPresenter;
import com.meitu.show.presenter.HomePresenter;
import com.meitu.show.view.SimpleToolbar;
import com.meitu.show.viewinf.HomeViewInterface;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryListActivity extends BaseActivity<CategoryListPresenter, CategoryListActivity> implements HomeViewInterface {

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

    private CategoryListPresenter mCategoryListPresenter;

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            // 该加载更多啦。
            mCategoryListPresenter.getCategoryMeiTuList(false,mImgId);

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
    private HomeAdapter mHomeAdapter;

    private int mImgId;
    private int mViewType;
    private CommonContentBean mCommonContentBean;

    public static void startActivity(Context mContext, CommonContentBean bean, int viewType) {
        Intent intent = new Intent(mContext, CategoryListActivity.class);
        intent.putExtra(ProfileListActivity.URL_ID_PARAM, bean.getId());
        intent.putExtra(ProfileListActivity.BEAN_PARAM, bean);
        intent.putExtra(ProfileListActivity.VIEW_TYPE, viewType);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();
        initView();
        initData();
    }

    private void prepareData() {
        mImgId = getIntent().getIntExtra(ProfileListActivity.URL_ID_PARAM,0);
        mViewType = getIntent().getIntExtra(ProfileListActivity.VIEW_TYPE,0);
        mCommonContentBean = (CommonContentBean) getIntent().getSerializableExtra(ProfileListActivity.BEAN_PARAM);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    public void initData() {
        mCategoryListPresenter.getCategoryMeiTuList(true,mImgId);
    }

    private void initView() {
        txtMainTitle.setText(mCommonContentBean == null ? "":mCommonContentBean.getName());
        swipeRefreshGridList.setOnRefreshListener(mReefreshListener);
        swipeGridList.useDefaultLoadMore();
        swipeGridList.setLoadMoreListener(mLoadMoreListener);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        swipeGridList.setLayoutManager(mGridLayoutManager);
        mHomeAdapter = new HomeAdapter(this);
        swipeGridList.setAdapter(mHomeAdapter);
    }

    public static final String TAG = "CategoryListActivity";

    @Override
    protected CategoryListPresenter getPresenter() {
        mCategoryListPresenter = new CategoryListPresenter();
        return mCategoryListPresenter;
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
    public void notifyHomeUiWithData(List<CommonContentBean> list, boolean refresh) {
        if (list == null) return;
        if (swipeRefreshGridList.isRefreshing()) swipeRefreshGridList.setRefreshing(false);
        mHomeAdapter.refreshUI(list, refresh);
        // 数据完更多数据，一定要调用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        swipeGridList.loadMoreFinish(list.size() == 0, false);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
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
