package com.jv.benedict.ui.transpwd;

import android.os.Bundle;
import android.view.View;
import com.jv.benedict.repository.RemoteRepo.RemoteRepo;
import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BeneBaseActivity;

/**
 * Created by Leo on 2018/1/4.
 */

public class TransPwdActivity extends BeneBaseActivity {
  @Override protected BasePresenter createPresenter() {
    return null;
  }

  @Override public void initView(View view) {
    TransPwdFragment transPwdFragment = new TransPwdFragment();
    new TransPwdPresenter(new RemoteRepo(), transPwdFragment);
    addFragment(transPwdFragment);
  }

  @Override public void initParms(Bundle parms) {

  }

  public void gotoTransPwdConfirmFragment() {
    TransPwdConfirmFragment transPwdConfirmFragment = new TransPwdConfirmFragment();
    new TransPwdPresenter(new RemoteRepo(), transPwdConfirmFragment);
    addFragment(transPwdConfirmFragment);
  }
}
