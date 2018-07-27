package com.sky.app.shoppingmall.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sky.app.shoppingmall.base.BaseFragment;

/**
 * Created with Android Studio.
 * 描述: 发现的Fragment
 * Date: 2018/7/26
 * Time: 16:07
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class CommunityFragment extends BaseFragment {
    private static final String TAG = CommunityFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG, "发现的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "发现的Fragment的数据被初始化了");
        textView.setText("发现内容");
    }
}
