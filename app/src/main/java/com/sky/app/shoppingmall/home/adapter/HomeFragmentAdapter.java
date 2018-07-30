package com.sky.app.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sky.app.shoppingmall.home.bean.ResultBeanData;

/**
 * Created with Android Studio.
 * 描述: ${DESCRIPTION}
 * Date: 2018/7/30
 * Time: 10:56
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;

    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;

    /**
     * 活动类型
     */
    public static final int ACT = 2;

    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;

    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;

    /**
     * 热卖类型
     */
    public static final int HOT = 5;

    private Context mContext;

    /**
     * 数据
     */
    private ResultBeanData.ResultBean resultBean;

    /**
     * 当前类型
     */
    private int currentType;

    /**
     * 用来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean) {
        this.mContext = context;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
            default:
                break;
        }
        return currentType;
    }
}
