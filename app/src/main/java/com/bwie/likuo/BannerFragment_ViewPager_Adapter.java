package com.bwie.likuo;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.likuo.bean.BannerBean;

import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;


public class BannerFragment_ViewPager_Adapter extends PagerAdapter {
    Context context;
    List<BannerBean> mListResult;

    public BannerFragment_ViewPager_Adapter(Context context, List<BannerBean> result) {
        this.context = context;
        mListResult = result;
    }

    /*public void reset(List<HomeFragment_BannerBean> result) {
        mListResult.clear();
        mListResult.addAll(result);
        notifyDataSetChanged();
    }*/

    @Override
    public int getCount() {
        return mListResult.size()>0 ?Integer.MAX_VALUE :0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.bannerfragment_viewpager_ban_item,null);
        SimpleDraweeView homefragment_viewpager_sim = view.findViewById(R.id.bannerfragment_viewpager_sim);

        homefragment_viewpager_sim.setImageURI(Uri.parse(mListResult.get(position%mListResult.size()).getImageUrl()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
