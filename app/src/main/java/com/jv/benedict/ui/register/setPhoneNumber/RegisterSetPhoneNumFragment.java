package com.jv.benedict.ui.register.setPhoneNumber;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.jv.benedict.R;
import com.jv.benedict.model.bean.MessageEvent;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.register.RegisterActivity;
import com.jv.benedict.ui.view.EditTextWathcer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Leo on 2018/1/2.
 */

public class RegisterSetPhoneNumFragment extends BaseFragment implements View.OnClickListener,
    RegisterContract.SetPhoneView {
  private RegisterContract.PhoneNumPresenter mPresenter;
  EditText editText;
  @BindView(R.id.register_cb)
  CheckBox checkBox;
  TextView tv_setphone_msg;

  @Override public int bindLayout() {
    return R.layout.fragment_register_setphonenumber;
  }

  @Override public void initCustomeViews(View view) {
    EventBus.getDefault().register(this);
    setProgressBar(PROGRESS_15);
    getTitleTv().setText(R.string.bind_phonenumber);
    mPresenter.onStart();
    view.findViewById(R.id.bt_next).setOnClickListener(this);
    editText = (EditText) view.findViewById(R.id.tv_set_phonenumber);
    tv_setphone_msg = (TextView) view.findViewById(R.id.tv_setphone_msg);
    //自动弹出键盘
    editText.setFocusable(true);
    //editText.setFocusableInTouchMode(true);
    editText.requestFocus();
    //监听 手机输入格式化
    editText.addTextChangedListener(new EditTextWathcer().getTextWatcher(editText, tv_setphone_msg));

    //随机获取数字
    editText.setText(mPresenter.getRandomPhone());
    editText.setSelection(editText.getText().toString().length());

    editText.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        editText.setCursorVisible(true);
      }
    });

  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_next:
        mPresenter.checkPhoneNum(editText.getText().toString());
        break;
    }
  }

  @Override public void setPresenter(RegisterContract.PhoneNumPresenter presenter) {
    mPresenter = presenter;
  }

  @Override public boolean isActive(){
    return isAdded();
  }

  public void showLoginPwdUi() {
    RegisterActivity act = (RegisterActivity)getBeneBaseActivity();
    act.gotoLoginPwdFragment();
  }


  @Override public boolean checkAgree() {
    return checkBox.isChecked();
  }

  @Override public void showToast(String msg) {
    initTextStyleError(tv_setphone_msg, msg);
  }

  @Override public Resources getRes() {
    return getResources();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mPresenter.onDestory();
    EventBus.getDefault().unregister(this);
  }
  //订阅方法，当接收到事件的时候，会调用该方法
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(MessageEvent messageEvent){
    Log.d("cylog","receive it");
    //使EditText触发一次失去焦点事件
    //editText.setFocusable(false);
    //editText.setFocusable(true);
    //                v.setFocusable(true); //这里不需要是因为下面一句代码会同时实现这个功能
    //editText.setFocusableInTouchMode(true);
    System.out.println("hahahahah");
    editText.clearFocus();
    editText.setCursorVisible(false);
  }




}
