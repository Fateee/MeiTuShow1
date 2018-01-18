package com.meitu.show.presenter;

import com.meitu.show.model.HomeMeituModel;
import com.meitu.show.presenter.base.BasePresenter;
import com.meitu.show.viewinf.HomeViewInterface;

/**
 * Created by Administrator on 2018/1/18.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface,HomeMeituModel> {
    @Override
    public HomeMeituModel getModel() {
        return new HomeMeituModel();
    }

    public void requestHomeUrl(String url) {

    }
}
