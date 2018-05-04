package zy.xuminghang.mode;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class NewCommodityson {
//    "code": "31101010006",
//            "name": "蛋糕房包装糕点",
//            "lsj": "0",
//            "rongliang": " ",
//            "dzxl": "3110101"
          private String code;
         private String name;
        private String lsj;
     private String rongliang;
     private String dzxl;
     private int count;

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

    public String getDzxl() {
        return dzxl;
    }

    public void setDzxl(String dzxl) {
        this.dzxl = dzxl;
    }

    @Override
    public String toString() {
        return "NewCommodityson{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", lsj='" + lsj + '\'' +
                ", rongliang='" + rongliang + '\'' +
                ", dzxl='" + dzxl + '\'' +
                ", count=" + count +
                '}';
    }
}
