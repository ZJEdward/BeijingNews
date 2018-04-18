package com.example.edward.beijingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.edward.beijingnews.SplashActivity;

/**
 * Created by edward on 2018/4/18.
 *
 * @version $Rev$
 * @des ${TODO}
 */

public class CacheUtils {


    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
}
