package com.meitu.show.activitys.home.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meitu.show.BaseActivity;
import com.meitu.show.BaseFragment;
import com.meitu.show.R;
import com.meitu.show.activitys.home.adapter.HomeAdapter;
import com.meitu.show.fragments.CategoryFragment;
import com.meitu.show.fragments.ChosenFragment;
import com.meitu.show.fragments.FragmentListAdapter;
import com.meitu.show.fragments.LatestFragment;
import com.meitu.show.presenter.HomePresenter;
import com.meitu.show.presenter.base.BasePresenter;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.top_tab)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private FragmentListAdapter mPagerAdapter;
    List<BaseFragment> mFragments;
    private String[] tabs = {"最新", "专辑", "精选"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
//        request();
        initView();
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    private void initView() {
        mFragments = new ArrayList<>();
        mPagerAdapter = new FragmentListAdapter(getSupportFragmentManager());
        for (String tab : tabs) {
            switch (tab) {//
                case "最新":
                    mFragments.add(LatestFragment.getInstance(tab));
                    break;
                case "专辑":
                    mFragments.add(CategoryFragment.getInstance(tab));
                    break;
                case "精选":
                    mFragments.add(ChosenFragment.getInstance(tab));
                    break;
            }
        }
        mPagerAdapter.setTabText(Arrays.asList(tabs));
        mPagerAdapter.setFragments(mFragments);
    }

    private void initData() {
        mViewPager.setAdapter(mPagerAdapter);
        //缓存fragment页面最大为4页
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                mCurTabIndex = position;
//                mCurrentFragment = mFragments.get(position);
//                mTitleView.setKeyWord(mSearchWord);
//                mTitleView.toggleChangeBtn(mCurrentFragment instanceof SearchProductFragment);
//                if (null != mCurrentFragment && !TextUtils.isEmpty(mTitleView.getSearchText())) {
//                    mCurrentFragment.loadData(mTitleView.getSearchText());
//                }
//                if (!setShopData(mShopHandler)) {
//                    mBannerView.setGone();
//                }
//                SASearchConstant.reportTabClickToSA(SearchListNewActivity.this, tabs.get(position).tabName);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, true);
        //线上crash fix，mFragments是根据tabs数量来初始化的，只有tabs数不大于1时mFragments才可能为空
        if (mFragments != null && mFragments.size() > 0) {
            initTablayout();
        }
    }

    private static final String TAG = "HomeActivity";



    public void initTablayout() {
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(tab.getText()));
            }
        }
        updateTabTextView(mTabLayout.getTabAt(mTabLayout.getSelectedTabPosition()), true);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
//        setTabLine(25,25);
    }

    public View getTabView(CharSequence title) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.ls_custom_view_tab_item, null);
        TextView tv = (TextView) layout.findViewById(R.id.search_tab_tv);
        tv.setText(title);
        return layout;
    }

    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect) {
            //选中加粗
            LinearLayout tabView = (LinearLayout) tab.getCustomView();
            if (tabView == null) return;
            TextView tabSelect = (TextView) tabView.findViewById(R.id.search_tab_tv);
            View tabline = tabView.findViewById(R.id.search_tab_line);
            tabline.setVisibility(View.VISIBLE);
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setText(tab.getText());
            tabSelect.setTextColor(Color.parseColor("#333333"));
        } else {
            LinearLayout tabView = (LinearLayout) tab.getCustomView();
            if (tabView == null) return;
            TextView tabUnSelect = (TextView) tabView.findViewById(R.id.search_tab_tv);
            View tabline = tabView.findViewById(R.id.search_tab_line);
            tabline.setVisibility(View.INVISIBLE);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
            tabUnSelect.setTextColor(Color.parseColor("#999999"));
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }
}
