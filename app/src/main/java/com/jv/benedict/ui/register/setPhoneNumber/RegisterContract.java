package com.jv.benedict.ui.register.setPhoneNumber;

import android.content.res.Resources;
import com.jv.benedict.ui.IPresenter;
import com.jv.benedict.ui.IView;

/**
 * Created by Leo on 2018/1/3.
 */

interface RegisterContract {
  interface PhoneNumPresenter extends IPresenter {
      void checkPhoneNum(String number);
      String getRandomPhone();
  }

  interface SetPhoneView extends IView<PhoneNumPresenter>{
      void showLoginPwdUi();

      boolean checkAgree();

      void showToast(String text);

      Resources getRes();

  }
}
