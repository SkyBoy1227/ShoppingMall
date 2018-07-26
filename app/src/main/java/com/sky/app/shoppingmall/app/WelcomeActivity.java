package com.sky.app.shoppingmall.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sky.app.shoppingmall.R;

/**
 * Created with Android Studio.
 * 描述: 欢迎页
 * Date: 2018/7/26
 * Time: 11:40
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 两秒钟进入主页面
        new Handler().postDelayed(() -> {
            // 执行在主线程
            // 启动主页面
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            // 关闭当前页面
            finish();
        }, 2000);
    }
}
