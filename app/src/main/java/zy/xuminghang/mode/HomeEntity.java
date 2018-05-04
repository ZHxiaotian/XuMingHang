package zy.xuminghang.entity;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class HomeEntity {

      private int img;
    private String  title;


    public HomeEntity(int img, String title) {
        this.img = img;
        this.title = title;
    }
    public HomeEntity() {

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

    @Override
    public String toString() {
        return "HomeEntity{" +
                "img=" + img +
                ", title='" + title + '\'' +
                '}';
    }
}
