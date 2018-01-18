package com.jv.benedict.ui.transpwd;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.LoginPwdCallBack.PwdCallBack;
import com.jv.benedict.ui.ocridcard.IDCardScanActivity;
import com.jv.benedict.ui.view.SixPwdEditTextView;

/**
 * Created by Leo on 2018/1/4.
 */

public class TransPwdConfirmFragment extends BaseFragment implements View.OnClickListener,
    TransPwdCntract.TransPwdView{
  TransPwdCntract.TransPwdPresenter mPresenter;
  SixPwdEditTextView spet_transpwd_confirm;
  TextView tv_transpwd_confirm_msg;

  @Override public int bindLayout() {
    return R.layout.fragment_transpwd_confirm;
  }

  @Override public void initCustomeViews(View view) {
    setProgressBar(PROGRESS_40);
      getTitleTv().setText(R.string.account_security);
      view.findViewById(R.id.bt_next).setOnClickListener(this);
       spet_transpwd_confirm = (SixPwdEditTextView) view.findViewById(R.id.spet_transpwd_confirm);
       tv_transpwd_confirm_msg = (TextView) view.findViewById(R.id.tv_transpwd_confirm_msg);
      spet_transpwd_confirm.addChangeListern(new PwdCallBack() {
       @Override public void gotoNextFragment() {
               mPresenter.checkTransPwdConfirm(spet_transpwd_confirm.getText().toString());
      }

        @Override public void fail() {
            initTextStyleError(tv_transpwd_confirm_msg, getRes().getString(R.string.input_pwd_confirm));
        }

        @Override public void initTextStyle() {
            initTextStyleNormal(tv_transpwd_confirm_msg, getRes().getString(R.string.again_input_sample_login_pwd));
        }
      });
      initSixPassGuardEdit(spet_transpwd_confirm.getPassGuardEdit());
  }

  @Override public void onClick(View v) {
    switch (v.getId()){
      case  R.id.bt_next:
        mPresenter.checkTransPwdConfirm(spet_transpwd_confirm.getText().toString());
        break;
    }
  }

  @Override public void setPresenter(TransPwdCntract.TransPwdPresenter presenter) {
    mPresenter = presenter;
  }

  @Override public boolean isActive() {
    return isActive();
  }

  @Override public Resources getRes() {
    return getBeneBaseActivity().getResources();
  }

  @Override public void gotoTransPwdConfirmFragment() {

  }

  @Override public void gotoOcR() {
      getBeneBaseActivity().startActivity(IDCardScanActivity.class);
  }

  @Override public void showToast(String msg) {
    initTextStyleError(tv_transpwd_confirm_msg, msg);
  }
}
