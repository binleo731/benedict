package com.jv.benedict.utils;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Glide图片加载类
 * Created by wj on 2017/7/25 14:07
 */

public class GlideImageLoader {
    /**
     * 最基本（加载网络图片）
     */
    public static void with(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))// 解决Glide缓存问题（url不变图片不更新，只会从缓存里拿）
                .into(imageView);
    }

    /**
     * 传fragment对象
     * 最基本（加载网络图片）
     */
    public static void with(Fragment fragment, String imageUrl, ImageView imageView) {
        Glide.with(fragment)
                .load(imageUrl)
                .into(imageView);
    }

    /**
     * 最基本（加载本地图片）
     */
    public static void with(Context context, int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .into(imageView);
    }

    /**
     * 传fragment对象
     * 最基本（加载本地图片）
     */
    public static void with(Fragment fragment, int resourceId, ImageView imageView) {
        Glide.with(fragment)
                .load(resourceId)
                .into(imageView);
    }

    /**
     * 最基本（加载Uri）
     */
    public static void with(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .into(imageView);
    }

    /**
     * 最基本（加载File）
     */
    public static void with(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .into(imageView);
    }

    /**
     * 有优先级
     */
    public static void with(Context context, String imageUrl, Priority priority, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .priority(priority)
                .into(imageView);
    }

    /**
     * 有缓存
     */
    public static void with(Context context, String imageUrl, DiskCacheStrategy diskCacheStrategy, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(diskCacheStrategy)
                .into(imageView);
    }

    /**
     * 指定高宽（裁剪）
     */
    public static void with(Context context, String imageUrl, int width, int height, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .override(width, height)
                .fitCenter()
                .into(imageView);
    }

    /**
     * 有缓存，有裁剪（裁剪类型可以需要在ImageLoader里改）
     */
    public static void with(Context context, String imageUrl, DiskCacheStrategy diskCacheStrategy, int width, int height, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(diskCacheStrategy)//设置缓存
                .override(width, height)//图片裁剪，如果太模糊就把宽高改大点
                .fitCenter()//设置裁剪类型
                .into(imageView);
    }
}
