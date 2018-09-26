package com.meitu.show.presenter;

import com.meitu.show.Constant;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetHomeRequest;
import com.meitu.show.viewinf.HomeViewInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosenPresenter extends BasePresenter<HomeViewInterface,PoMeiTuModel> implements Callback<PoMeiTuModel> {

    private int pageNo = 1;
    private int pageNum = 10;
    private final GetHomeRequest mRequestModel;

    public ChosenPresenter() {
        super();
        mRequestModel = initRetrofit(Constant.mHomePoUrl,GetHomeRequest.class);
    }

    @Override
    public PoMeiTuModel getModel() {
        return new PoMeiTuModel();
    }

    //https://www.poshow18.com/spacial/list?type=2&key=views&size=10&index=1
    public void getChosenMeiTuList(boolean refresh) {
        if (refresh) {
            if (getView() != null) getView().showLoading();
            pageNo = 1;
        }
        Map<String,String> param = new HashMap<>();
        param.put("type","2");
        param.put("key","views");
        param.put("size",String.valueOf(pageNum));
        param.put("index",String.valueOf(pageNo));
        Call<PoMeiTuModel> requestCallback = mRequestModel.getPoMeitu(param);
        requestCallback.enqueue(this);
    }

    @Override
    public void onResponse(Call<PoMeiTuModel> call, Response<PoMeiTuModel> response) {
        String status = response.body() != null ? response.body().getCode() : "";
        if (!"0".equals(status)) return;
        pageNo++;
        HomeViewInterface homeView = getView();
        List<PoMeiTuModel.ContentBean> dataList = response.body().getContent();
        if (homeView != null ) {
            homeView.dismissLoading();
            homeView.notifyHomeUiWithData(dataList,pageNo > 2 ? false:true);
        }
    }

    @Override
    public void onFailure(Call<PoMeiTuModel> call, Throwable t) {

    }
}