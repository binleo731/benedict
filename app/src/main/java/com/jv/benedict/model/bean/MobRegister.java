package com.jv.benedict.model.bean;

/**
 * Created by leo on 2018/1/3.
 */

public class MobRegister {

    /**
     * applicationID : 20130125000000000000000000002900
     * ReturnCode : 000000
     * ReturnMsg :
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

    public void setReturnCode(String ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    public String getReturnMsg() {
        return ReturnMsg;
    }

    public void setReturnMsg(String ReturnMsg) {
        this.ReturnMsg = ReturnMsg;
    }
}
