package com.jv.benedict.ui.ocridcardresult;

import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BaseView;

/**
 * 身份证信心展示契约类
 * Created by jwang on 2018/1/10.
 */

public interface IIDCardResultContract {
    interface IIDCardResultView extends BaseView {
        void startBankCard();
    }

    abstract class IIDCardResultPresenter extends BasePresenter<BaseView> {
        abstract void postInfo();
    }
}
