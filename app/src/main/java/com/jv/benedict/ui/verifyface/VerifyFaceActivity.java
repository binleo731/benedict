package com.jv.benedict.ui.verifyface;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jv.benedict.R;
import com.jv.benedict.ui.BaseActivity;
import com.jv.benedict.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 活体检测V层
 * Created by wj on 2018/1/8 19:20
 */
public class VerifyFaceActivity extends BaseActivity<VerifyFaceActivity, VerifyFacePresenter> implements IVerifyFaceContract.IFaceView {

    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.ll_avatar)
    LinearLayout llAvatar;
    @BindView(R.id.tv_face)
    TextView tvFace;

    @Override
    protected VerifyFacePresenter createPresenter() {
        return new VerifyFacePresenter();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_verify_face;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        ivAvatar.setImageBitmap(Constant.front.GetFaceBitmap());
        getTitleTv().setText(R.string.id_card_title);
        getTitleTv().setTextColor(Color.BLACK);
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


    @OnClick(R.id.tv_face)
    public void onViewClicked() {
        getPresenter().verifyFace();
    }

    @Override
    public void startResult(Intent intent) {
        startActivity(intent);
    }
}
