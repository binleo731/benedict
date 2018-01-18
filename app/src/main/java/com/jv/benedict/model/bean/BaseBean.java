package com.jv.benedict.model.bean;

/**
 * bean基类
 * Created by leo on 2018/1/11.
 */

public class BaseBean {
    private String ReturnCode;
    private String ReturnMsg;

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

    @Override
    public String toString() {
        return "BaseBean{" +
                "ReturnCode='" + ReturnCode + '\'' +
                ", ReturnMsg='" + ReturnMsg + '\'' +
                '}';
    }
}
