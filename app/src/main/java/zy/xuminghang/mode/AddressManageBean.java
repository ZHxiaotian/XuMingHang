package zy.xuminghang.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
public class AddressManageBean implements Serializable {

    private int id;
    private String usercode;
    private String username;
    private String usetphoneno;
    private String szdq;
    private String descaddress;
    private int isdefault;

    public AddressManageBean(String usercode, String username, String usetphoneno, String szdq, String descaddress,int isdefault) {
        this.usercode = usercode;
        this.username = username;
        this.usetphoneno = usetphoneno;
        this.szdq = szdq;
        this.descaddress = descaddress;
        this.isdefault=isdefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int isdefault() {
        return isdefault;
    }

    public void setIsdefault(int isdefault) {
        this.isdefault = isdefault;
    }

    public AddressManageBean() {
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsetphoneno() {
        return usetphoneno;
    }

    public void setUsetphoneno(String usetphoneno) {
        this.usetphoneno = usetphoneno;
    }

    public String getSzdq() {
        return szdq;
    }

    public void setSzdq(String szdq) {
        this.szdq = szdq;
    }

    public String getDescaddress() {
        return descaddress;
    }

    public void setDescaddress(String descaddress) {
        this.descaddress = descaddress;
    }

    @Override
    public String toString() {
        return "AddressManageBean{" +
                "usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", usetphoneno='" + usetphoneno + '\'' +
                ", szdq='" + szdq + '\'' +
                ", descaddress='" + descaddress + '\'' +
                ", isdefault=" + isdefault +
                '}';
    }
}
