package com.jv.benedict.utils;

/**
 * Created by Leo on 2018/1/9.
 */

public class CheckPwdUtils {
  //判断是否是连续数字
  public static boolean isOrderNumberic(String str){
    boolean flag = true;
    boolean isNumeric = true;
    for(int i = 0; i < str.length(); i++){
      if(!Character.isDigit(str.charAt(i))){
        isNumeric = false;
        break;
      }
    }
    if(isNumeric){
      for(int i = 0; i < str.length(); i++){
        if(i > 0){
          int num = Integer.parseInt(str.charAt(i)+"");
          int num_ = Integer.parseInt(str.charAt(i-1)+"")+1;
          if(num != num_){
            flag = false;
            break;
          }
        }
      }
    }else{
      flag = false;
    }
    return flag;
  }
}
