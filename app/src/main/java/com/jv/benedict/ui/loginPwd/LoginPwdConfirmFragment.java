package com.jv.benedict.ui.loginPwd;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.LoginPwdCallBack.PwdCallBack;
import com.jv.benedict.ui.view.EditTextWathcer;
import com.jv.benedict.utils.ToastUtils;

/**
 * Created by Leo on 2018/1/3.
 */

public class LoginPwdConfirmFragment extends BaseFragment
    implements View.OnClickListener, LoginPwdContract.LoginPwdView {
  LoginPwdContract.ILoginPwdPresenter mPresenter;
  private EditText et_login_pwd_confirm;
  private TextView tv_confirmpwd;
  private Button btnext;

  @Override public int bindLayout() {
    return R.layout.fragment_login_pwd_confirm;
  }

  @Override public void initCustomeViews(View view) {
    setProgressBar(PROGRESS_25);
    getTitleTv().setText(R.string.bind_phonenumber);
    btnext = (Button) view.findViewById(R.id.bt_next);
    tv_confirmpwd = (TextView) view.findViewById(R.id.tv_confirmpwd);
    btnext.setOnClickListener(this);
    et_login_pwd_confirm = (EditText) view.findViewById(R.id.et_login_pwd_confirm);
    et_login_pwd_confirm.requestFocus();
    et_login_pwd_confirm.setFocusable(true);
    et_login_pwd_confirm.setFocusableInTouchMode(true);

    //forceOpenSoftKeyboard(getContext());
    //initPassGuardEdit(et_login_pwd_confirm);

    et_login_pwd_confirm.addTextChangedListener(
        new EditTextWathcer().getLoginPwdTextWatcher(new PwdCallBack() {
          @Override public void gotoNextFragment() {
            mPresenter.checkLoginPwdConfirm(et_login_pwd_confirm.getText().toString());
          }

          @Override public void fail() {
            initTextStyleError(tv_confirmpwd, getRes().getString(R.string.confirm_pwd_mustsame_pwd));
          }

          @Override public void initTextStyle() {
            initTextStyleNormal(tv_confirmpwd, getRes().getString(R.string.again_input_sample_login_pwd));
          }
        }));
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_next:
        mPresenter.checkLoginPwdConfirm(et_login_pwd_confirm.getText().toString());
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
    InputMethodManager imm =
        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

    getBeneBaseActivity().addFragment(new SuccessBindFragment());
  }

  @Override public void gotoLoginPwdConfirmFragment() {

  }

  @Override public void showToast(String text) {
    ToastUtils.showShort(text);
  }


  @Override public Resources getRes() {
    return getResources();
  }
}
