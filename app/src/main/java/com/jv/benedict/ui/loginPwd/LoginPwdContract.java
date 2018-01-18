package com.jv.benedict.ui.loginPwd;

import android.content.res.Resources;
import com.jv.benedict.ui.IPresenter;
import com.jv.benedict.ui.IView;

/**
 * Created by Leo on 2018/1/4.
 */

public interface LoginPwdContract {
  interface LoginPwdView extends IView<ILoginPwdPresenter>{
      void gotoSuccessBindFragment();

      void gotoLoginPwdConfirmFragment();

      void showToast(String text);

      Resources getRes();
  }

  interface  ILoginPwdPresenter extends IPresenter{
    void  checkLoginPwd(String loginpwd);
    void checkLoginPwdConfirm(String loginpwdConfirm);
    void remote(String pwd, String pwdType);
    boolean checkEzayLoginPwd(String loginpwd);
  }



}
