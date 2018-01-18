package com.jv.benedict.ui.register;

import android.view.View;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseFragment;

/**
 * Created by Leo on 2018/1/2.
 */

public class RegisterReadyFragment extends BaseFragment implements View.OnClickListener{
  @Override public int bindLayout() {
    return R.layout.fragment_register_first;
  }

  @Override public void initCustomeViews(View view) {
      setProgressBar(PROGRESS_10);
      getTitleTv().setText(R.string.prepare_items);
      view.findViewById(R.id.bt_next).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
      switch (v.getId()){
        case R.id.bt_next :
          RegisterActivity act = (RegisterActivity)getActivity();
          act.gotoRegisterSetPhoneNumFragment();
          break;
      }
  }
}
