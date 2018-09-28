package com.meitu.show.viewinf;

import com.meitu.show.model.CommonContentBean;
import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.model.PoMeiTuModel;
import com.meitu.show.presenter.base.BaseViewInf;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface HomeViewInterface extends BaseViewInf {
    void showLoading();
    void dismissLoading();
    void showErrorView();
//    void notifyHomeUiWithData(List<HomeMeituModel.Content.DataDetail> list,boolean refresh);
    void notifyHomeUiWithData(List<CommonContentBean> list, boolean refresh);
}
