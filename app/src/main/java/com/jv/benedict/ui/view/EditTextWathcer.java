package com.jv.benedict.ui.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.jv.benedict.ui.LoginPwdCallBack.PwdCallBack;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.SPUtils;

/**
 * Created by Leo on 2018/1/9.
 */

public class EditTextWathcer {
  private final int LENGTH_3 = 3;
  private final int LENGTH_8 = 8;
  private final int LENGTH_7 = 7;
  private final int LENGTH_1 = 1;
  private final int COUNT = 1;

  private EditText editText;
  private TextView textView;
  public TextWatcher getTextWatcher(EditText editText,TextView textView) {

    this.editText = editText;
    return new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count == COUNT) {
          int length = s.toString().length();
          if (length == LENGTH_3 || length == LENGTH_8) {
            editText.setText(s + " ");
            editText.setSelection(editText.getText().toString().length());
          }
        }
        textView.setText("");

      }

      @Override public void afterTextChanged(Editable s) {

      }
    };
  }

  public TextWatcher getTextWatcher(TextView textView, String text, int color) {

    this.textView = textView;
    return new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length()>LENGTH_7 || s.length() == LENGTH_1) {
          textView.setText(text);
          textView.setTextColor(color);
        }
      }

      @Override public void afterTextChanged(Editable s) {

      }
    };
  }

  public TextWatcher getLoginPwdTextWatcher(PwdCallBack loginPwdCallBack) {

    return new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override public void afterTextChanged(Editable s) {
        if (s.length() == SPUtils.getInstance().getString(Constant.PWDLOGIN).length()) {
          String str = s.toString();
          if (str.equals(SPUtils.getInstance().getString(Constant.PWDLOGIN))) {
            loginPwdCallBack.gotoNextFragment();
          }else{
            loginPwdCallBack.fail();
          }
        }else if(s.length() < SPUtils.getInstance().getString(Constant.PWDLOGIN).length()){
          loginPwdCallBack.initTextStyle();
        }
      }
    };
  }
}
