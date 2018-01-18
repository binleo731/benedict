package com.jv.benedict.ui.transpwd;

import android.text.TextUtils;
import com.jv.benedict.R;
import com.jv.benedict.model.bean.ApplicationID;
import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import com.jv.benedict.repository.common.RepoCallBack;
import com.jv.benedict.utils.CheckPwdUtils;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.SPUtils;
import com.jv.benedict.utils.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Leo on 2018/1/5.
 */

public class TransPwdPresenter implements TransPwdCntract.TransPwdPresenter {

  IRemoteRepo remoteRepo;
  TransPwdCntract.TransPwdView mView;
  private final int TRANS_PWD_LENGTH_6 = 6;

  public TransPwdPresenter(IRemoteRepo remoteRepo, TransPwdCntract.TransPwdView mView) {
    this.remoteRepo = remoteRepo;
    this.mView = mView;
    mView.setPresenter(this);
  }

  @Override public void onStart() {

  }

  @Override public void onDestory() {
    remoteRepo.cancelHttp();
  }

  public void checkTransPwd(String trnaspwd) {

    if (checkNotNull(trnaspwd, mView.getRes().getString(R.string.transpwd_notnull)) && checkEazyPwd(
        trnaspwd) && checkSixPwd(trnaspwd.length())) {
      SPUtils.getInstance().put(Constant.TRANSPWD, trnaspwd);
      mView.gotoTransPwdConfirmFragment();
    }
  }

  public void checkTransPwdConfirm(String transPwdConfirm) {
    if (checkNotNull(transPwdConfirm, mView.getRes().getString(R.string.transpwd_confirm_notnull))
        && checkSixPwd(transPwdConfirm.length())) {
      if (!transPwdConfirm.equals(SPUtils.getInstance().getString(Constant.TRANSPWD))) {
        mView.showToast(mView.getRes().getString(R.string.input_pwd_confirm));
        return;
      }

      remote(new JSONObject(), transPwdConfirm, Constant.VALUE_TRANPWDTYPE);

    }
  }

  @Override public void remote(JSONObject jsonObject, String pwd, String pwdType) {
    try {
      mView.showLoading();
      jsonObject.put(Constant.KEY_PWD, pwd);
      jsonObject.put(Constant.KEY_PWDTYPE, pwdType);
      remoteRepo.remotePost(Constant.URL_PWD, jsonObject, ApplicationID.class, new RepoCallBack() {
        @Override public <T> void onSuccess(T response) {
          mView.hideLoading();
          mView.gotoOcR();
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

  public boolean checkNotNull(String str, String msg) {
    if (TextUtils.isEmpty(str)) {
      ToastUtils.showShort(msg);
      return false;
    }
    return true;
  }

  public boolean checkSixPwd(int len) {
    if (len != TRANS_PWD_LENGTH_6) {
     mView.showToast(mView.getRes().getString(R.string.input_six_pwd));
      return false;
    }
    return true;
  }

  public boolean checkEazyPwd(String pwd) {

    if (pwd.matches(Constant.TRANSPWDREGEX)) {
      mView.showToast(mView.getRes().getString(R.string.input_eazy_pwd));
      return false;
    }
    if (CheckPwdUtils.isOrderNumberic(pwd)) {
      mView.showToast(mView.getRes().getString(R.string.input_continuous_pwd));
      return false;
    }
    return true;
  }
}
