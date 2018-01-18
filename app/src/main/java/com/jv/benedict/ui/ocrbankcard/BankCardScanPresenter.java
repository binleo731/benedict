package com.jv.benedict.ui.ocrbankcard;

import android.graphics.Bitmap;

import com.jv.benedict.model.bean.BankCard;
import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import com.jv.benedict.repository.common.RepoCallBack;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.EmptyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import exocr.bankcard.BankManager;
import exocr.bankcard.DataCallBack;
import exocr.bankcard.EXBankCardInfo;

/**
 * OCR扫描银行卡P层
 * Created by jwang on 2018/1/4.
 */

public class BankCardScanPresenter extends IBankCardScanContract.IBankCardPresenter {
    private IBankCardScanContract.IBankCardView iBankCardView;
    private BankManager bankManager;
    private IRemoteRepo iRemoteRepo;
    private IBankCardScanContract.IBankCardView view;

    public BankCardScanPresenter(BankManager bankManager, IRemoteRepo iRemoteRepo) {
        this.bankManager = bankManager;
        this.iRemoteRepo = iRemoteRepo;
    }

    @Override
    void scanBankCard() {
        if (EmptyUtils.isNotEmpty(getMvpView())) {
            iBankCardView = (IBankCardScanContract.IBankCardView) getMvpView();
            bankManager.showLogo(false);
            bankManager.recognize(new DataCallBack() {
                @Override
                public void onRecSuccess(int i, EXBankCardInfo exBankCardInfo) {
                    Constant.bankCardInfo = exBankCardInfo;
                    if (EmptyUtils.isNotEmpty(getMvpView())) {
                        iBankCardView.showBankNumber(exBankCardInfo.strNumbers, exBankCardInfo.strBankName);
                    }
                }

                @Override
                public void onRecCanceled(int i) {

                }

                @Override
                public void onRecFailed(int i, Bitmap bitmap) {

                }

                @Override
                public void onCameraDenied() {

                }
            }, (BankCardScanActivity) iBankCardView);
        }
    }

    @Override
    void postFace() {
        view = (IBankCardScanContract.IBankCardView) getMvpView();
        view.showLoading();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constant.BANKCARDNO, Constant.bankCardInfo.strNumbers);
            jsonObject.put(Constant.BANKNAME, Constant.bankCardInfo.strBankName);
            jsonObject.put(Constant.NAMEONCARD, Constant.bankCardInfo.strCardName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iRemoteRepo.remotePost(Constant.BANK_CARD, jsonObject, BankCard.class, new RepoCallBack() {
            @Override
            public <T> void onSuccess(T response) {
                view.hideLoading();
                view.startFace();
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
