package com.example.edward.beijingnews.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.edward.beijingnews.R;
import com.example.edward.beijingnews.activitys.MainActivity;
import com.example.edward.beijingnews.adapter.ContentFragmentAdapter;
import com.example.edward.beijingnews.base.BaseFragment;
import com.example.edward.beijingnews.base.BasePager;
import com.example.edward.beijingnews.pager.GovaffairPager;
import com.example.edward.beijingnews.pager.HomePager;
import com.example.edward.beijingnews.pager.NewsCenterPager;
import com.example.edward.beijingnews.pager.SettingPager;
import com.example.edward.beijingnews.pager.SmartServicePager;
import com.example.edward.beijingnews.utils.LogUtil;
import com.example.edward.beijingnews.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by edward on 2018/4/19.
 *
 * @version $Rev$
 * @des ${TODO}
 */

public class ContentFragment extends BaseFragment {

    //2.初始化控件
    @ViewInject(R.id.viewpager)
    private NoScrollViewPager  viewPager;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    /**
     * 装五个页面的集合
     */
    private ArrayList<BasePager> basePagers;


    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.content_fragment,null);

//        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);

        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this,view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        LogUtil.e("-----------");



        //初始化五个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));//主页面
        basePagers.add(new NewsCenterPager(context));//新闻中心页面
        basePagers.add(new SmartServicePager(context));//智慧服务页面
        basePagers.add(new GovaffairPager(context));//政要指南页面
        basePagers.add(new SettingPager(context));//设置中心面

        //设置ViewPager的适配器
        viewPager.setAdapter(new ContentFragmentAdapter(basePagers));

        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听某个页面被选中，初始对应的页面的数据
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());


        //设置默认选中首页
        rg_main.check(R.id.rb_home);

        basePagers.get(0).initData();

    }

    /**
     * 得到新闻中心
     * @return
     */
    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) basePagers.get(1);
    }

    /**
     * 监听ViewPager改变 防止 ViewPager预加载数据
     */
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中的时候回调这个方法
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            //            BasePager basePager = basePagers.get(position);
            //调用被选中的页面的initData方法
            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        /**
         *
         * @param group RadioGroup
         * @param checkedId 被选中的RadioButton的id
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home://主页radioButton的id
                    viewPager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter://新闻中心radioButton的id
                    viewPager.setCurrentItem(1,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice://智慧服务radioButton的id
                    viewPager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair://政要指南的RadioButton的id
                    viewPager.setCurrentItem(3,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting://设置中心RadioButton的id
                    viewPager.setCurrentItem(4,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }

        }
    }

    /**
     根据传人的参数设置是否让SlidingMenu可以滑动
     */
    private void isEnableSlidingMenu(int touchmodeFullscreen) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);
    }

//    class ContentFragmentAdapter  extends PagerAdapter {
//
//
//        @Override
//        public int getCount() {
//            return basePagers.size();
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            BasePager basePager = basePagers.get(position);//各个页面的实例
//            View rootView = basePager.rootView;//各个子页面
//            //调用各个页面的initData()
////            basePager.initData();//初始化数据
//            container.addView(rootView);
//            return rootView;
//        }
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }
}
