package com.livedetect;

import android.os.Bundle;

import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BaseView;

/**
 * 活体检成功测契约类
 * Created by jwang on 2018/1/11.
 */

public interface ISuccessContract {
    interface ISuccessView extends BaseView {
        void showView();
    }

    abstract class ASuccessPresenter extends BasePresenter {
        abstract void postFace(Bundle bundle);
    }
}
