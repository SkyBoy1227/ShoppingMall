package com.sky.app.shoppingmall.utils;

import android.content.Context;

/**
 * Created with Android Studio.
 * 描述: dp、px转换工具类
 * Date: 2018/7/31
 * Time: 9:35
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
