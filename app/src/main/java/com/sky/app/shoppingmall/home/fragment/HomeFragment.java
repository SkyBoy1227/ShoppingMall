package com.sky.app.shoppingmall.home.fragment;

import android.util.Log;
import android.view.View;

import com.sky.app.shoppingmall.R;
import com.sky.app.shoppingmall.base.BaseFragment;


/**
 * Created with Android Studio.
 * 描述: 主页面的Fragment
 * Date: 2018/7/26
 * Time: 16:07
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    public View initView() {
        Log.e(TAG, "主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页面的Fragment的数据被初始化了");
    }
}
