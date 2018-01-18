package com.jv.benedict.ui.transpwd;

import android.content.res.Resources;
import com.jv.benedict.ui.IPresenter;
import com.jv.benedict.ui.IView;
import org.json.JSONObject;

/**
 * Created by Leo on 2018/1/5.
 */

public interface TransPwdCntract {

    interface TransPwdPresenter extends IPresenter{

      void checkTransPwd(String pwd);
      void checkTransPwdConfirm(String pwd);
      void remote(JSONObject json, String pwd, String pwdType);
    }

    interface TransPwdView extends IView<TransPwdPresenter>{
        Resources getRes();
        void gotoTransPwdConfirmFragment();
        void gotoOcR();
        void showToast(String msg);

    }


}
