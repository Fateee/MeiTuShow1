package com.meitu.show.view;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.meitu.show.Constant.UrlConst;
import com.meitu.show.R;
import com.meitu.show.model.PoProlistModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyi on 18/2/9.
 * email: huyi@codoon.com
 * author: huyi
 */

public class MyImageAdapter<T> extends PagerAdapter {

    public static final String TAG = MyImageAdapter.class.getSimpleName();
    private List<T> mDataList;
    private AppCompatActivity activity;
    private int[] mPlaceHolders = new int[] {R.color.CBBDEFB,R.color.CC5CAE9,R.color.CD8D8D8,R.color.CDCEDC8,R.color.CFDEFBA};

    public MyImageAdapter(ArrayList<T> dataList, AppCompatActivity activity) {
        this.mDataList = dataList;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object temp = mDataList.get(position);
        String url = "";
        if (temp instanceof PoProlistModel.ContentBean) {
            url = UrlConst.mHomePoUrl+((PoProlistModel.ContentBean)temp).getUrl();
        }
        PhotoView photoView = new PhotoView(activity);
        //显示图片
        int index=(int)(Math.random()*mPlaceHolders.length);
        int nowHolder = mPlaceHolders[index];
        Glide.with(activity).load(url).placeholder(nowHolder).into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
