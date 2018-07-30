package com.sky.app.shoppingmall.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sky.app.shoppingmall.R;
import com.sky.app.shoppingmall.base.BaseFragment;
import com.sky.app.shoppingmall.home.adapter.HomeFragmentAdapter;
import com.sky.app.shoppingmall.home.bean.ResultBeanData;
import com.sky.app.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


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

    /**
     * 返回的数据
     */
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;

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
        getDataFromNet();
    }

    /**
     * 联网请求数据
     */
    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "首页请求失败==" + e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response 请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "首页请求成功==" + response);
                        processData(response);
                    }
                });
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            // 有数据
            Log.e(TAG, "解析成功==" + resultBean.getHot_info().get(0).getName());
            // 设置适配器
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);
        } else {
            // 没有数据
        }
    }
}
