package com.example.edward.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.edward.beijingnews.activitys.MainActivity;
import com.example.edward.beijingnews.base.BasePager;
import com.example.edward.beijingnews.domian.NewsCenterPagerBean;
import com.example.edward.beijingnews.fragment.LeftmenuFragment;
import com.example.edward.beijingnews.utils.CacheUtils;
import com.example.edward.beijingnews.utils.Constants;
import com.example.edward.beijingnews.utils.LogUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edward on 2018/4/19.
 *
 * @version $Rev$
 * @des ${TODO}
 */

public class NewsCenterPager extends BasePager {


    private long startTime;
    private List<NewsCenterPagerBean.DataBean> data;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("新闻中心数据被初始化了..");

        ib_menu.setVisibility(View.VISIBLE);
        //1.设置标题
        tv_title.setText("新闻中心");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("新闻中心内容");


        startTime = SystemClock.uptimeMillis();

        getDataFromNet();

    }

    /**
     * 使用xUtils3联网请求数据
     */
    private void getDataFromNet() {

        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                long endTime = SystemClock.uptimeMillis();
                long passTime = endTime - startTime;

                LogUtil.e("xUtils3--passTime==" + passTime);

                LogUtil.e("使用xUtils3联网请求成功==" + result);

//                //缓存数据
//                CacheUtils.putString(context,Constants.NEWSCENTER_PAGER_URL,result);
//
                processData(result);
//                //设置适配器


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("使用xUtils3联网请求失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("使用xUtils3-onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("使用xUtils3-onFinished");
            }
        });

    }

    /**
     * 解析json数据和显示数据
     *
     * @param json
     */
    private void processData(String json) {

        NewsCenterPagerBean bean = parsedJson(json);

        String title = bean.getData().get(0).getChildren().get(1).getTitle();

        LogUtil.e("title ----------------"+title);

        //给左侧菜单传递数据
        data = bean.getData();

        MainActivity mainActivity = (MainActivity) context;

        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        //把数据传递给左侧菜单
        leftmenuFragment.setData(data);

    }

    private NewsCenterPagerBean parsedJson(String json) {

        Gson gson = new Gson();
        NewsCenterPagerBean bean = gson.fromJson(json,NewsCenterPagerBean.class);
        return bean;
    }
}
