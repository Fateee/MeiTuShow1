package com.meitu.show.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.meitu.show.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentListAdapter extends FragmentPagerAdapter {

    private List<String> tabText;
    private ArrayList<BaseFragment> listFragments;

    public FragmentListAdapter(FragmentManager fm) {
        super(fm);
        listFragments = new ArrayList<>();
    }

    public void setFragments(List<BaseFragment> orderFragments) {
        if (null != orderFragments
                && orderFragments.size() > 0) {
            listFragments.clear();
            listFragments.addAll(orderFragments);
        }

        notifyDataSetChanged();
    }

    public void setTabText(List<String> tabText) {
        if (null != tabText && tabText.size() > 0) {
            this.tabText = tabText;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabText.get(position);
    }
}