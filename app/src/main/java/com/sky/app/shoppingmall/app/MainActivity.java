package com.sky.app.shoppingmall.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.sky.app.shoppingmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * 描述: 主页面
 * Date: 2018/7/26
 * Time: 14:24
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // ButterKnife和当前Activity绑定
        rgMain.check(R.id.rb_home);
    }
}
