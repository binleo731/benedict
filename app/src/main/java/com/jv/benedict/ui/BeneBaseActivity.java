package com.jv.benedict.ui;

import android.support.v4.app.Fragment;
import com.jv.benedict.R;

/**
 * Created by Leo on 2018/1/2.
 * 所有需要管理fragment的activity 继承它
 * [管理fragment]
 */

public abstract class BeneBaseActivity extends BaseActivity {
  @Override public int bindLayout() {
    return R.layout.activity_benebase_layout;
  }

  public void addFragment(Fragment fragment){
    addFragment(R.id.bene_base_content, fragment, false);
  }
}
