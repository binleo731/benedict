package com.jv.benedict.ui;

/**
 * MVP中V层的基类
 * Created by wj on 2017/12/28 17:35
 */
public interface BaseView {

    // 展示等待加载页面
    void showLoading();

    // 隐藏等待加载页面
    void hideLoading();
}
