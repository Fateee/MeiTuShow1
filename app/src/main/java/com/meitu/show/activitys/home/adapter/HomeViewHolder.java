package com.meitu.show.activitys.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.meitu.show.R;

/**
 * Created by Administrator on 2018/2/4.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {
    public final ImageView mHomeItemIV;

    public HomeViewHolder(View itemView) {
        super(itemView);
        mHomeItemIV = (ImageView) itemView.findViewById(R.id.iv_home_pic);
    }
}
