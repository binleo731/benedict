package com.jv.benedict.ui.verifyface;

import android.content.Intent;
import android.os.Bundle;

import com.jv.benedict.R;
import com.jv.benedict.utils.EmptyUtils;
import com.livedetect.LiveDetectActivity;

/**
 * 活体检测P层
 * Created by jwang on 2018/1/8.
 */

public class VerifyFacePresenter extends IVerifyFaceContract.FacePresenter {
    private IVerifyFaceContract.IFaceView view;
    private VerifyFaceActivity verifyFaceActivity;

    @Override
    void verifyFace() {
        if (EmptyUtils.isNotEmpty(getMvpView())) {
            view = (IVerifyFaceContract.IFaceView) getMvpView();
            verifyFaceActivity = (VerifyFaceActivity) view;
            Intent intent = new Intent(verifyFaceActivity,
                    LiveDetectActivity.class);
            Bundle bundle = new Bundle();

            bundle.putBoolean(verifyFaceActivity.getString(R.string.face_isRandomable), true);
            bundle.putString(verifyFaceActivity.getString(R.string.face_actions), "01279");
            bundle.putString(verifyFaceActivity.getString(R.string.face_selectActionsNum), "3");
            bundle.putString(verifyFaceActivity.getString(R.string.face_singleActionDectTime), "8");
            bundle.putBoolean(verifyFaceActivity.getString(R.string.isWaterable), false);
            bundle.putBoolean(verifyFaceActivity.getString(R.string.face_openSound), true);
            intent.putExtra(verifyFaceActivity.getString(R.string.face_comprehensive_set), bundle);
            view.startResult(intent);
        }

    }
}
