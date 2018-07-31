package com.sky.app.shoppingmall.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sky.app.shoppingmall.R;
import com.sky.app.shoppingmall.home.bean.ResultBeanData;
import com.sky.app.shoppingmall.utils.Constants;
import com.sky.app.shoppingmall.utils.DensityUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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

    /**
     * 相当于getView 创建ViewHolder部分代码
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }
        return null;
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter adapter;
        private long dt;

        @SuppressLint("HandlerLeak")
        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt -= 1000;
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                String time = dateFormat.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt <= 0) {
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            // 1.得到数据了
            // 2.设置数据：文本和RecyclerView的数据
            adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info.getList());
            rv_seckill.setAdapter(adapter);

            // 设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            // 设置item的点击事件
            adapter.setOnSeckillRecyclerView(position -> Toast.makeText(mContext, "秒杀 = " + position, Toast.LENGTH_SHORT).show());

            // 秒杀倒计时 - 毫秒
            dt = Integer.valueOf(seckill_info.getEnd_time()) - Integer.valueOf(seckill_info.getStart_time());
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ViewPager viewPager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            viewPager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            viewPager.setPageMargin(DensityUtil.dip2px(mContext, 20));
            // >=3
            viewPager.setOffscreenPageLimit(3);

            // setPageTransformer 决定动画效果
            viewPager.setPageTransformer(true, new AlphaPageTransformer(new ScaleInTransformer()));
            // 1.有数据了
            // 2.设置适配器
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    // 添加到容器中
                    container.addView(imageView);
                    RequestOptions options = new RequestOptions()
                            // 磁盘缓存策略
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    String imageUrl = Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url();
                    Glide.with(mContext)
                            // 图片地址
                            .load(imageUrl)
                            // 参数
                            .apply(options)
                            // 需要显示的ImageView控件
                            .into(imageView);
                    // 设置点击事件
                    imageView.setOnClickListener(v -> Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show());
                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gridView;
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.gridView = itemView.findViewById(R.id.gv_channel);
            // 设置item的点击事件
            gridView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(mContext, "position = " + position, Toast.LENGTH_SHORT).show());
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            // 得到数据了
            // 设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gridView.setAdapter(adapter);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
            banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            // 设置Banner的数据
            // 得到图片集合地址
            List<String> imageUrls = new ArrayList<>();
            for (ResultBeanData.ResultBean.BannerInfoBean infoBean : banner_info) {
                String imageUrl = infoBean.getImage();
                imageUrls.add(imageUrl);
            }
            banner.setImages(imageUrls);
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    RequestOptions options = new RequestOptions()
                            // 磁盘缓存策略
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    String imageUrl = Constants.BASE_URL_IMAGE + path;
                    Glide.with(context)
                            // 图片地址
                            .load(imageUrl)
                            // 参数
                            .apply(options)
                            // 需要显示的ImageView控件
                            .into(imageView);
                }
            });
            // 设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            // 设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            // 设置item的点击事件
            banner.setOnBannerListener(position -> Toast.makeText(mContext, "position == " + position, Toast.LENGTH_SHORT).show());
            banner.start();
        }
    }

    /**
     * 相当于getview中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }
    }

    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 4;
    }

    /**
     * 得到类型
     *
     * @param position
     * @return
     */
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
