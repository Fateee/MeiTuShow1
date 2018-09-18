package com.meitu.show.activitys.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.meitu.show.Constant;
import com.meitu.show.R;
import com.meitu.show.activitys.details.PhotoViewActivity;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.model.PoProlistModel;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.activitys.profilelist.activity.ProfileListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class HomeAdapter<T> extends RecyclerView.Adapter<HomeViewHolder> {

    private int[] mPlaceHolders = new int[] {R.color.CBBDEFB,R.color.CC5CAE9,R.color.CD8D8D8,R.color.CDCEDC8,R.color.CFDEFBA};
    private ArrayList<T> mDataList;
    private Context mContext;
    private View.OnClickListener mProlistListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int imgId = (int) v.getTag(R.id.id_one);
            ProfileListActivity.startActivity(mContext,imgId);
        }
    };
    private int mItemWidth;
    private View.OnClickListener mDetailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag(R.id.id_one);
            startActivity(mContext,pos,mDataList);
        }
    };

    public  void startActivity(Context mContext, int pos, ArrayList<T> mDataList) {
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.NOWPOS,pos);
        intent.putExtra(PhotoViewActivity.DATALIST,mDataList);
        mContext.startActivity(intent);
    }

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
            holder.mHomeItemIV.setTag(R.id.id_one,position);
            holder.mHomeItemIV.setOnClickListener(mDetailListener);
        } else if (temp instanceof PoMeiTuModel.ContentBean) {
            url = Constant.mHomePoUrl+((PoMeiTuModel.ContentBean)temp).getCover();
            if (TextUtils.isEmpty(url)) return;
            int linkId = ((PoMeiTuModel.ContentBean)temp).getId();
            holder.mHomeItemIV.setTag(R.id.id_one,linkId);
            holder.mHomeItemIV.setOnClickListener(mProlistListener);
        } else if (temp instanceof PoProlistModel.ContentBean) {
            url = Constant.mHomePoUrl+((PoProlistModel.ContentBean)temp).getUrl();
            if (TextUtils.isEmpty(url)) return;
            ViewGroup.LayoutParams layoutParams = holder.mHomeItemIV.getLayoutParams();
            layoutParams.width = mItemWidth;
            layoutParams.height = mItemWidth;
            holder.mHomeItemIV.setLayoutParams(layoutParams);
            holder.itemView.setPadding(3,3,3,3);
            holder.mHomeItemIV.setTag(R.id.id_one,position);
            holder.mHomeItemIV.setOnClickListener(mDetailListener);
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
