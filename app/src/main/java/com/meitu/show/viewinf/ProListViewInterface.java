package com.meitu.show.viewinf;


import com.meitu.show.model.ProlistModel;
import com.meitu.show.presenter.base.BaseViewInf;

import java.util.List;


/**
 * Created by Administrator on 2018/2/4.
 */

public interface ProListViewInterface extends BaseViewInf {
    void showLoading();
    void dismissLoading();
    void showErrorView();
    void notifyListUiWithData(List<ProlistModel.ProlistContent.DataDetail> list, boolean refresh);
    void notifyListUiWithData(List<ProlistModel.ProlistContent.DataDetail> list);
}
