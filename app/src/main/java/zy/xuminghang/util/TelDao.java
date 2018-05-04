package zy.xuminghang.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import zy.xuminghang.mode.AddressUser;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class TelDao {

    private Context context;
    private TeLAddressHelper helper;

    public TelDao(Context context) {
        this.context = context;
        helper=new TeLAddressHelper(context,"tel",null,1);
    }



    /**
     * 将确认订单生成的商品信息存入数据库
     *
     * @return
     */
    public long insertTel(AddressUser user){

        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("tel",user.getTel());
        cv.put("usercode",user.getUsercode());

        long result= db.insert("addressuser",null,cv);
        return result;

    }

    /**
     * 查询全部购物车的方法
     * @return
     */
    public List<AddressUser> selectAll(){
//        img, String title, String price, String discount, int count, boolean ischose
        List<AddressUser>  carlist=new ArrayList<AddressUser>();
        SQLiteDatabase db=helper.getReadableDatabase();
        AddressUser car=null;
        Cursor cursor= db.query("addressuser",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
//                  car=new ShopCar(cursor.getInt(cursor.getColumnIndex("imgurl")),cursor.getString(cursor.getColumnIndex("productdetial")),""+cursor.getInt(cursor.getColumnIndex("price")),cursor.getInt(cursor.getColumnIndex("discount"))+"",cursor.getInt(cursor.getColumnIndex("discount")));
            carlist.add(new AddressUser(cursor.getString(cursor.getColumnIndex("tel")),cursor.getString(cursor.getColumnIndex("usercode"))));
        }
        cursor.close();
        return  carlist;
    }




    /**
     * 按是否是默认地址查找
     */

    public AddressUser findByIsdefault(String usercode) {


        AddressUser bean = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        String whereClause = "usercode=?";
//修改添加参数
        String[] whereArgs = {usercode};
        Cursor cs = db.query("addressuser", null, "usercode=?", new String[]{usercode}, null, null, null, null);


        if (cs.moveToNext()) {

            bean = new AddressUser(
                    cs.getString(cs.getColumnIndex("tel")), cs
                    .getString(cs.getColumnIndex("usercode")));
        }
        cs.close();
        return bean;
    }

}
