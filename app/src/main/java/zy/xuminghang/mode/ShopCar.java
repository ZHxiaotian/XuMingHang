package zy.xuminghang.entity;

/**
 * Created by Administrator on 2017/9/5 0005.
 */
public class ShopCar {

    private int id;
    private int img;//商品图片地址
    private String title;//商品详情
    private String price;//商品原价
    private String discount;//商品折扣价格
    private int count;//购物车的商品数量
    private boolean ischose;

    public ShopCar() {

    }

    public ShopCar(int img, String title, String price, String discount, int count, boolean ischose) {
        this.img = img;
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.count = count;
        this.ischose = ischose;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public boolean ischose() {
        return ischose;
    }

    public void setIschose(boolean ischose) {
        this.ischose = ischose;
    }


    @Override
    public String toString() {
        return "ShopCar{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", ischose=" + ischose +
                '}';
    }
}
