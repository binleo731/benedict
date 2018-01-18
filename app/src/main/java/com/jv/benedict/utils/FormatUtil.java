package com.jv.benedict.utils;

/**
 * Created by Leo on 2018/1/4.
 */

public class FormatUtil {

  private static FormatUtil formatUtil;

  public static FormatUtil getInstance() {
    if (null == formatUtil) {
      return formatUtil = new FormatUtil();
    }
    return formatUtil;
  }
  public String FormatPhoneNumber(String phone){
    return phone.substring(0,3) + phone.substring(4,8) +  phone.substring(9,13);
  }
}
