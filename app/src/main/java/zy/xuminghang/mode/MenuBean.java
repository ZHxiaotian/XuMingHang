package zy.xuminghang.mode;

import java.util.List;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class NewCommodity {

//    "code": "3110101",
//            "name": "奶油蛋糕",
//            "artiList":
        String code;
        String name;
        List<NewCommodityson> artiList;

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

    public List<NewCommodityson> getArtiList() {
        return artiList;
    }

    public void setArtiList(List<NewCommodityson> artiList) {
        this.artiList = artiList;
    }

    @Override
    public String toString() {
        return "NewCommodity{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", artiList=" + artiList +
                '}';
    }
}
