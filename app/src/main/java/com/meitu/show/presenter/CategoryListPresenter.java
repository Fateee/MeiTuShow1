package com.meitu.show.presenter;

import com.meitu.show.Constant.UrlConst;
import com.meitu.show.model.CategoryListModel;
import com.meitu.show.model.CommonContentBean;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.request.GetCategoryRequest;
import com.meitu.show.viewinf.HomeViewInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2018/1/18.
 */

public class CategoryListPresenter extends BasePresenter<HomeViewInterface, CategoryListModel> implements Callback<CategoryListModel> {

    private final GetCategoryRequest mRequestModel;
    private int pageNo = 1;
    private int pageNum = 10;

    public CategoryListPresenter() {
        super();
        mRequestModel = initRetrofit(UrlConst.mHomePoUrl, GetCategoryRequest.class);
    }


    @Override
    public CategoryListModel getModel() {
        return new CategoryListModel();
    }

    public void getCategoryMeiTuList(boolean refresh, int id) {
        if (refresh) {
            if (getView() != null) getView().showLoading();
            pageNo = 1;
        }
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        Call<CategoryListModel> requestCallback = mRequestModel.getCategoryList(param);
        requestCallback.enqueue(this);
    }

    @Override
    public void onResponse(Call<CategoryListModel> call, Response<CategoryListModel> response) {
        String status = response.body() != null ? response.body().getCode() : "";
        if (!"0".equals(status)) return;
        pageNo++;
        HomeViewInterface homeView = getView();
        List<CommonContentBean> dataList = response.body().getContent();
        if (homeView != null) {
            homeView.dismissLoading();
            homeView.notifyHomeUiWithData(dataList, pageNo > 2 ? false : true);
        }
    }

    @Override
    public void onFailure(Call<CategoryListModel> call, Throwable t) {

    }

}
