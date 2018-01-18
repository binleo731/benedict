package com.jv.benedict.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import cn.passguard.PassGuardEdit;
import com.jv.benedict.R;
import com.jv.benedict.utils.Constant;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by Leo on 2017/12/29.
 */

public abstract class BaseFragment<T, V> extends Fragment {

  private FrameLayout mContent;
  private LinearLayout mHead;
  private ImageView mBack, mRightIvTwo, mRightIvOne;
  private TextView mTitle, mRightTv;
  private boolean isDebug;
  private String APP_NAME;
  protected final String TAG = this.getClass().getSimpleName();
  private View rootView;
  private BeneBaseActivity mActivity;
  private ProgressBar progressBar;
  private IPresenter mPresenter;
  public final int   PROGRESS_10 = 10;
  public final int   PROGRESS_15 = 15;
  public final int   PROGRESS_20 = 20;
  public final int   PROGRESS_25 = 25;
  public final int   PROGRESS_30 = 30;
  public final int   PROGRESS_35 = 35;
  public final int   PROGRESS_40 = 40;
  private KProgressHUD kProgressHUD;

  public enum BackFlag {
    NO_HANDLER, FINISH_HANDLER, CUSTOM_HANDLER
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    isDebug = Constant.isDebug;
    APP_NAME = Constant.APPNAME;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (null == rootView) {
      rootView = inflater.inflate(R.layout.fragment_root_layout, container, false);
    }
    setCustomView(bindLayout());
    initViews(rootView);
    initTitle(rootView);
    getBeneBaseActivity().getHeadView().setVisibility(View.GONE);
    // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
    setCustomView(bindLayout());
    initCustomeViews(mContent);
    ViewGroup parent = (ViewGroup) rootView.getParent();
    if (parent != null) {
      parent.removeView(rootView);
    }
    ButterKnife.bind(this, rootView);

    kProgressHUD = KProgressHUD.create(getBeneBaseActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在加载...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);


    return rootView;
  }
  @Override
  public void onAttach(Context context ) {
    super.onAttach(context);
    this.mActivity = (BeneBaseActivity)context;
  }

  private void setCustomView(int layoutId) {
    if (null != mContent) {
      mContent.removeAllViews();
      LayoutInflater.from(getActivity()).inflate(layoutId, mContent);
    }
  }
  public BeneBaseActivity getBeneBaseActivity() {
    return (BeneBaseActivity) getActivity();
  }

  public void setPresenter(IPresenter presenter){
    mPresenter = presenter;
  }

  public void initViews(View rootView){
    mContent = (FrameLayout) rootView.findViewById(R.id.fg_content);
    mActivity.getBackIv().setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mActivity.onBackPressed();
      }
    });
  }

  public void initTitle(View rootView){
    progressBar = (ProgressBar) rootView.findViewById(R.id.pb_title);
    mHead = (LinearLayout) rootView.findViewById(R.id.base_head);
    mBack = (ImageView) rootView.findViewById(R.id.base_head_back);
    mRightIvOne = (ImageView) rootView.findViewById(R.id.base_head_iv_one);
    mRightIvTwo = (ImageView) rootView.findViewById(R.id.base_head_iv_two);
    mTitle = (TextView) rootView.findViewById(R.id.base_head_title);
    mRightTv = (TextView) rootView.findViewById(R.id.base_head_txt);
    mBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getBeneBaseActivity().onBackPressed();
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
  @Override
  public void onDestroy() {
    rootView = null;
    super.onDestroy();
    if(mPresenter!=null){
      mPresenter.onDestory();
    }

    Log("destory--------");
  }

  @Override public void onPause() {
    super.onPause();

  }

  /**
   * [绑定布局]
   *
   * @return
   */
  public abstract int bindLayout();

  /**
   * [初始化控件]
   *
   * @param view
   */
  public abstract void initCustomeViews(final View view);



  /**
   * [日志输出]
   *
   * @param msg
   */
  protected void Log(String msg) {
    if (isDebug) {
      Log.d(APP_NAME, msg);
    }
  }

  /**
   * fragment是否处理返回事件
   *
   * @return true已处理, false未处理
   */
  public BackFlag backPressed() {
    return BackFlag.NO_HANDLER;
  }

  public void initPassGuardEdit(PassGuardEdit ge){
    ge.setEncrypt(false);

    ge.initPassGuardKeyBoard();  
    ge.StartPassGuardKeyBoard();
  }

  public void initSixPassGuardEdit(PassGuardEdit ge){
    ge.useNumberPad(true);
    ge.setEncrypt(false);
    ge.initPassGuardKeyBoard();
    ge.StartPassGuardKeyBoard();
  }

  //强制弹出键盘
  public void forceOpenSoftKeyboard(Context context){
    InputMethodManager imm =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
  }

  //设置 text 样式
  public void initTextStyleError(TextView tv, String msg){
    tv.setText(msg);
    tv.setTextColor(getResources().getColor(R.color.errortxt));
  }

  public void initTextStyleNormal(TextView tv, String msg){
    tv.setText(msg);
    tv.setTextColor(getResources().getColor(R.color.txtblack));
  }

  public void showLoading(){
    kProgressHUD.show();
  }
  public void hideLoading(){
    kProgressHUD.dismiss();
  }



}


