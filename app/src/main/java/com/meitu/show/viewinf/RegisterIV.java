package com.meitu.show.viewinf;

import com.meitu.show.model.RegisterModel;

public interface RegisterIV extends BaseCommonIV {

    void notifyRegisterResult(RegisterModel.DataBean data);
}
