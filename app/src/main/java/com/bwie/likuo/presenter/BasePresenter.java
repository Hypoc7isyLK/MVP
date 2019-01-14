package com.bwie.likuo.presenter;

import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/7
 * author:李阔(淡意衬优柔)
 * function:
 */
public abstract class BasePresenter {
    private DataCall dataCall;
    Exception e;


    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    protected abstract Observable observable(Object...args);

    public void request(Object...args){
        observable(args)
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(Observable upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Consumer<Result>() {

                    @Override
                    public void accept(Result result) throws Exception {
                        dataCall.success(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dataCall.fail(e);
                    }
                });

    }
}
