package com.jv.benedict.ui.register.setPhoneNumber;

import android.text.TextUtils;
import com.jv.benedict.R;
import com.jv.benedict.model.bean.ApplicationID;
import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import com.jv.benedict.repository.common.RepoCallBack;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.RandomUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Leo on 2018/1/4.
 */

public class SetPhoneNumPresenter implements RegisterContract.PhoneNumPresenter {

  private final IRemoteRepo remoteRepo;
  private final RegisterContract.SetPhoneView mView;
  private String phone;
  private int PHONELENGHT_13 = 13;
  public SetPhoneNumPresenter(IRemoteRepo remoteRepo, RegisterContract.SetPhoneView mView) {
    this.remoteRepo = remoteRepo;
    this.mView = mView;
    mView.setPresenter(this);
  }

  @Override public void checkPhoneNum(String number) {
    if(mView.isActive()) {

      if (TextUtils.isEmpty(number)) {
        mView.showToast(mView.getRes().getString(R.string.input_phonenumber));
        return;
      }
      if (number.length() != PHONELENGHT_13) {
        mView.showToast(mView.getRes().getString(R.string.input_right_phonenumber));
        return;
      }
      if (!mView.checkAgree()) {
        mView.showToast(mView.getRes().getString(R.string.argee_argeemnet));
        return;
      }
      mView.showLoginPwdUi();
      //remote(FormatUtil.getInstance().FormatPhoneNumber(number));
    }
  }

  @Override public String getRandomPhone() {
    return phone;
  }

  @Override public void onStart() {
    //随机获得电话号码
    phone = RandomUtil.getPhoneNumber();
  }


  @Override public void onDestory() {
    if(remoteRepo != null) {
      remoteRepo.cancelHttp();
    }
  }



  public void remote(String number){
    mView.showLoading();
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put(Constant.KEY_MOBILE, number);
      remoteRepo.remotePost(Constant.URL_MOBILE, jsonObject, ApplicationID.class, new RepoCallBack() {
        @Override public <T> void onSuccess(T response) {
          mView.showLoginPwdUi();
          mView.hideLoading();
          ApplicationID applicationID =  (ApplicationID)response;
          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.put(Constant.HEAD_APPLICATIONID, applicationID.getApplicationID());

          OkGo.getInstance().addCommonHeaders(httpHeaders);
        }

        @Override public void onFailed(String response) {
          mView.showToast(response);
          mView.hideLoading();
        }

        @Override public void onError(String response) {
          mView.showToast(response);
          mView.hideLoading();
        }
      });
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }


}
