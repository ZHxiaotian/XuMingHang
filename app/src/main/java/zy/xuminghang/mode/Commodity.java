package zy.xuminghang.entity;

/**
 * 商品详情的实体类
 * Created by Administrator on 2017/8/23 0023.
 */
public class Commodity {


    private int imgurl;//商品图片
    private String detial;//商品详情
    private int price;//商品加个
    private int count;//商品数量

    public Commodity(int imgurl, String detial, int price, int count) {
        this.imgurl = imgurl;
        this.detial = detial;
        this.price = price;
        this.count = count;
    }

    public Commodity() {
    }

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "imgurl=" + imgurl +
                ", detial='" + detial + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
