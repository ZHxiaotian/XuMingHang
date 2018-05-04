package zy.xuminghang.entity;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class ShangPin {

//     "code": "74203010001",
//             "name": "三件套",
//             "lsj": "35",
//             "rongliang": "D:\\pic\\1001.jpg"


       private String code;
      private String name;
      private String lsj;
      private String rongliang;
      private int count;


    public ShangPin(String code, String name, String lsj, String rongliang) {
        this.code = code;
        this.name = name;
        this.lsj = lsj;
        this.rongliang = rongliang;
    }

    public ShangPin() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLsj() {
        return lsj;
    }

    public void setLsj(String lsj) {
        this.lsj = lsj;
    }

    public String getRongliang() {
        return rongliang;
    }

    public void setRongliang(String rongliang) {
        this.rongliang = rongliang;
    }

    @Override
    public String toString() {
        return "ShangPin{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", lsj=" + lsj +
                ", rongliang='" + rongliang + '\'' +
                '}';
    }
}
