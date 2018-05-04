package zy.xuminghang.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class TeLAddress extends SQLiteOpenHelper {


    public TeLAddress(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "tel", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table orderdetial (id integer primary key autoincrement,tel varchar(20))";
        db.execSQL(sql);//至此数据库创建成功
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}




