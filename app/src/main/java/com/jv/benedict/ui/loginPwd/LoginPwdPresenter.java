package com.jv.benedict.ui.loginPwd;

import android.text.TextUtils;

import com.jv.benedict.R;
import com.jv.benedict.model.bean.ApplicationID;
import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import com.jv.benedict.repository.common.RepoCallBack;
import com.jv.benedict.utils.CheckPwdUtils;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Leo on 2018/1/4.
 */

public class LoginPwdPresenter implements LoginPwdContract.ILoginPwdPresenter {

  IRemoteRepo remoteRepo;
  LoginPwdContract.LoginPwdView mView;
  private final int LOGINPWD_LENGTH_8 = 8;

  public LoginPwdPresenter(IRemoteRepo remoteRepo, LoginPwdContract.LoginPwdView mView) {
    this.remoteRepo = remoteRepo;
    this.mView = mView;
    mView.setPresenter(this);
  }

  @Override public void onStart() {

  }

  @Override public void onDestory() {
    if (remoteRepo != null) {
      remoteRepo.cancelHttp();
    }
  }

  @Override public void checkLoginPwd(String loginpwd) {
    if (mView.isActive()) {
      if (TextUtils.isEmpty(loginpwd)) {
        mView.showToast(mView.getRes().getString(R.string.loginpwd_notnull));
        return;
      }
      if (loginpwd.length() < LOGINPWD_LENGTH_8) {
        mView.showToast(mView.getRes().getString(R.string.loginpwd_morethan_8));
        return;
      }

      if (checkEzayLoginPwd(loginpwd)) {

        SPUtils.getInstance().put(Constant.PWDLOGIN, loginpwd);
        mView.gotoLoginPwdConfirmFragment();
      }
    }
  }

  @Override public void checkLoginPwdConfirm(String loginpwd) {
    if (mView.isActive()) {
      //if (!loginpwd.equals(SPUtils.getInstance().getString(Constant.PWDLOGIN))) {
      //  mView.showToast(mView.getRes().getString(R.string.confirm_pwd_mustsame_pwd));
      //  return;
      //}
      //mView.gotoSuccessBindFragment();
      remote(loginpwd, Constant.VALUE_LOGINPWDTYPE);
    }
  }

  public boolean checkEzayLoginPwd(String loginpwd) {
    if (loginpwd.matches(Constant.LOGINPWDREGEX)) {
      mView.showToast(mView.getRes().getString(R.string.pwd_toosimple_same_num));
      return false;
    }
    if (CheckPwdUtils.isOrderNumberic(loginpwd)) {
      mView.showToast(mView.getRes().getString(R.string.login_pwd_toosimple_continuenum));
      return false;
    }
    return true;
  }

  @Override public void remote(String loginpwd, String pwdType) {
    try {
      mView.showLoading();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put(Constant.KEY_PWD, loginpwd);
      jsonObject.put(Constant.KEY_PWDTYPE, pwdType);
      remoteRepo.remotePost(Constant.URL_PWD, jsonObject, ApplicationID.class, new RepoCallBack() {
        @Override public <T> void onSuccess(T response) {
          mView.hideLoading();
          mView.gotoSuccessBindFragment();
        }

        @Override public void onFailed(String response) {
          mView.hideLoading();
          mView.showToast(response);
        }

        @Override public void onError(String response) {
          mView.hideLoading();
          mView.showToast(response);
        }
      });
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
