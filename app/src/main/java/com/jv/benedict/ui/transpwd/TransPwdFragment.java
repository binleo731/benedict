package com.jv.benedict.ui.transpwd;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.LoginPwdCallBack.PwdCallBack;
import com.jv.benedict.ui.view.SixPwdEditTextView;

/**
 * Created by Leo on 2018/1/4.
 */

public class TransPwdFragment extends BaseFragment
    implements View.OnClickListener, TransPwdCntract.TransPwdView {
  TransPwdCntract.TransPwdPresenter mPresenter;
  SixPwdEditTextView spet_transpwd;
  TextView tv_transpwd_msg;

  @Override public int bindLayout() {
    return R.layout.fragment_transpwd;
  }

  @Override public void initCustomeViews(View view) {
    setProgressBar(PROGRESS_35);
    getTitleTv().setText(R.string.account_security);
    view.findViewById(R.id.bt_next).setOnClickListener(this);
    spet_transpwd = (SixPwdEditTextView) view.findViewById(R.id.spet_transpwd);
    tv_transpwd_msg = (TextView) view.findViewById(R.id.tv_transpwd_msg);
    //初始化密码键盘
    initSixPassGuardEdit(spet_transpwd.getPassGuardEdit());
    spet_transpwd.addChangeListern(new PwdCallBack() {
      @Override public void gotoNextFragment() {

      }

      @Override public void fail() {

      }

      @Override public void initTextStyle() {
          initTextStyleNormal(tv_transpwd_msg, getRes().getString(R.string.set_transpwd));
      }
    });
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bt_next:
        mPresenter.checkTransPwd(spet_transpwd.getText().toString());
        break;
    }
  }

  @Override public Resources getRes() {
    return getBeneBaseActivity().getResources();
  }

  @Override public void setPresenter(TransPwdCntract.TransPwdPresenter presenter) {
    mPresenter = presenter;
  }

  @Override public boolean isActive() {
    return isActive();
  }

  public void gotoTransPwdConfirmFragment() {
    TransPwdActivity trnAct = (TransPwdActivity) getBeneBaseActivity();
    trnAct.gotoTransPwdConfirmFragment();
  }

  @Override public void gotoOcR() {

  }

  @Override public void showToast(String msg) {
      initTextStyleError(tv_transpwd_msg, msg);
  }
}
