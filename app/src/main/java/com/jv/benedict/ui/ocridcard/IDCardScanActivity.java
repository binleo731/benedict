package com.jv.benedict.ui.ocridcard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jv.benedict.R;
import com.jv.benedict.ui.BaseActivity;
import com.jv.benedict.ui.ocridcardresult.IDCardResultActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import exocr.engine.EngineManager;
import exocr.idcard.IDCardManager;

/**
 * OCR扫描身份证V层
 * Created by wj on 2018/1/4 19:56
 */
public class IDCardScanActivity extends BaseActivity<IDCardScanActivity, IDCardScanPresenter> implements IIDCardScanContract.IIDCardScanView {

    @BindView(R.id.tv_tips_one)
    TextView tvTipsOne;
    @BindView(R.id.tv_tips_two)
    TextView tvTipsTwo;
    @BindView(R.id.tv_idcard_ocr)
    TextView tvIdcardOcr;
    @BindView(R.id.ll_container_id)
    LinearLayout llContainerId;
    private boolean isFront = true;


    @Override
    protected IDCardScanPresenter createPresenter() {
        return new IDCardScanPresenter(IDCardManager.getInstance());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_id_card_scan;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        EngineManager.getInstance().initEngine(this);
        IDCardManager.getInstance().setView(null);
        IDCardManager.getInstance().setScanMode(IDCardManager.ID_IMAGEMODE_HIGH);
        IDCardManager.getInstance().setShowLogo(false);
        Logger.e("包名为："+this.getPackageName());
        IDCardManager.getInstance().setPackageName(this.getPackageName());
        IDCardManager.getInstance().setAutoFlash(true);
        getTitleTv().setText(R.string.id_card_title);
        getTitleTv().setTextColor(Color.BLACK);
        setProgressBar(50);
    }

    @Override
    public void initParms(Bundle parms) {

    }


    @OnClick(R.id.tv_idcard_ocr)
    public void onViewClicked() {
        getPresenter().scanIDCard(isFront);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EngineManager.getInstance().finishEngine();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setTips(boolean isFront) {
        this.isFront = isFront;
        runOnUiThread(() -> tvTipsTwo.setText(R.string.id_card_step_two));
    }

    @Override
    public void startResult() {
        startActivity(new Intent(IDCardScanActivity.this, IDCardResultActivity.class));
    }
}
