package zy.xuminghang.entity;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class User {


//    code":null,"pfscode":"001","shr":"江河","tel":"18632172565","address":"怀特商业广场A21F","pfslb":null,"zip":null

    String code;//客户编码
    String pfscode;
    String shr;
    String tel;
    String address;
    String pfslb;//客户级别
    String zip;//


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPfscode() {
        return pfscode;
    }

    public void setPfscode(String pfscode) {
        this.pfscode = pfscode;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPfslb() {
        return pfslb;
    }

    public void setPfslb(String pfslb) {
        this.pfslb = pfslb;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
