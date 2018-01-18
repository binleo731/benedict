package com.livedetect;

import android.os.Bundle;
import android.util.Base64;

import com.jv.benedict.R;
import com.jv.benedict.model.bean.BaseBean;
import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import com.jv.benedict.repository.common.RepoCallBack;
import com.jv.benedict.utils.Constant;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 活体检测V层
 * Created by jwang on 2018/1/11.
 */

public class SuccessPresenter extends ISuccessContract.ASuccessPresenter {
    private IRemoteRepo iRemoteRepo;
    private ISuccessContract.ISuccessView view;

    public SuccessPresenter(IRemoteRepo iRemoteRepo) {
        this.iRemoteRepo = iRemoteRepo;
    }

    @Override
    void postFace(Bundle bundle) {
        view = (ISuccessContract.ISuccessView) getMvpView();
        view.showLoading();
        JSONObject jsonObject = new JSONObject();
        boolean check_pass = bundle.getBoolean(((SuccessActivity) view).getString(R.string.face_check_pass));
        if (check_pass) {
            byte[] pic_result = bundle.getByteArray(((SuccessActivity) view).getString(R.string.face_pic_result));
            if (pic_result != null) {
                try {
                    jsonObject.put(Constant.FACEIMG, Base64.encodeToString(pic_result, Base64.NO_WRAP));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        iRemoteRepo.remotePost(Constant.FACE, jsonObject, BaseBean.class, new RepoCallBack() {
            @Override
            public <T> void onSuccess(T response) {
                iRemoteRepo.remotePost(Constant.CUSTOMER, new JSONObject(), BaseBean.class, new RepoCallBack() {
                    @Override
                    public <T> void onSuccess(T response) {
                        Logger.e(response.toString());
                        view.hideLoading();
                        view.showView();
                    }

                    @Override
                    public void onFailed(String response) {

                    }

                    @Override
                    public void onError(String response) {

                    }
                });
            }

            @Override
            public void onFailed(String response) {
                view.showLoading();
            }

            @Override
            public void onError(String response) {
                view.showLoading();
            }
        });
    }
}
