package com.jv.benedict.model.bean;

/**
 * Created by jwang on 2018/1/11.
 */

public class BankCard {
    /**
     * bankCardNo : string
     * bankName : string
     * nameOnCard : string
     */

    private String bankCardNo;
    private String bankName;
    private String nameOnCard;

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
}
