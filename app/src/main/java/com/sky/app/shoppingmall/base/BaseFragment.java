package com.sky.app.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with Android Studio.
 * 描述: 基类Fragment
 * Date: 2018/7/26
 * Time: 15:51
 * 首页：HomeFragment
 * 分类：TypeFragment
 * 发现：CommunityFragment
 * 购物车：ShoppingCartFragment
 * 用户中心：UserFragemnt
 * 等等都要继承该类
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    /**
     * 当该类被系统创建的时候被回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象类，由孩子实现，实现不同的效果
     *
     * @return
     */
    public abstract View initView();

    /**
     * 当Activity被创建了的时候回调这个方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要联网请求数据的时候，可以重写该方法，在该方法中联网请求
     */
    public void initData() {

    }
}
