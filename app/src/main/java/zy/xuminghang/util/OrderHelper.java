package zy.xuminghang.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
public class OrderHelper extends SQLiteOpenHelper {


    /*
      * 构造方法用来创建数据库
      */
    public OrderHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
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
        String sql = "create table orderdetial (id integer primary key autoincrement,productdetial varchar(20),imgurl varchar(30),price Integer(10),discount Integer(10),count Integer (10))";
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
