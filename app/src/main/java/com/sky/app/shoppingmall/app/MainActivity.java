package com.sky.app.shoppingmall.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.sky.app.shoppingmall.R;
import com.sky.app.shoppingmall.base.BaseFragment;
import com.sky.app.shoppingmall.community.fragment.CommunityFragment;
import com.sky.app.shoppingmall.home.fragment.HomeFragment;
import com.sky.app.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.sky.app.shoppingmall.type.fragment.TypeFragment;
import com.sky.app.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

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

    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // ButterKnife和当前Activity绑定
        initFragment();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_home:
                    // 主页
                    position = 0;
                    break;
                case R.id.rb_type:
                    // 分类
                    position = 1;
                    break;
                case R.id.rb_community:
                    // 发现
                    position = 2;
                    break;
                case R.id.rb_cart:
                    // 购物车
                    position = 3;
                    break;
                case R.id.rb_user:
                    // 用户中心
                    position = 4;
                    break;
                default:
                    position = 0;
                    break;
            }
            BaseFragment baseFragment = getFragment(position);
            switchFragment(tempFragment, baseFragment);
        });
        rgMain.check(R.id.rb_home);
    }

    /**
     * 切换Fragment
     *
     * @param fromFragment 上次显示的Fragment
     * @param nextFragment 当前正要显示的Fragment
     */
    private void switchFragment(Fragment fromFragment, Fragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (tempFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    // 隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    // 添加Fragment
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    // 隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    /**
     * 根据位置取不同的Fragment
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    /**
     * 初始化Fragment
     * 添加的时候要按照顺序
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }
}
