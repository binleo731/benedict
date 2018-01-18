package com.jv.benedict.model.bean;

/**
 * Created by Leo on 2018/1/10.
 */

public class ApplicationID {
  /**
   * applicationID
   * ReturnCode
   * ReturnMsg
   */
  private String applicationID;
  private String ReturnCode;
  private String ReturnMsg;

  public String getApplicationID() {
    return applicationID;
  }

  public void setApplicationID(String applicationID) {
    this.applicationID = applicationID;
  }

  public String getReturnCode() {
    return ReturnCode;
  }

  public void setReturnCode(String returnCode) {
    ReturnCode = returnCode;
  }

  public String getReturnMsg() {
    return ReturnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    ReturnMsg = returnMsg;
  }

}
