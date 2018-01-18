package com.jv.benedict.ui.ocridcardresult;

import com.jv.benedict.model.bean.IDInformation;
import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import com.jv.benedict.repository.common.RepoCallBack;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.EmptyUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 身份证信息展示页P层
 * Created by jwang on 2018/1/10.
 */

public class IDCardResultPresenter extends IIDCardResultContract.IIDCardResultPresenter {
    private IRemoteRepo iRemoteRepo;
    private IIDCardResultContract.IIDCardResultView view;

    public IDCardResultPresenter(IRemoteRepo iRemoteRepo) {
        this.iRemoteRepo = iRemoteRepo;
    }

    @Override
    void postInfo() {
        if (EmptyUtils.isNotEmpty(getMvpView())) {
            view = (IIDCardResultContract.IIDCardResultView) getMvpView();
        }
        view.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constant.ID, Constant.front.cardnum);
            jsonObject.put(Constant.NAME, Constant.front.name);
            jsonObject.put(Constant.SEX, Constant.front.sex);
            jsonObject.put(Constant.ETHNIC, Constant.front.nation);
            jsonObject.put(Constant.DOB, Constant.front.birth);
            jsonObject.put(Constant.ADDRESS, Constant.front.address);
            jsonObject.put(Constant.ISSUEDDEPART, Constant.side.office);
            jsonObject.put(Constant.ISSUEDDATE, Constant.side.validdate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iRemoteRepo.remotePost(Constant.INFO, jsonObject, IDInformation.class, new RepoCallBack() {
            @Override
            public <T> void onSuccess(T response) {
                view.hideLoading();
                view.startBankCard();
            }

            @Override
            public void onFailed(String response) {
                view.hideLoading();
            }

            @Override
            public void onError(String response) {
                view.hideLoading();
            }
        });
    }
}
