package work.aijiu.helloandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author aijiu
 * @time 2020-12-17
 * @Description
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表(名称记录)：自增长的主键，名称，最后一次更新时间
        db.execSQL("create table history(_id integer primary key autoincrement, name varchar(20) , lasttime varchar(50));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}
