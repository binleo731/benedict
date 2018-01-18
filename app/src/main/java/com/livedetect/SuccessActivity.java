package com.livedetect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jv.benedict.R;
import com.jv.benedict.repository.RemoteRepo.RemoteRepo;
import com.jv.benedict.ui.BaseActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 活体检测成功V层
 * Created by wj on 2018/1/11 16:22
 */
public class SuccessActivity extends BaseActivity<SuccessActivity, SuccessPresenter> implements ISuccessContract.ISuccessView {
    private final String TAG = SuccessActivity.class.getSimpleName();
    @BindView(R.id.tv_tips_one)
    TextView tvTipsOne;
    @BindView(R.id.tv_tips_two)
    TextView tvTipsTwo;
    @BindView(R.id.btn_to_account)
    Button btnToAccount;
    @BindView(R.id.btn_transfer)
    Button btnTransfer;
    @BindView(R.id.btn_product)
    Button btnProduct;
    @BindView(R.id.cl_success)
    ConstraintLayout clSuccess;


    @Override
    protected SuccessPresenter createPresenter() {
        return new SuccessPresenter(new RemoteRepo());
    }

    @Override
    public int bindLayout() {
        return R.layout.htjc_activity_success;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);
        getTitleTv().setText(R.string.face_success);
        getTitleTv().setTextColor(Color.BLACK);
        getBackIv().setVisibility(View.GONE);
        Intent mIntent = getIntent();
        Bundle result = mIntent.getBundleExtra(getString(R.string.face_result));
        if (null != result) {
            getPresenter().postFace(result);
        }
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @OnClick({R.id.btn_to_account, R.id.btn_transfer, R.id.btn_product})
    public void onViewClicked(View view) {
        // TODO: 2018/1/9 by jwang. You need to update later,do something what you want,just like following
        switch (view.getId()) {
            case R.id.btn_to_account:
                finish();
                Logger.e("account");
                break;
            case R.id.btn_transfer:
                break;
            case R.id.btn_product:
                Intent intent = new Intent(SuccessActivity.this, LiveDetectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(getString(R.string.face_isRandomable), true);
                bundle.putString(getString(R.string.face_actions), "01279");
                bundle.putString(getString(R.string.face_selectActionsNum), "3");
                bundle.putString(getString(R.string.face_singleActionDectTime), "8");
                bundle.putBoolean(getString(R.string.isWaterable), false);
                bundle.putBoolean(getString(R.string.face_openSound), true);
                intent.putExtra(getString(R.string.face_comprehensive_set), bundle);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
//        startActivity(new Intent(SuccessActivity.this, FeatureActivity.class));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showView() {
        clSuccess.setVisibility(View.VISIBLE);
    }
}
