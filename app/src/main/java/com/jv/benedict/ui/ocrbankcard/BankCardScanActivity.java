package com.jv.benedict.ui.ocrbankcard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jv.benedict.R;
import com.jv.benedict.repository.RemoteRepo.RemoteRepo;
import com.jv.benedict.ui.BaseActivity;
import com.jv.benedict.ui.verifyface.VerifyFaceActivity;
import com.jv.benedict.utils.EmptyUtils;
import com.jv.benedict.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import exocr.bankcard.BankManager;
import exocr.bankcard.DataCallBack;
import exocr.bankcard.EXBankCardInfo;

/**
 * OCR扫描银行卡V层
 * Created by wj on 2018/1/5 10:41
 */
public class BankCardScanActivity extends BaseActivity<BankCardScanActivity, BankCardScanPresenter> implements IBankCardScanContract.IBankCardView, DataCallBack {


    @BindView(R.id.tv_tips_one)
    TextView tvTipsOne;
    @BindView(R.id.tv_tips_two)
    TextView tvTipsTwo;
    @BindView(R.id.et_bank_card_number)
    EditText etBankCardNumber;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.tv_bank_type)
    TextView tvBankType;
    @BindView(R.id.tv_bank_list)
    TextView tvBankList;
    @BindView(R.id.btn_accept)
    Button btnAccept;

    @Override
    protected BankCardScanPresenter createPresenter() {
        return new BankCardScanPresenter(BankManager.getInstance(), new RemoteRepo());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bank_card_scan;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        getTitleTv().setText(R.string.id_card_title);
        getTitleTv().setTextColor(Color.BLACK);
        SpannableString spannableString = new SpannableString(getString(R.string.bank_list));
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvBankList.setText(spannableString);
        setProgressBar(80);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showBankNumber(String number, String bankType) {
        etBankCardNumber.setText(number);
        etBankCardNumber.setSelection(etBankCardNumber.getText().toString().trim().length());
        tvBankType.setVisibility(View.VISIBLE);
        tvBankType.setText(bankType);
        btnAccept.setEnabled(true);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvTipsOne.setText(R.string.card_tips_one);
            }
        });
    }

    @Override
    public void startFace() {
        String bankNumber = etBankCardNumber.getText().toString().trim();
        if (EmptyUtils.isEmpty(bankNumber)) {
            ToastUtils.showShort(R.string.bankcard_toast_one);
        } else if (bankNumber.length() != 16 && bankNumber.length() != 19) {
            ToastUtils.showShort(R.string.bankcard_toast_two);
        } else {
            startActivity(new Intent(BankCardScanActivity.this, VerifyFaceActivity.class));
        }
    }

    @OnClick({R.id.iv_camera, R.id.tv_bank_list, R.id.btn_accept})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_camera:
                getPresenter().scanBankCard();
                break;
            case R.id.tv_bank_list:
                break;
            case R.id.btn_accept:
                getPresenter().postFace();
                break;
        }
    }

    @Override
    public void onRecSuccess(int i, EXBankCardInfo exBankCardInfo) {

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
}
