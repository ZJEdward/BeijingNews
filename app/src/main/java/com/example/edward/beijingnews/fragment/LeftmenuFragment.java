package com.example.edward.beijingnews.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edward.beijingnews.R;
import com.example.edward.beijingnews.activitys.MainActivity;
import com.example.edward.beijingnews.base.BaseFragment;
import com.example.edward.beijingnews.domian.NewsCenterPagerBean;
import com.example.edward.beijingnews.utils.DensityUtil;
import com.example.edward.beijingnews.utils.LogUtil;

import java.util.List;

/**
 * Created by edward on 2018/4/19.
 *
 * @version $Rev$
 * @des ${TODO}
 */

public class LeftmenuFragment extends BaseFragment {


    private List<NewsCenterPagerBean.DataBean> data;

    private ListView listView;

    private LeftmenuFragmentAdapter adapter;

    /**
     * 点击的位置
     */
    private int prePosition;

    @Override
    public View initView() {

        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dip2px(context,40),0,0);
        listView.setDividerHeight(0);//设置分割线高度为0
        listView.setCacheColorHint(Color.TRANSPARENT);

        //设置按下listView的item不变色
        listView.setSelector(android.R.color.transparent);


        //设置item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.记录点击的位置，变成红色
                prePosition = position;
                adapter.notifyDataSetChanged();//getCount()-->getView

                //2.把左侧菜单关闭
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();//关<->开

                //3.切换到对应的详情页面：新闻详情页面，专题详情页面，图组详情页面，互动详情页面
//                swichPager(prePosition);

            }
        });



        return listView;
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("-----------");


    }

    public void setData(List<NewsCenterPagerBean.DataBean> data) {

        this.data = data;
        for(int i=0;i<data.size();i++){
            LogUtil.e("title=="+data.get(i).getTitle());
        }

        //设置适配器
        adapter   = new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);
//
//        //设置默认页面
//        swichPager(prePosition);
    }

    class LeftmenuFragmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu,null);
            textView.setText(data.get(position).getTitle());
            //            if(position==prePosition){
            //                //设置红色
            //                textView.setEnabled(true);
            //
            //            }else{
            //                textView.setEnabled(false);
            //            }

            textView.setEnabled(position==prePosition);
            return textView;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }
}
