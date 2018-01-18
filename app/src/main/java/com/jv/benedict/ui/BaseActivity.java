package com.jv.benedict.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jv.benedict.R;
import com.jv.benedict.model.bean.MessageEvent;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.StatusBarUtil;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Leo on 2017/12/28.
 * 初始化butterknife
 * 是否全屏
 * 标题
 * 沉浸式状态栏
 * activity之间的跳
 */

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter>
    extends AppCompatActivity {
  private P presenter;
  /** 是否沉浸状态栏 默认开**/
  private boolean isSetStatusBar = true;
  /** 是否允许全屏 默认禁止 **/
  private boolean mAllowFullScreen = false;
  /** 是否禁止旋转屏幕 默认禁止 **/
  private boolean isAllowScreenRoate = true;
  /** 当前Activity渲染的视图View **/
  private View mContextView = null;
  /** 当前页fragment tag **/
  private String currFrgTag = "";
  private LinearLayout mHead;
  private ImageView mBack, mRightIvTwo, mRightIvOne;
  private TextView mTitle, mRightTv;
  private ProgressBar progressBar;
  private final int FRAGMENT_COUNT_0 = 0;
  private final int FRAGMENT_COUNT_1 = 1;
  protected final String TAG = this.getClass().getSimpleName();
  private LinearLayout rootLayout;
  private View mView;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mView = LayoutInflater.from(this).inflate(R.layout.root_layout, null);
    mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
    if (mAllowFullScreen) {
      this.getWindow()
          .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    try {
      Bundle bundle = getIntent().getExtras();
      initParms(bundle);

      if (isSetStatusBar) {
        SetStatusBarColor();
      }

      setLayoutView(mContextView);
      if (presenter == null) {
        presenter = createPresenter();
      }
      //if (presenter == null) {
      //    throw new NullPointerException("presenter 不能为空!");
      //}
      //绑定view
      //presenter.attachMvpView((V) this);

      if (presenter != null) {
        presenter.attachMvpView((V) this);
      }

      if (!isAllowScreenRoate) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      } else {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      }
      initView(mContextView);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 创建Presenter
   *
   * @return 子类自己需要的Presenter
   */
  protected abstract P createPresenter();

  /**
   * 获取Presenter
   *
   * @return 返回子类创建的Presenter
   */
  public P getPresenter() {
    return presenter;
  }

  /**
   * [沉浸状态栏]
   */
  private void SetStatusBarColor() {
    StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.appcolor));
  }

  /**
   * [绑定布局]
   */
  public abstract int bindLayout();

  /**
   * [初始化控件]
   */
  public abstract void initView(final View view);

  /**
   * [初始化Bundle参数]
   */
  public abstract void initParms(Bundle parms);

  /**
   * [是否初始化title]
   */

  private void initTitle() {
    progressBar = (ProgressBar) findViewById(R.id.pb_title);
    mHead = (LinearLayout) findViewById(R.id.base_head);
    mBack = (ImageView) findViewById(R.id.base_head_back);
    mRightIvOne = (ImageView) findViewById(R.id.base_head_iv_one);
    mRightIvTwo = (ImageView) findViewById(R.id.base_head_iv_two);
    mTitle = (TextView) findViewById(R.id.base_head_title);
    mRightTv = (TextView) findViewById(R.id.base_head_txt);
    mBack.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        finish();
      }
    });
  }

  public View getHeadView() {
    return this.mHead;
  }

  public ImageView getBackIv() {
    return this.mBack;
  }

  public ImageView getRightIvTwo() {
    return this.mRightIvTwo;
  }

  public ImageView getRightIvOne() {
    return this.mRightIvOne;
  }

  public TextView getTitleTv() {
    return this.mTitle;
  }

  public TextView getRightTv() {
    return this.mRightTv;
  }
  public ProgressBar getProgressBar(){
      return this.progressBar;
  }
  public void setProgressBar(int progress){
    this.progressBar.setVisibility(View.VISIBLE);
    this.progressBar.setProgress(progress);
  }
  public void setProgressBar(boolean isOpen){
      if (isOpen){
          this.progressBar.setVisibility(View.VISIBLE);
      }else {
          this.progressBar.setVisibility(View.GONE);
      }
  }
  public void setLayoutView(View view) {
    rootLayout = (LinearLayout) mView.findViewById(R.id.root_layout);
    if (rootLayout == null) {
      return;
    }
    rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    setContentView(rootLayout);
    initTitle();
  }

  /**
   * 隐藏系统软键
   */
  public void hideSoftInput() {
    try {
      InputMethodManager manager =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if (manager != null
          && this.getWindow().getAttributes().softInputMode
          == WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().getAttributes().softInputMode =
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * [页面跳转]
   */
  public void startActivity(Class<?> clz) {
    startActivity(clz, null);
  }

  /**
   * [携带数据的页面跳转]
   */
  public void startActivity(Class<?> clz, Bundle bundle) {
    Intent intent = new Intent();
    intent.setClass(this, clz);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    startActivity(intent);
  }

  /**
   * [含有Bundle通过Class打开编辑界面]
   */
  public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
    Intent intent = new Intent();
    intent.setClass(this, cls);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    startActivityForResult(intent, requestCode);
  }

  @Override protected void onResume() {
    super.onResume();
    Log(TAG + "--->onResume()");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.detachMvpView();
    }
    hideSoftInput();
    Log(TAG + "--->onDestroy()");
  }

  /**
   * [是否允许全屏]
   */
  public void setAllowFullScreen(boolean allowFullScreen) {
    this.mAllowFullScreen = allowFullScreen;
  }

  /**
   * [是否沉浸式栏]
   */
  public void setSetStatusBar(boolean isSetStatusBar) {
    this.isSetStatusBar = isSetStatusBar;
  }

  /**
   * [是否允许屏幕旋转]
   */
  public void setScreenRoate(boolean isAllowScreenRoate) {
    this.isAllowScreenRoate = isAllowScreenRoate;
  }

  /**
   * [日志输出]
   */
  protected void Log(String msg) {
    if (Constant.isDebug) {
      Log.d(Constant.APPNAME, msg);
    }
  }

  /**------------------------fragment页面管理-------------------------------**/
  /** [添加fragment页面] **/
  public void addFragment(int contentId, Fragment fragment, boolean isAnim) {
    if (null != fragment) {
      String tag = fragment.getClass().getName();
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      if (isAnim) {
        ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in,
            R.anim.slide_right_out);
      }
      Fragment tempFragment = getSupportFragmentManager().findFragmentByTag(tag);
      if (null != tempFragment) {
        ft.replace(contentId, fragment, tag);
      } else {
        ft.add(contentId, fragment, tag);
      }
      ft.addToBackStack(tag);
      ft.commitAllowingStateLoss();
    }
  }

  /**
   * [获取当前fragment]
   */
  public BaseFragment getCurrentFragment() {
    int count = getSupportFragmentManager().getBackStackEntryCount();
    if (count > FRAGMENT_COUNT_0) {
      FragmentManager.BackStackEntry entry =
          getSupportFragmentManager().getBackStackEntryAt(count - 1);
      String tag = entry.getName();
      return getFragmentByTag(tag);
    } else {
      return null;
    }
  }

  /**
   * [通过tag获取fragment]
   */
  public BaseFragment getFragmentByTag(String tag) {
    Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
    if (fragment instanceof Fragment) {
      return (BaseFragment) fragment;
    }
    return null;
  }

  /**
   * [退回到前一个fragment 最后结束activity页面]
   */
  @Override public void onBackPressed() {
    BaseFragment baseFragment = getCurrentFragment();
    if (baseFragment != null && BaseFragment.BackFlag.NO_HANDLER == baseFragment.backPressed()) {
      FragmentManager fm = getSupportFragmentManager();
      int count = fm.getBackStackEntryCount();
      if (count > FRAGMENT_COUNT_1) {
        prevBackStacFragment();
      } else {
        finish();
      }
    }

    if (baseFragment != null
        && BaseFragment.BackFlag.FINISH_HANDLER == baseFragment.backPressed()) {
      finish();
    }

    if (baseFragment == null) {
      finish();
    }
  }

  /**
   * [返回堆栈的前一个fragment]
   */
  public void prevBackStacFragment() {
    currFrgTag = getCurrentFragment().getClass().getName();
    getSupportFragmentManager().popBackStack();
  }

  /**
   * [通过tag返回堆栈中的fragment]
   *
   * @param isDestory tag本身是否销
   */
  public void backStackFragment(String tag, boolean isDestory) {
    currFrgTag = getCurrentFragment().getClass().getName();
    FragmentManager fm = getSupportFragmentManager();
    if (isDestory) {
      fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    } else {
      fm.popBackStack(tag, 0);
    }
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
      View v = getCurrentFocus();
      if (isShouldHideKeyboard(v, ev)) {
        hideKeyboard(v.getWindowToken());

      }
    }
    return super.dispatchTouchEvent(ev);
  }
  private boolean isShouldHideKeyboard(View v, MotionEvent event) {
    if (v != null && (v instanceof EditText)) {
      int[] l = {0, 0};
      v.getLocationInWindow(l);
      int left = l[0],
          top = l[1],
          bottom = top + v.getHeight(),
          right = left + v.getWidth();
      if (event.getX() > left && event.getX() < right
          && event.getY() > top && event.getY() < bottom) {
        // 点击EditText的事件，忽略它。
        return false;
      } else {
        return true;
      }
    }
    // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
    return false;
  }
  @RequiresApi(api = Build.VERSION_CODES.CUPCAKE) private void hideKeyboard(IBinder token) {
    if (token != null) {
      EventBus.getDefault().post(new MessageEvent("Hello !....."));
      InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }
}
