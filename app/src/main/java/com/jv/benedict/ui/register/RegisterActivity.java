package com.jv.benedict.ui.register;

import android.os.Bundle;
import android.view.View;
import com.jv.benedict.repository.RemoteRepo.RemoteRepo;
import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BeneBaseActivity;
import com.jv.benedict.ui.loginPwd.LoginPwdConfirmFragment;
import com.jv.benedict.ui.loginPwd.LoginPwdFragment;
import com.jv.benedict.ui.loginPwd.LoginPwdPresenter;
import com.jv.benedict.ui.register.setPhoneNumber.RegisterSetPhoneNumFragment;
import com.jv.benedict.ui.register.setPhoneNumber.SetPhoneNumPresenter;

/**
 * Created by Leo on 2018/1/2.
 */

public class RegisterActivity extends BeneBaseActivity {
  @Override protected BasePresenter createPresenter() {
    return null;
  }

  @Override public void initView(View view) {
    addFragment(new RegisterReadyFragment());
  }

 //去手机号码设置页面
  public void gotoRegisterSetPhoneNumFragment(){
    RegisterSetPhoneNumFragment RegisterSetPhoneNumFragment = new RegisterSetPhoneNumFragment();

    new SetPhoneNumPresenter(new RemoteRepo(), RegisterSetPhoneNumFragment);

    addFragment(RegisterSetPhoneNumFragment);
  }

  //去设置密码页面
  public void gotoLoginPwdFragment(){
    LoginPwdFragment loginPwdFragment = new LoginPwdFragment();

    new LoginPwdPresenter(new RemoteRepo(), loginPwdFragment);

    addFragment(loginPwdFragment);
  }


  //去登录密码设置页面
  public void gotoLoginPwdConfirmFragment(){
    LoginPwdConfirmFragment loginPwdConfirmFragment = new LoginPwdConfirmFragment();

    new LoginPwdPresenter(new RemoteRepo(), loginPwdConfirmFragment);

    addFragment(loginPwdConfirmFragment);
  }

  @Override public void initParms(Bundle parms) {

  }
}
