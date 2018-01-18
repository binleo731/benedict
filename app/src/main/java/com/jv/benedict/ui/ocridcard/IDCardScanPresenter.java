package com.jv.benedict.ui.ocridcard;

import android.Manifest;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.EmptyUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import exocr.exocrengine.EXIDCardResult;
import exocr.idcard.IDCardManager;

/**
 * OCR扫描身份证P层
 * Created by jwang on 2018/1/4.
 */

public class IDCardScanPresenter extends IIDCardScanContract.IIDCardScanPresenter {
    private IIDCardScanContract.IIDCardScanView iidCardScanView;
    private IDCardManager idCardManager;

    public IDCardScanPresenter(IDCardManager idCardManager) {
        this.idCardManager = idCardManager;
    }

    @Override
    void scanIDCard(boolean isFront) {
        if (EmptyUtils.isNotEmpty(getMvpView())) {
            iidCardScanView = (IIDCardScanContract.IIDCardScanView) getMvpView();
            AndPermission.with((IDCardScanActivity) iidCardScanView)
                    .requestCode(Constant.REQUEST_CODE)
                    .permission(Manifest.permission.CAMERA)
                    .callback(new PermissionListener() {
                        @Override
                        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                            if (requestCode == Constant.REQUEST_CODE) {
                                // 跳转到身份证识别
                                idCardManager.recognizeWithSide(new IDCardManager.IDCallBack() {
                                    @Override
                                    public void onRecSuccess(int i, EXIDCardResult exidCardResult) {
                                        if (1 == exidCardResult.type) {
                                            Constant.front = exidCardResult;
                                            iidCardScanView.setTips(false);
                                        } else if (2 == exidCardResult.type) {
                                            Constant.side = exidCardResult;
                                            iidCardScanView.startResult();
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
                                }, (IDCardScanActivity) iidCardScanView, isFront);
                            }
                        }

                        @Override
                        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                        }
                    })
                    .start();
        }
    }
}
