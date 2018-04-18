package com.example.edward.beijingnews.activitys;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.edward.beijingnews.R;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewpager;
    private Button btn_start_main;
//    private LinearLayout ll_point_group;
//    private ImageView iv_red_point;


    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
//        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
//        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };


        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合中
            imageViews.add(imageView);

//            //创建点
//            ImageView point = new ImageView(this);
//            point.setBackgroundResource(R.drawable.point_normal);
//            /**
//             * 单位是像数
//             * 把单位当成dp转成对应的像数
//             */
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
//            if(i !=0){
//                //不包括第0个，所有的点距离左边有10个像数
//                params.leftMargin = widthdpi;
//            }
//            point.setLayoutParams(params);
//            //添加到线性布局里面
//            ll_point_group.addView(point);
        }

        viewpager.setAdapter(new MyPagerAdapter());
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
