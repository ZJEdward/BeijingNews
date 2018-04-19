package com.example.edward.beijingnews.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.edward.beijingnews.base.BaseFragment;
import com.example.edward.beijingnews.utils.LogUtil;

/**
 * Created by edward on 2018/4/19.
 *
 * @version $Rev$
 * @des ${TODO}
 */

public class LeftmenuFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {

        textView = new TextView(context);
        textView.setTextSize(23);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("-----------");

        textView.setText("左侧菜单");
    }
}
