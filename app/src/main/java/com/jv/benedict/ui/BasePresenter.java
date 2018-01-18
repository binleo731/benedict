package com.jv.benedict.ui;

/**
 * MVP中P层基类
 * Created by wj on 2017/12/28 17:34
 * 泛型用于指定绑定的View必须继承自BaseView
 */
public abstract class BasePresenter<V extends BaseView>{
    private V mMvpView;

    /**
     * 绑定V层
     */
    public void attachMvpView(V view) {
        this.mMvpView = view;
    }

    /**
     * 解除绑定V层
     */
    public void detachMvpView() {
        mMvpView = null;
    }

    /**
     * 获取V层
     *
     * @return V
     */
    public V getMvpView() {
        return mMvpView;
    }
}
