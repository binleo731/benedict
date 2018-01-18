package com.jv.benedict.ui.loginPwd;

import android.view.View;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseFragment;
import com.jv.benedict.ui.transpwd.TransPwdActivity;

/**
 * Created by Leo on 2018/1/3.
 */

public class SuccessBindFragment extends BaseFragment implements View.OnClickListener{
  @Override public int bindLayout() {
    return R.layout.fragment_setphonenumber_success;
  }

  @Override public void initCustomeViews(View view) {
    setProgressBar(PROGRESS_30);
    getTitleTv().setText(R.string.bind_phonenumber);
      view.findViewById(R.id.bt_next).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()){
      case R.id.bt_next:
        getBeneBaseActivity().startActivity(TransPwdActivity.class);
        break;
    }
  }
}
