package com.jv.benedict.utils;

import exocr.bankcard.EXBankCardInfo;

import exocr.exocrengine.EXIDCardResult;

/**
 * 常量
 * Created by wj on 2017/11/8 23:01
 */
public class Constant {

  // 7.0 FileProvider路径（要和清单文件配置的一样）
  public static final String PWDLOGIN = "pwd_login";
  public static final String TRANSPWD = "trans_pwd";
  public static final String LOGINPWDREGEX = "^([0-9])\\1{7,15}$";
  public static final String TRANSPWDREGEX = "^([0-9])\\1{5}$";
  public static String Ip;
  public static final boolean isDebug = true;
  public static final String CLASS_NAME = "class_name";
  public static final String APPNAME = "bjbank";
  public static final int REQUEST_CODE = 200;
  public static final String VALUE_TRANPWDTYPE = "transaction";
  public static final String VALUE_LOGINPWDTYPE = "login";

  public static final String KEY_PWD = "pwd";
  public static final String KEY_PWDTYPE = "pwdType";
  public static final String KEY_MOBILE = "mobileno";

  public static final String URL_PWD = "password";
  public static final String URL_MOBILE = "mobile";

  public static final String HEAD_APPLICATIONID = "applicationID";
  //    身份证信
  public static EXIDCardResult front;
  public static EXIDCardResult side;
  //    银行卡信
  public static EXBankCardInfo bankCardInfo;
  //    身份证信息交
  public static final String INFO = "information";
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String SEX = "sex";
  public static final String ETHNIC = "ethnic";
  public static final String DOB = "dob";
  public static final String ADDRESS = "address";
  public static final String ISSUEDDEPART = "issuedDepart";
  public static final String ISSUEDDATE = "issuedDate";
  //    银行卡交
  public static final String BANK_CARD = "bankcard";
  public static final String BANKCARDNO = "bankCardNo";
  public static final String BANKNAME = "bankName";
  public static final String NAMEONCARD = "nameOnCard";
  // 人脸识别
  public static final String FACE = "face";
  public static final String FACEIMG = "faceimg";
  // 开户
  public static final String CUSTOMER = "customer";
  static {
//              Ip = "http://10.166.3.13:8090/api/app/onboarding/";// 科蓝
//              Ip = "http://10.3.102.76:10086/v2/api/app/onboarding/";// 神马
               Ip = "http://10.3.102.51:9902/api/app/onboarding/";//赞同
    //            Ip = "http://10.3.102.10:9017/api/app/onboarding/";//长`亮
//                Ip = "http://10.3.101.247:11226/api/app/onboarding/";//宇信
  }

  /**
   * 正则：手机号（精确）
   */
  public static final String REGEX_MOBILE_EXACT =
      "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|(147))\\d{8}$";
  /**
   * 正则：邮
   */
  public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
}
