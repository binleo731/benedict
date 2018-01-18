package com.jv.benedict.ui.ocridcard;

import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BaseView;

/**
 * OCR扫描身份证契约类
 * Created by jwang on 2018/1/4.
 */

public interface IIDCardScanContract {
    interface IIDCardScanView extends BaseView {
        void setTips(boolean isFront);

        void startResult();
    }

    abstract class IIDCardScanPresenter extends BasePresenter<BaseView> {
        abstract void scanIDCard(boolean isFront);
    }
}
