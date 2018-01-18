package com.jv.benedict.model.bean;

/**
 * Created by leo on 2018/1/10.
 */

public class IDInformation {

    /**
     * id : string
     * name : string
     * sex : string
     * ethnic : string
     * dob : string
     * address : string
     * issuedDepart : string
     * issuedDate : string
     */

    private String id;
    private String name;
    private String sex;
    private String ethnic;
    private String dob;
    private String address;
    private String issuedDepart;
    private String issuedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssuedDepart() {
        return issuedDepart;
    }

    public void setIssuedDepart(String issuedDepart) {
        this.issuedDepart = issuedDepart;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }
}
