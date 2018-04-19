package com.example.edward.beijingnews.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.edward.beijingnews.R;
import com.example.edward.beijingnews.base.BaseFragment;
import com.example.edward.beijingnews.utils.LogUtil;

/**
 * Created by edward on 2018/4/19.
 *
 * @version $Rev$
 * @des ${TODO}
 */

public class ContentFragment extends BaseFragment {

    private ViewPager viewPager;
    private RadioGroup rg_main;


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.content_fragment,null);
        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
//        x.view().inject(ContentFragment.this,view);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("-----------");

        rg_main.check(R.id.rb_home);

    }
}
