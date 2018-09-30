package com.meitu.show.activitys.home.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.meitu.show.BaseFragment;
import com.meitu.show.Constant;
import com.meitu.show.R;
import com.meitu.show.activitys.details.PhotoViewActivity;
import com.meitu.show.activitys.home.activity.CategoryListActivity;
import com.meitu.show.fragments.CategoryFragment;
import com.meitu.show.fragments.ChosenFragment;
import com.meitu.show.fragments.LatestFragment;
import com.meitu.show.model.CommonContentBean;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.model.PoProlistModel;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.activitys.profilelist.activity.ProfileListActivity;
import com.meitu.show.utils.ScreenUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class HomeAdapter<T> extends RecyclerView.Adapter<HomeViewHolder> {

    private int[] mPlaceHolders = new int[]{R.color.CBBDEFB, R.color.CC5CAE9, R.color.CD8D8D8, R.color.CDCEDC8, R.color.CFDEFBA};
    private ArrayList<T> mDataList;
    private Context mContext;
    private View.OnClickListener mProlistListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonContentBean mPicBean = (CommonContentBean) v.getTag(R.id.id_one);
            int imgId = mPicBean.getId();
            String typeStr = "";
            switch (mType) {
                case BaseFragment.LATEST_TYPE:
                    typeStr = LatestFragment.TAG;
                    ProfileListActivity.startActivity(mContext, mPicBean, mType);
                    break;
                case BaseFragment.CATEGORY_TYPE:
                    typeStr = CategoryFragment.TAG;
                    CategoryListActivity.startActivity(mContext, mPicBean, mType);
                    break;
                case BaseFragment.CHOSEN_TYPE:
                    typeStr = ChosenFragment.TAG;
                    ProfileListActivity.startActivity(mContext, mPicBean, mType);
                    break;
                default:
                    if (mContext instanceof CategoryListActivity)
                        typeStr = CategoryListActivity.TAG;
                    ProfileListActivity.startActivity(mContext, mPicBean, mType);
                    break;
            }

            HashMap<String, String> map = new HashMap<>();
            map.put("view_type", typeStr);
            map.put("imgId", imgId + "");
            MobclickAgent.onEvent(mContext, "Click", map);
        }
    };
    private int mItemWidth;
    private View.OnClickListener mDetailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String typeStr = "";
            switch (mType) {
                case BaseFragment.LATEST_TYPE:
                    typeStr = LatestFragment.TAG;
                    break;
                case BaseFragment.CATEGORY_TYPE:
                    typeStr = CategoryFragment.TAG;
                    break;
                case BaseFragment.CHOSEN_TYPE:
                    typeStr = ChosenFragment.TAG;
                    break;
            }
            int pos = (int) v.getTag(R.id.id_one);

            HashMap<String, String> map = new HashMap<>();
            map.put("view_type", typeStr + "_profilelist");
            map.put("imgId", mImgId + "");
            map.put("pos", pos + "");
            MobclickAgent.onEvent(mContext, "Click", map);

            startActivity(mContext, pos, mDataList);
        }
    };
    private int mType;
    private int mImgId;

    public void startActivity(Context mContext, int pos, ArrayList<T> mDataList) {
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.NOWPOS, pos);
        intent.putExtra(PhotoViewActivity.DATALIST, mDataList);
        mContext.startActivity(intent);
    }

    public HomeAdapter(Context context) {
        mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
            holder.mHomeItemIV.setTag(R.id.id_one, link);
            holder.mHomeItemIV.setOnClickListener(mProlistListener);
        }
        if (temp instanceof ProlistModel.ProlistContent.DataDetail) {
            url = ((ProlistModel.ProlistContent.DataDetail) temp).getImg();
            if (TextUtils.isEmpty(url)) return;
            ViewGroup.LayoutParams layoutParams = holder.mHomeItemIV.getLayoutParams();
            layoutParams.width = mItemWidth;
            layoutParams.height = mItemWidth;
            holder.mHomeItemIV.setLayoutParams(layoutParams);
            holder.itemView.setPadding(3, 3, 3, 3);
            holder.mHomeItemIV.setTag(R.id.id_one, position);
            holder.mHomeItemIV.setOnClickListener(mDetailListener);
        } else if (temp instanceof CommonContentBean) {
            CommonContentBean mPicBean = (CommonContentBean) temp;
            url = Constant.mHomePoUrl + mPicBean.getCover();
            if (TextUtils.isEmpty(url)) return;
//            if (mLatestType == 1) {
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.mHomeItemIV.getLayoutParams();
//                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
//                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                holder.mHomeItemIV.setLayoutParams(layoutParams);
//            }
            initCoverWH(holder.mHomeItemIV, mPicBean.getWidth(), mPicBean.getHeight());
            holder.mHomeItemIV.setTag(R.id.id_one, mPicBean);
            holder.mHomeItemIV.setOnClickListener(mProlistListener);
        } else if (temp instanceof PoProlistModel.ContentBean) {
            url = Constant.mHomePoUrl + ((PoProlistModel.ContentBean) temp).getUrl();
            if (TextUtils.isEmpty(url)) return;
            ViewGroup.LayoutParams layoutParams = holder.mHomeItemIV.getLayoutParams();
            layoutParams.width = mItemWidth;
            layoutParams.height = mItemWidth;
            holder.mHomeItemIV.setLayoutParams(layoutParams);
            holder.itemView.setPadding(3, 3, 3, 3);
            holder.mHomeItemIV.setTag(R.id.id_one, position);
            holder.mHomeItemIV.setOnClickListener(mDetailListener);
        }
        int index = (int) (Math.random() * mPlaceHolders.length);
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

    private void initCoverWH(ImageView img, int coverWidth, int coverHeight) {
        if (coverWidth == 0 || coverHeight == 0) return;
        android.widget.LinearLayout.LayoutParams coverImgParam = (android.widget.LinearLayout.LayoutParams) img.getLayoutParams();
        float itemWidth;
        //列表左右边距各位8 中间间距为7 8+7+8 = 23
        if (mType == 1) {
            itemWidth = (ScreenUtil.getWith(mContext) - ScreenUtil.dip2px(mContext, 24f));
        } else {
            itemWidth = (ScreenUtil.getWith(mContext) - ScreenUtil.dip2px(mContext, 34f)) / 2;
        }
        coverImgParam.width = (int) itemWidth;
        float scale = (itemWidth + 0f) / coverWidth;
        coverImgParam.height = (int) (coverHeight * scale);
        img.setLayoutParams(coverImgParam);
    }

    public void setProfileListId(int mImgId) {
        this.mImgId = mImgId;
    }

    public void setViewType(int mViewType) {
        mType = mViewType;
    }
}
