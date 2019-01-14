package com.bwie.likuo.presenter;

import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.core.net.IRetrofit;
import com.bwie.likuo.core.net.NetworkManager;

import io.reactivex.Observable;

/**
 * date:2019/1/7
 * author:李阔(淡意衬优柔)
 * function:
 */
public class LoginPresenter extends BasePresenter {
    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRetrofit iRetrofit = NetworkManager.instance().create(IRetrofit.class);
        return iRetrofit.showLogin((String) args[0],(String)args[1]);
    }
}
