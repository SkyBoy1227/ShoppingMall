package com.sky.app.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sky.app.shoppingmall.R;
import com.sky.app.shoppingmall.home.bean.ResultBeanData;
import com.sky.app.shoppingmall.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * 描述: 推荐的适配器
 * Date: 2018/7/31
 * Time: 17:03
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class RecommendAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> datas;

    public RecommendAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 根据位置得到对应的数据
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        RequestOptions options = new RequestOptions()
                // 正在加载中的图片
                .placeholder(R.drawable.new_img_loading_2)
                // 加载失败的图片
                .error(R.drawable.new_img_loading_2)
                // 磁盘缓存策略
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        String imageUrl = Constants.BASE_URL_IMAGE + recommendInfoBean.getFigure();
        Glide.with(mContext)
                // 图片地址
                .load(imageUrl)
                // 参数
                .apply(options)
                // 需要显示的ImageView控件
                .into(holder.ivRecommend);
        holder.tvName.setText(recommendInfoBean.getName());
        holder.tvPrice.setText("￥" + recommendInfoBean.getCover_price());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
