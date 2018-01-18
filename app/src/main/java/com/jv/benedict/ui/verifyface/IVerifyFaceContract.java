package com.jv.benedict.ui.verifyface;

import android.content.Intent;

import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BaseView;

/**
 * 活体检测contract类
 * Created by jwang on 2018/1/8.
 */

public interface IVerifyFaceContract {
    interface IFaceView extends BaseView {
        void startResult(Intent intent);
    }

    abstract class FacePresenter extends BasePresenter<BaseView> {
        abstract void verifyFace();
    }
}
