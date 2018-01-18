package com.jv.benedict.ui.ocrbankcard;

import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.BaseView;

/**
 * OCR扫描银行卡契约类
 * Created by jwang on 2018/1/4.
 */

public interface IBankCardScanContract {
    interface IBankCardView extends BaseView {
        void showBankNumber(String number, String bankType);

        void startFace();
    }

    abstract class IBankCardPresenter extends BasePresenter<BaseView> {
        abstract void scanBankCard();

        abstract void postFace();
    }
}
