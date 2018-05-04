package zy.xuminghang.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
public class AddressHelper extends SQLiteOpenHelper{



    /*
      * 构造方法用来创建数据库
      */
    public AddressHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, null, version);
        // TODO Auto-generated constructor stub
    }

    /**
     * 此方法中创建数据表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table dosomething(id integer primary key autoincrement,customercode varchar(20),customername varchar(9),customerphoneno varchar(30),wherearea varchar(30),address varchar(10),isdefault int(2))";
        db.execSQL(sql);//至此数据库创建成功

    }

    /**
     * 此方法是当数据库的版本升级时此方法就会被调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
