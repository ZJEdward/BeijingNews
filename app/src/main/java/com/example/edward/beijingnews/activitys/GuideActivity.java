package com.example.edward.beijingnews.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.edward.beijingnews.R;
import com.example.edward.beijingnews.SplashActivity;
import com.example.edward.beijingnews.utils.CacheUtils;
import com.example.edward.beijingnews.utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;


    private ArrayList<ImageView> imageViews;

    /**
     * 两点的间距
     */
    private int leftmax;

    private int widthdpi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };


        widthdpi = DensityUtil.dip2px(this,10);

        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中
            imageViews.add(imageView);

            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            /**
             * 单位是像数
             * 把单位当成dp转成对应的像数
             */
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if(i !=0){
                //不包括第0个，所有的点距离左边有10个像数
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局里面
            ll_point_group.addView(point);
        }

        viewpager.setAdapter(new MyPagerAdapter());

        //获得 leftmax
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //得到屏幕滑动的百分比
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置按钮的点击事件
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1.保存曾经进入过主页面
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);

                //2.跳转到主页面
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);

                //3.关闭引导页面
                finish();
            }
        });

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面回调了会回调这个方法
         * @param position 当前滑动页面的位置
         * @param positionOffset 页面滑动的百分比
         * @param positionOffsetPixels 滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            //两点间移动的距离 = 屏幕滑动百分比 * 间距
            //            int leftmargin = (int) (positionOffset * leftmax);
            //            Log.e(TAG,"position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);
            //两点间滑动距离对应的坐标 = 原来的起始位置 +  两点间移动的距离
            int leftmargin = (int) (position*leftmax +  (positionOffset * leftmax));
            //params.leftMargin = 两点间滑动距离对应的坐标

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);

        }

        /**
         * 当页面被选中的时候，回调这个方法
         * @param position 被选中页面的对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if(position==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                //其他页面
                btn_start_main.setVisibility(View.GONE);
            }

        }

        /**
         * 当ViewPager页面滑动状态发生变化的时候
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {

            //执行不只一次
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(MyOnGlobalLayoutListener.this);

            //            间距  = 第1个点距离左边的距离 - 第0个点距离左边的距离
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();

        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            ImageView imageView   = imageViews.get(position);
            //添加到容器中
            container.addView(imageView);
            return  imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
