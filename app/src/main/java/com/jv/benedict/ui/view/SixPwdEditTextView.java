package com.jv.benedict.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jv.benedict.R;
import com.jv.benedict.ui.LoginPwdCallBack.PwdCallBack;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.SPUtils;

import cn.passguard.PassGuardEdit;

/**
 * Created by Leo on 2018/1/5.
 */

public class SixPwdEditTextView extends LinearLayout {
  private PassGuardEdit et_transpwd;
  private LinearLayout pass_img_ll;
  private ImageView pass_radio_1;
  private ImageView pass_radio_2;
  private ImageView pass_radio_3;
  private ImageView pass_radio_4;
  private ImageView pass_radio_5;
  private ImageView pass_radio_6;

  public final int TRANS_PWD_LENGTH_0 = 0;
  public final int TRANS_PWD_LENGTH_1 = 1;
  public final int TRANS_PWD_LENGTH_2 = 2;
  public final int TRANS_PWD_LENGTH_3 = 3;
  public final int TRANS_PWD_LENGTH_4 = 4;
  public final int TRANS_PWD_LENGTH_5 = 5;
  public final int TRANS_PWD_LENGTH_6 = 6;
  public final int TRANS_PWD_LENGTH_7 = 7;
  PwdCallBack loginPwdCallBack;
  public SixPwdEditTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initview(context);



    TextWatcher mEditText = new TextWatcher() {
      private CharSequence temp;

      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        temp = s;
      }

      @Override public void afterTextChanged(Editable s) {
        if (getlenght(et_transpwd) < TRANS_PWD_LENGTH_7) {

          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_0) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_null);
            loginPwdCallBack.initTextStyle();
          }
          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_1) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_null);
            loginPwdCallBack.initTextStyle();
          }
          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_2) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_null);
            loginPwdCallBack.initTextStyle();
          }
          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_3) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_null);
            loginPwdCallBack.initTextStyle();
          }
          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_4) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_null);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_null);
            loginPwdCallBack.initTextStyle();
          }
          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_5) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_null);
            loginPwdCallBack.initTextStyle();
          }
          if (getlenght(et_transpwd) == TRANS_PWD_LENGTH_6) {
            pass_radio_1.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_2.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_3.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_4.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_5.setBackgroundResource(R.drawable.passnumber_left_in);
            pass_radio_6.setBackgroundResource(R.drawable.passnumber_ringht_in);
            if(et_transpwd.getText().toString().equals(SPUtils.getInstance().getString(Constant.TRANSPWD))) {
                loginPwdCallBack.gotoNextFragment();
            }else{
                loginPwdCallBack.fail();
            }
          }
        }
      }
    };
    et_transpwd.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        et_transpwd.setVisibility(GONE);
        pass_img_ll.setVisibility(VISIBLE);
      }
    });
    et_transpwd.addTextChangedListener(mEditText);
    et_transpwd.setBackgroundResource(R.drawable.numberpassed_img_bg);

    pass_img_ll.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        et_transpwd.setText("");
        pass_img_ll.setVisibility(VISIBLE);
        et_transpwd.StartPassGuardKeyBoard();
      }
    });
  }

  private void initview(Context context) {
    inflate(context, R.layout.view_sixpwd_et, this);
    et_transpwd = (PassGuardEdit) findViewById(R.id.et_transpwd);
    pass_img_ll = (LinearLayout) findViewById(R.id.pass_img_ll);
    pass_radio_1 = (ImageView) findViewById(R.id.pass_radio_1);
    pass_radio_2 = (ImageView) findViewById(R.id.pass_radio_2);
    pass_radio_3 = (ImageView) findViewById(R.id.pass_radio_3);
    pass_radio_4 = (ImageView) findViewById(R.id.pass_radio_4);
    pass_radio_5 = (ImageView) findViewById(R.id.pass_radio_5);
    pass_radio_6 = (ImageView) findViewById(R.id.pass_radio_6);
  }

  private int getlenght(EditText et) {
    int length = et.getText().length();
    return length;
  }

  public String getText() {
    return et_transpwd.getText().toString();
  }

  public PassGuardEdit getPassGuardEdit() {
    return et_transpwd;
  }

  public void addChangeListern(PwdCallBack loginPwdCallBack){
      this.loginPwdCallBack = loginPwdCallBack;

  }
}
