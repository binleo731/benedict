package com.jv.benedict.ui;

/**
 * Created by Leo on 2018/1/4.
 */

public interface IView<T> {
  void setPresenter(T presenter);
  //判断fragment 是否在activity上，防止内存泄漏
  boolean isActive();
  void showLoading();
  void hideLoading();
}
