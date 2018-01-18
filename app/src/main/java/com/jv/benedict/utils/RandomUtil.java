package com.jv.benedict.utils;

/**
 * Created by Leo on 2018/1/9.
 */

public class RandomUtil {

  private static String string;

  public static String getPhoneNumber() {
    int a[] = new int[11];
    String[] str = new String[11];
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < a.length; i++) {
      a[i] = (int) (10 * (Math.random()));
      sb.append(a[i]);
    }
    string = sb.toString();
    return "1" + string.substring(1, 3) + " " + string.substring(3, 7) + " " + string.substring(7, 11);
  }
}
