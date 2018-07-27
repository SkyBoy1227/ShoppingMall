package com.sky.app.shoppingmall.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;

    @Override
    public View initView() {
        Log.e(TAG, "主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        initListener();
        return view;

    }

    /**
     * 设置点击事件
     */
    private void initListener() {
        // 置顶的监听
        ib_top.setOnClickListener(v -> {
            // 回到顶部
            rvHome.scrollToPosition(0);
        });
        // 搜索的监听
        tv_search_home.setOnClickListener(v -> Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show());
        // 消息的监听
        tv_message_home.setOnClickListener(v -> Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页面的Fragment的数据被初始化了");
    }
}
