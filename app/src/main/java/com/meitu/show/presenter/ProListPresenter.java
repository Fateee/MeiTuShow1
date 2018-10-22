package com.meitu.show.presenter;

import android.text.TextUtils;

import com.meitu.show.Constant.UrlConst;
import com.meitu.show.model.ProlistModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetProlistRequest;
import com.meitu.show.viewinf.ProListViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/2/4.
 */

public class ProListPresenter extends BasePresenter<ProListViewInterface,ProlistModel> implements Callback<ProlistModel> {

    private final GetProlistRequest mRequestModel;

    private int pageNo = 1;

    public ProListPresenter() {

//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                //打印retrofit日志
//                Log.e("RetrofitLog","retrofitBack = "+message);
//            }
//        });
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .connectTimeout(5000, TimeUnit.SECONDS)
//                .readTimeout(5000, TimeUnit.SECONDS)
//                .writeTimeout(5000, TimeUnit.SECONDS)
//                .build();

//        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(AppConstant.mHomeUrl)/*.client(client)*/
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        mRequestModel = mRetrofit.create(GetProlistRequest.class);
        mRequestModel = initRetrofit(UrlConst.mHomeUrl,GetProlistRequest.class);
    }

    @Override
    public ProlistModel getModel() {
        return null;
    }

//    public void getProlistMeiTuList(boolean refresh,String url) {
//        if (TextUtils.isEmpty(url)) return;
//        if (refresh) pageNo = 1;
//        Map<String,String> param = new HashMap<>();
//        param.put("pageNum",String.valueOf(pageNo));
//        param.put("url",url);
//        Call<ProlistModel> requestCallback = mRequestModel.getProlistMeitu(param);
//        requestCallback.enqueue(this);
//    }

    private String mUrl;

    public void getProlistMeiTuList(boolean refresh,String url) {
        if (TextUtils.isEmpty(url)) return;
        mUrl = url;
        if (refresh) {
            if (getView() != null) getView().showLoading();
            pageNo = 1;
        }
        Map<String,String> param = new HashMap<>();
        param.put("pageNum",String.valueOf(pageNo));
        param.put("url",url);
        Call<ProlistModel> requestCallback = mRequestModel.getProlistMeitu(param);
        requestCallback.enqueue(this);
    }
    private List<ProlistModel.ProlistContent.DataDetail> previousList = new ArrayList<>();
    private List<ProlistModel.ProlistContent.DataDetail> allList = new ArrayList<>();
    @Override
    public void onResponse(Call<ProlistModel> call, Response<ProlistModel> response) {
        String status = response.body() != null ? response.body().getStatus() : "";
        if (!"OK".equals(status)) return;
        pageNo++;
        ProListViewInterface homeView = getView();
        ProlistModel.ProlistContent data = response.body().getData();
        if (pageNo == 2) {
            addDataLoadMore(data);
        } else {
            if (previousList != null && previousList.size() > 0 && data.getList() != null && data.getList().size() > 0) {
                String lastPageFirst = previousList.get(0).getImg();
                String nowPageFirst = data.getList().get(0).getImg();
                if (!TextUtils.isEmpty(lastPageFirst) && !TextUtils.isEmpty(nowPageFirst)) {
                    //拿当前这一页数据和上一页数据的第一个比较 相同则说明加载完成
                    if (!lastPageFirst.equals(nowPageFirst)) {
                        addDataLoadMore(data);
                    } else {
                        if (homeView != null ) {
                            homeView.dismissLoading();
                            homeView.notifyListUiWithData(allList);
                        }
                    }
                } else {
                    if (homeView != null ) {
                        homeView.dismissLoading();
                        homeView.showErrorView();
                    }
                }
            } else {
                if (homeView != null ) {
                    homeView.dismissLoading();
                    homeView.showErrorView();
                }
            }
        }
//        if (homeView != null ) {
////            homeView.notifyListUiWithData(data.getList(),pageNo > 1 ? false:true);
//            //拿当前这一页数据和上一页数据的第一个比较 相同则说明加载完成
//        }
    }

    private void addDataLoadMore(ProlistModel.ProlistContent data) {
        previousList = data.getList();
        allList.addAll(previousList);
        getProlistMeiTuList(false,mUrl);
    }

    @Override
    public void onFailure(Call<ProlistModel> call, Throwable t) {

    }
}
