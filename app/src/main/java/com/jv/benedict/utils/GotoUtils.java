package com.jv.benedict.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseActivity;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.BeneBaseActivity;

/**
 * 页面跳转工具类
 */
public class GotoUtils {

    private static GotoUtils gotoUtils;
    private Intent intent;

    public static GotoUtils getInstance() {
        if (null == gotoUtils) {
            return gotoUtils = new GotoUtils();
        }
        return gotoUtils;
    }

    private GotoUtils() {
    }

    /**
     * 前往登录页面
     *
     * @param baseAct
     */
    public void gotoLogin(BaseActivity baseAct) {

    }

    /**
     * 前往开户页面
     *
     * @param baseAct
     */
    public void gotoOpenAcc(BaseActivity baseAct) {

    }








    /**
     * 在新activity中启动fragment
     *
     * @param baseAct
     * @param className
     */
    public void gotoFragmentWithActivity(BeneBaseActivity baseAct, String className, Class clazz) {
        intent = new Intent(baseAct, clazz);
        intent.putExtra(Constant.CLASS_NAME, className);
        baseAct.startActivity(intent);
    }

    /**
     * 在新activity中启动fragment，带数据
     *
     * @param baseAct
     * @param className
     * @param bundle
     */
    public void gotoFragmentWithActivity(BeneBaseActivity baseAct, String className, Class clazz, Bundle bundle) {
        intent = new Intent(baseAct, clazz);
        intent.putExtra(Constant.CLASS_NAME, className);
        intent.putExtras(bundle);
        baseAct.startActivity(intent);
    }

    /**
     * 在activity中加入新fragment
     *

     * @param className
     */
    public void gotoFragment(BeneBaseActivity baseAct, String className) {
        Fragment fragment = Fragment.instantiate(baseAct, className);
        baseAct.addFragment(R.id.bene_base_content, fragment, true);
    }

    /**
     * 在activity中加入新fragment
     *
     */
    public void gotoFragment(BeneBaseActivity baseAct, BaseFragment fragment) {
        baseAct.addFragment(R.id.bene_base_content, fragment, true);

    }

    /**
     * 在activity中加入新fragment，带数据
     *
     * @param baseAct
     * @param className
     * @param bundle
     */
    public void gotoFragment(BeneBaseActivity baseAct, String className, Bundle bundle) {
        Fragment fragment = Fragment.instantiate(baseAct, className, bundle);
        baseAct.addFragment(R.id.bene_base_content, fragment, true);
    }






}
