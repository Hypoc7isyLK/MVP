package com.bwie.likuo.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bwie.likuo.BannerFragment_ViewPager_Adapter;
import com.bwie.likuo.R;
import com.bwie.likuo.bean.BannerBean;
import com.bwie.likuo.bean.Result;
import com.bwie.likuo.core.DataCall;
import com.bwie.likuo.presenter.BannerPresenter;
import com.bwie.likuo.util.ZoomOutPageTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.bannerfragment_viewpager)
    ViewPager bannerfragmentViewpager;
    @BindView(R.id.llllllll)
    LinearLayout llllllll;
    private BannerPresenter mBannerPresenter;
    private List<BannerBean> mResult;
    private BannerFragment_ViewPager_Adapter mBannerFragment_viewPager_adapter;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = bannerfragmentViewpager.getCurrentItem();
            item++;
            bannerfragmentViewpager.setCurrentItem(item);
            mHandler.sendEmptyMessageDelayed(1,2000);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        unbinder = ButterKnife.bind(this, view);

        initDa();


        initData();
        return view;
    }

    private void initDa() {
        mBannerPresenter = new BannerPresenter(new BannerCall());
        mBannerPresenter.request();
    }

    private void initData() {

    }

    //解绑
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //获取数据

    private class BannerCall implements DataCall<Result<List<BannerBean>>> {
        @Override
        public void success(Result<List<BannerBean>> data) {
            mResult = data.getResult();
            //设置adapter
            mBannerFragment_viewPager_adapter = new BannerFragment_ViewPager_Adapter(getActivity(), mResult);
            bannerfragmentViewpager.setAdapter(mBannerFragment_viewPager_adapter);
            bannerfragmentViewpager.setPageMargin(20);
            bannerfragmentViewpager.setOffscreenPageLimit(5);
            bannerfragmentViewpager.setPageTransformer(true, new ZoomOutPageTransformer());

            bannerfragmentViewpager.setCurrentItem(1);


            bannerfragmentViewpager.setCurrentItem(5000);
            mHandler.sendEmptyMessageDelayed(1,2000);
            llllllll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return bannerfragmentViewpager.dispatchTouchEvent(motionEvent);
                }
            });
        }

        @Override
        public void fail(Exception e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBannerPresenter=null;
    }
}
