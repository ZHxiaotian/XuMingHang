package zy.xuminghang.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import zy.xuminghang.entity.AddressManageBean;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
public class AddressDao {


    Context context;
    private AddressHelper helper;

    public AddressDao(Context context) {
        super();
        helper = new AddressHelper(context, "address.db", null, 1);
    }


    /**
     * 添加数据
     */

    public long addMessage(AddressManageBean things) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("customercode", things.getUsercode());
        cv.put("customername", things.getUsername());
        cv.put("customerphoneno", things.getUsetphoneno());
        cv.put("wherearea", things.getSzdq());
        cv.put("address", things.getDescaddress());
        cv.put("isdefault", things.isdefault());
        return db.insert("dosomething", null, cv);

    }

    /**
     * 查询全部查询
     */
    public List<AddressManageBean> selectAddress() {
        List<AddressManageBean> list = new ArrayList<AddressManageBean>();
        SQLiteDatabase db = helper.getWritableDatabase();
//     "customercode", things.getUsercode());
//        cv.put("customername", things.getUsername());
//        cv.put("customerphoneno", things.getUsetphoneno());
//        cv.put("wherearea", things.getSzdq());
//        cv.put("address", things.getDescaddress());
//        cv.put("isdefault
        AddressManageBean bean = null;
        Cursor cs = db.query("dosomething", null, null, null, null, null, null,
                null);
        while (cs.moveToNext()) {

            bean = new AddressManageBean(
                    cs.getString(cs.getColumnIndex("customercode")), cs
                    .getString(cs.getColumnIndex("customername")), cs
                    .getString(cs.getColumnIndex("customerphoneno")), cs
                    .getString(cs.getColumnIndex("wherearea")), cs
                    .getString(cs.getColumnIndex("address")), cs.getInt(cs.getColumnIndex("isdefault")));
            bean.setId(cs.getInt(cs.getColumnIndex("id")));
            list.add(bean);
        }
        cs.close();
        System.out.println(list.toString());
        return list;
    }

    /**
     * 修改的方法
     */

    public int updateMsg(AddressManageBean bean) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
//在values中添加内容
        values.put("isdefault", bean.isdefault());
//修改条件
        String whereClause = "customercode=?";
//修改添加参数
        String[] whereArgs = {bean.getUsercode()};
//修改
        return db.update("dosomething", values, whereClause, whereArgs);


    }

    /**
     * 修改全部的是否是默认地址项
     */

    public int updateAlladdress() {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
//在values中添加内容
        values.put("isdefault", 0);
//修改条件
        String whereClause = "?";
//修改添加参数
        String[] whereArgs = {};
//修改
        return db.update("dosomething", values, null, null);
    }

    /**
     * 删除的方法
     */
    public void deleteMsg() {
        SQLiteDatabase db = helper.getWritableDatabase();


    }

    /**
     * 按是否是默认地址查找
     */

    public AddressManageBean findByIsdefault() {


        AddressManageBean bean = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        String whereClause = "isdefault=?";
//修改添加参数
        String[] whereArgs = {"0"};
        Cursor cs = db.query("dosomething", null, "isdefault=?", new String[]{"1"}, null, null, null, null);


        if (cs.moveToNext()) {

            bean = new AddressManageBean(
                    cs.getString(cs.getColumnIndex("customercode")), cs
                    .getString(cs.getColumnIndex("customername")), cs
                    .getString(cs.getColumnIndex("customerphoneno")), cs
                    .getString(cs.getColumnIndex("wherearea")), cs
                    .getString(cs.getColumnIndex("address")), cs.getInt(cs.getColumnIndex("isdefault")));
        }
        cs.close();
        return bean;
    }


    /**
     * 删除某个地址
     *
     * @param id
     * @return
     */
    public int deleteAddress(String id) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = new String[]{id};
        int flag = db.delete("dosomething", whereClause, whereArgs);

        db.close();
        return flag;
    }

    /**
     * 修改地址信息
     */
    public long updateAddressbyid(AddressManageBean bean) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
//在values中添加内容
        values.put("customercode", bean.getUsercode());
        values.put("customername", bean.getUsername());
        values.put("customerphoneno", bean.getUsetphoneno());
        values.put("wherearea", bean.getSzdq());
        values.put("address", bean.getDescaddress());
        values.put("isdefault", bean.isdefault());
//修改条件
        String whereClause = "id=?";
//修改添加参数
        String[] whereArgs = {""+bean.getId()};
//修改
        return db.update("dosomething", values, whereClause, whereArgs);


    }


}
