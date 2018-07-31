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

/**
 * Created with Android Studio.
 * 描述: 频道的适配器
 * Date: 2018/7/30
 * Time: 17:51
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class ChannelAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;
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
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            holder = new ViewHolder();
            holder.ivIcon = convertView.findViewById(R.id.iv_channel);
            holder.tvTitle = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 根据位置得到对应的数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        RequestOptions options = new RequestOptions()
                // 磁盘缓存策略
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        String imageUrl = Constants.BASE_URL_IMAGE + channelInfoBean.getImage();
        Glide.with(mContext)
                // 图片地址
                .load(imageUrl)
                // 参数
                .apply(options)
                // 需要显示的ImageView控件
                .into(holder.ivIcon);
        holder.tvTitle.setText(channelInfoBean.getChannel_name());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}
