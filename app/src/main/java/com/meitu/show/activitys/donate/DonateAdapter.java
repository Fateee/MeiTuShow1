package com.meitu.show.activitys.donate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.meitu.show.BaseFragment;
import com.meitu.show.Constant.UrlConst;
import com.meitu.show.R;
import com.meitu.show.activitys.RegisterActivity;
import com.meitu.show.activitys.details.PhotoViewActivity;
import com.meitu.show.activitys.home.activity.CategoryListActivity;
import com.meitu.show.activitys.home.adapter.HomeViewHolder;
import com.meitu.show.activitys.profilelist.activity.ProfileListActivity;
import com.meitu.show.fragments.CategoryFragment;
import com.meitu.show.fragments.ChosenFragment;
import com.meitu.show.fragments.LatestFragment;
import com.meitu.show.model.BuyVipModel;
import com.meitu.show.model.CommonContentBean;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.PoProlistModel;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.model.eventbus.EventConst;
import com.meitu.show.model.eventbus.MessageEvent;
import com.meitu.show.utils.ScreenUtil;
import com.meitu.show.utils.UserInfoUtil;
import com.payelves.sdk.EPay;
import com.payelves.sdk.enums.EPayResult;
import com.payelves.sdk.listener.PayResultListener;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/4.
 */

public class DonateAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int[] mPlaceHolders = new int[]{R.color.CBBDEFB, R.color.CC5CAE9, R.color.CD8D8D8, R.color.CDCEDC8, R.color.CFDEFBA};
    private ArrayList<T> mDataList;
    private Context mContext;
//    private View.OnClickListener mProlistListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            CommonContentBean mPicBean = (CommonContentBean) v.getTag(R.id.id_one);
//            int imgId = mPicBean.getId();
//            String typeStr = "";
//            boolean islogin = UserInfoUtil.isUserLogin(mContext.getApplicationContext());
//            switch (mType) {
//                case BaseFragment.LATEST_TYPE:
//                    typeStr = LatestFragment.TAG;
//                    if(islogin) {
//                        ProfileListActivity.startActivity(mContext, mPicBean, mType);
//                    } else {
//                        RegisterActivity.startActivity(mContext);
//                    }
//                    break;
//                case BaseFragment.CATEGORY_TYPE:
//                    typeStr = CategoryFragment.TAG;
////                    CategoryListActivity.startActivity(mContext, mPicBean, mType);
//                    EPay.getInstance(mContext).pay("试用会员", "3元试用会员", 1, "", "", "", new PayResultListener() {
//                        @Override
//                        public void onFinish(Context context, Long payId, String orderId, String payUserId, EPayResult payResult, int payType, Integer amount) {
//                            EPay.getInstance(context).closePayView();//关闭快捷支付页面
//                            if(payResult.getCode() == EPayResult.SUCCESS_CODE.getCode()){
//                                //支付成功逻辑处理
//                                Toast.makeText(mContext, payResult.getMsg(), Toast.LENGTH_LONG).show();
//                            }else if(payResult.getCode() == EPayResult.FAIL_CODE.getCode()){
//                                //支付失败逻辑处理
//                                Toast.makeText(mContext, payResult.getMsg(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                    break;
//                case BaseFragment.CHOSEN_TYPE:
//                    typeStr = ChosenFragment.TAG;
//                    if(islogin) {
//                        ProfileListActivity.startActivity(mContext, mPicBean, mType);
//                    } else {
//                        RegisterActivity.startActivity(mContext);
//                    }
//                    break;
//                default:
//                    if (mContext instanceof CategoryListActivity)
//                        typeStr = CategoryListActivity.TAG;
//                    ProfileListActivity.startActivity(mContext, mPicBean, mType);
//                    break;
//            }
//
//            HashMap<String, String> map = new HashMap<>();
//            map.put("view_type", typeStr);
//            map.put("imgId", imgId + "");
//            map.put("islogin", islogin + "");
//            MobclickAgent.onEvent(mContext, "Click", map);
//        }
//    };

//    public void startActivity(Context mContext, int pos, ArrayList<T> mDataList) {
//        Intent intent = new Intent(mContext, PhotoViewActivity.class);
//        intent.putExtra(PhotoViewActivity.NOWPOS, pos);
//        intent.putExtra(PhotoViewActivity.DATALIST, mDataList);
//        mContext.startActivity(intent);
//    }

    public DonateAdapter(Context context) {
        mContext = context;
        mDataList = new ArrayList<>();
    }

    public DonateAdapter(Context context,ArrayList list) {
        mContext = context;
        mDataList = list;
    }

    @Override
    public int getItemViewType(int position) {
        T temp = mDataList.get(position);
        if (temp instanceof BuyVipModel) {
            return ((BuyVipModel) temp).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BuyVipModel.PRODUCT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donate_view, null);
                return new DonateHolder(view);
            case BuyVipModel.INFO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imageview, null);
                return new InfoHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donate_view, null);
        return new DonateHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T temp = mDataList.get(position);
        if (holder instanceof DonateHolder) {
            DonateHolder donateHolder = (DonateHolder) holder;
            if (temp instanceof BuyVipModel) {
                BuyVipModel buyVipModel = (BuyVipModel) temp;
                int days = buyVipModel.getDays();
                String info = buyVipModel.getProName();
                double price = buyVipModel.getPrice();
                if (days < 0) {
                    donateHolder.mVipProName.setText(info);
                } else {
                    donateHolder.mVipProName.setText(String.format(info,days));
                }
                donateHolder.mVipPrice.setText("¥"+String.valueOf(price));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
class DonateHolder extends RecyclerView.ViewHolder {

    TextView mVipProName;

    TextView mVipPrice;

    public DonateHolder(View itemView) {
        super(itemView);
        mVipProName = itemView.findViewById(R.id.tv_vip_pro_name);
        mVipPrice = itemView.findViewById(R.id.tv_vip_price);
    }
}
class InfoHolder extends RecyclerView.ViewHolder {

//    @BindView(R.id.iv_vip_info)
//    ImageView mVipInfoIV;

    public InfoHolder(View view) {
        super(view);
//        ButterKnife.bind(view);
    }
}
