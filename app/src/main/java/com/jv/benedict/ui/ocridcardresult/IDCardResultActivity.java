package com.jv.benedict.ui.ocridcardresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jv.benedict.R;
import com.jv.benedict.repository.RemoteRepo.RemoteRepo;
import com.jv.benedict.ui.BaseActivity;
import com.jv.benedict.ui.ocrbankcard.BankCardScanActivity;
import com.jv.benedict.ui.ocridcard.IDCardScanActivity;
import com.jv.benedict.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import exocr.exocrengine.EXIDCardResult;

/**
 * OCR身份证信息展示页V层
 * Created by wj on 2018/1/4 18:27
 */
public class IDCardResultActivity extends BaseActivity<IDCardResultActivity, IDCardResultPresenter> implements IIDCardResultContract.IIDCardResultView {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_avatar)
    LinearLayout llAvatar;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_id_number)
    TextView tvIdNumber;
    @BindView(R.id.et_id_address)
    EditText etIdAddress;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_accept)
    TextView tvAccept;
    @BindView(R.id.tv_rescan)
    TextView tvRescan;


    @Override
    protected IDCardResultPresenter createPresenter() {
        return new IDCardResultPresenter(new RemoteRepo());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_id_card_result;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        SpannableString spannableString = new SpannableString(getString(R.string.id_card_edit));
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvEdit.setText(spannableString);
        EXIDCardResult front = Constant.front;
        EXIDCardResult side = Constant.side;
        ivAvatar.setImageBitmap(front.GetFaceBitmap());
        tvName.append(front.name);
        tvSex.setText(front.sex);
        tvAge.setText(front.birth);
        tvIdNumber.setText(front.cardnum);
        etIdAddress.setText(front.address);
        tvDate.setText(side.validdate);
        tvSign.setText(side.office);
        getTitleTv().setText(R.string.id_card_title);
        getTitleTv().setTextColor(Color.BLACK);
        setProgressBar(50);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @OnClick({R.id.tv_edit, R.id.tv_accept, R.id.tv_rescan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                etIdAddress.setEnabled(true);
                etIdAddress.setSelection(etIdAddress.getText().toString().length());
                break;
            case R.id.tv_accept:
                getPresenter().postInfo();
                break;
            case R.id.tv_rescan:
                startActivity(new Intent(this, IDCardScanActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void startBankCard() {
        startActivity(new Intent(IDCardResultActivity.this, BankCardScanActivity.class));
    }
}
