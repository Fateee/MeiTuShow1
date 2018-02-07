package com.meitu.show.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.meitu.show.R;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.profilelist.activity.ProfileListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class HomeAdapter<T> extends RecyclerView.Adapter<HomeViewHolder> {

    private int[] mPlaceHolders = new int[] {R.color.CBBDEFB,R.color.CC5CAE9,R.color.CD8D8D8,R.color.CDCEDC8,R.color.CFDEFBA};
    private List<T> mDataList;
    private Context mContext;
    private View.OnClickListener mProlistListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = (String) v.getTag(R.id.id_one);
            ProfileListActivity.startActivity(mContext,url);
        }
    };
    private int mItemWidth;

    public HomeAdapter(Context context) {
        mDataList = new ArrayList<>();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        String url = null;
        T temp = mDataList.get(position);
        if (temp instanceof HomeMeituModel.Content.DataDetail) {
            url = ((HomeMeituModel.Content.DataDetail) temp).getImg();
            if (TextUtils.isEmpty(url)) return;
            String link = ((HomeMeituModel.Content.DataDetail) temp).getLink();
            holder.mHomeItemIV.setTag(R.id.id_one,link);
            holder.mHomeItemIV.setOnClickListener(mProlistListener);
        }
        if (temp instanceof ProlistModel.ProlistContent.DataDetail) {
            url = ((ProlistModel.ProlistContent.DataDetail) temp).getImg();
            if (TextUtils.isEmpty(url)) return;
            ViewGroup.LayoutParams layoutParams = holder.mHomeItemIV.getLayoutParams();
            layoutParams.width = mItemWidth;
            layoutParams.height = mItemWidth;
            holder.mHomeItemIV.setLayoutParams(layoutParams);
            holder.itemView.setPadding(3,3,3,3);
        }
        int index=(int)(Math.random()*mPlaceHolders.length);
        int nowHolder = mPlaceHolders[index];
        Glide.with(mContext).load(url).placeholder(nowHolder).centerCrop().into(holder.mHomeItemIV);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void refreshUI(List<T> list, boolean refresh) {
        if (refresh) {
            mDataList.clear();
        }
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void setItemWidth(int mItemWidth) {
        this.mItemWidth = mItemWidth;
    }
}