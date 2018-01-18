package com.jv.benedict.ui.loginPwd;

import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.register.RegisterActivity;
import com.jv.benedict.ui.view.EditTextWathcer;

/**
 * Created by Leo on 2018/1/3.
 */

public class LoginPwdFragment extends BaseFragment
    implements View.OnClickListener, LoginPwdContract.LoginPwdView {

  LoginPwdContract.ILoginPwdPresenter mPresenter;
  EditText et_login_pwd;
  TextView tv_loginpwd_msg;

  @Override public int bindLayout() {
    return R.layout.fragment_login_pwd;
  }

  @Override public void initCustomeViews(View view) {
    setProgressBar(PROGRESS_20);
    getTitleTv().setText(R.string.bind_phonenumber);
    mPresenter.onStart();
    view.findViewById(R.id.bt_next).setOnClickListener(this);
    et_login_pwd = (EditText) view.findViewById(R.id.et_login_pwd);
    tv_loginpwd_msg = (TextView) view.findViewById(R.id.tv_loginpwd_msg);
    //initPassGuardEdit(et_login_pwd);
    et_login_pwd.setFocusable(true);
    et_login_pwd.setFocusableInTouchMode(true);
    et_login_pwd.requestFocus();
    et_login_pwd.addTextChangedListener(
        new EditTextWathcer().getTextWatcher(tv_loginpwd_msg, getString(R.string.setpwd_8_16),
            getRes().getColor(R.color.txtblack)));
    forceOpenSoftKeyboard(getContext());
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_next:
        mPresenter.checkLoginPwd(et_login_pwd.getText().toString());
        break;
    }
  }

  @Override public void setPresenter(LoginPwdContract.ILoginPwdPresenter presenter) {
    mPresenter = presenter;
  }

  @Override public boolean isActive() {
    return isAdded();
  }

  @Override public void gotoSuccessBindFragment() {

  }

  @Override public void gotoLoginPwdConfirmFragment() {
    RegisterActivity act = (RegisterActivity) getBeneBaseActivity();
    act.gotoLoginPwdConfirmFragment();
  }

  @Override public void showToast(String text) {
    tv_loginpwd_msg.setText(text);
    tv_loginpwd_msg.setTextColor(getResources().getColor(R.color.errortxt));
    initTextStyleError(tv_loginpwd_msg, text);
  }

  @Override public Resources getRes() {
    return getResources();
  }
}
