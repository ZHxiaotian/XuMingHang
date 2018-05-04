package zy.xuminghang.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import zy.xuminghang.entity.ShopCar;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
public class OrderDao {


   private Context context;
    private OrderHelper helper;

    public OrderDao(Context context) {
        this.context = context;
        helper=new OrderHelper(context,"order",null,1);
    }


    /**
     * 将确认订单生成的商品信息存入数据库
     * @param car
     * @return
     */
     public long insertOrder(ShopCar car){

         SQLiteDatabase db=helper.getWritableDatabase();

         ContentValues cv=new ContentValues();
             cv.put("productdetial",car.getTitle());
             cv.put("imgurl",car.getImg());
             cv.put("price",car.getPrice());
             cv.put("discount",car.getDiscount());
             cv.put("count",car.getCount());
          long result= db.insert("orderdetial",null,cv);
         return result;

     }

    /**
     * 查询全部购物车的方法
     * @return
     */
    public List<ShopCar>  selectAll(){
//        img, String title, String price, String discount, int count, boolean ischose
        List<ShopCar>  carlist=new ArrayList<ShopCar>();
              SQLiteDatabase db=helper.getReadableDatabase();
                  ShopCar car=null;
              Cursor cursor= db.query("orderdetial",null,null,null,null,null,null,null);
              while (cursor.moveToNext()){
//                  car=new ShopCar(cursor.getInt(cursor.getColumnIndex("imgurl")),cursor.getString(cursor.getColumnIndex("productdetial")),""+cursor.getInt(cursor.getColumnIndex("price")),cursor.getInt(cursor.getColumnIndex("discount"))+"",cursor.getInt(cursor.getColumnIndex("discount")));
                  carlist.add(new ShopCar());
              }
              cursor.close();
              return  null;
    }

    /**
     *删除的方法
     */
    public void  deleteCar(ShopCar car){



    }



}
