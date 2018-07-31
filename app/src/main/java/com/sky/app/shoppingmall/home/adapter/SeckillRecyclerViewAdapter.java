package com.sky.app.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sky.app.shoppingmall.R;
import com.sky.app.shoppingmall.home.bean.ResultBeanData;
import com.sky.app.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created with Android Studio.
 * 描述: 秒杀的适配器
 * Date: 2018/7/31
 * Time: 11:27
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> datas;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 1.根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        // 2.绑定数据
        RequestOptions options = new RequestOptions()
                // 正在加载中的图片
                .placeholder(R.drawable.new_img_loading_2)
                // 加载失败的图片
                .error(R.drawable.new_img_loading_2)
                // 磁盘缓存策略
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        String imageUrl = Constants.BASE_URL_IMAGE + listBean.getFigure();
        Glide.with(mContext)
                // 图片地址
                .load(imageUrl)
                // 参数
                .apply(options)
                // 需要显示的ImageView控件
                .into(holder.iv_figure);
        holder.tv_cover_price.setText("￥" + listBean.getCover_price());
        holder.tv_origin_price.setText("￥" + listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);
        }
    }

}
