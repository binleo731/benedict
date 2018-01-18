package com.jv.benedict.ui.features;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.jv.benedict.R;
import com.jv.benedict.ui.BaseActivity;
import com.jv.benedict.ui.BasePresenter;
import com.jv.benedict.ui.register.RegisterActivity;
import com.jv.benedict.utils.Constant;

/**
 * Created by Leo on 2018/1/2.
 */

public class FeatureActivity extends BaseActivity implements View.OnClickListener {

  private String items[] = {
      "http://10.166.3.13:8090/api/app/onboarding/",
      "http://10.3.102.76:10086/v2/api/app/onboarding/",
      "http://10.3.102.51:9902/api/app/onboarding/",
      "http://10.3.102.10:9017/api/app/onboarding/",
      "http://10.3.101.247:11226/api/app/onboarding/",
  };
  @Override protected BasePresenter createPresenter() {
    return null;
  }

  @Override public int bindLayout() {
    return R.layout.activity_features;
  }

  @Override public void initView(View view) {
        getHeadView().setVisibility(View.GONE);
        view.findViewById(R.id.fea_bt_openaccount).setOnClickListener(this);
        view.findViewById(R.id.fea_bt_login).setOnClickListener(this);
        view.findViewById(R.id.fea_bt_co_merchant).setOnClickListener(this);
        view.findViewById(R.id.iv_changeip).setOnClickListener(this);
  }

  @Override public void initParms(Bundle parms) {

  }

  @Override public void onClick(View v) {
      switch (v.getId()){
        case R.id.fea_bt_openaccount :
          startActivity(RegisterActivity.class);
          break;

        case R.id.fea_bt_login :

          break;

        case R.id.fea_bt_co_merchant :

          break;
        case R.id.iv_changeip:
          AlertDialog.Builder builder = new AlertDialog.Builder(FeatureActivity.this);
          builder.setTitle(R.string.select_ip);
          builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
              Constant.Ip = items[which].toString();
            }
          });
          builder.show();
          break;
      }
  }
}
