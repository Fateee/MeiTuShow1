package com.meitu.show.viewinf;

import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.presenter.base.BaseViewInf;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface HomeViewInterface extends BaseViewInf {

    void notifyHomeUiWithData(List<HomeMeituModel.Content.DataDetail> list);
}
