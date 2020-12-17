package work.aijiu.helloandroid.dao.history_record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import work.aijiu.helloandroid.dao.DbHelper;
import work.aijiu.helloandroid.model.HistoryRecord;
import work.aijiu.helloandroid.utils.DateUtils;

/**
 * @author aijiu
 * @time 2020-12-17
 */
public class HistoryDao {

    /**
     * 创建数据库
     */
    private DbHelper dbHelper;

    private static HistoryDao instance;

    private String tabHistory = "history";

    private HistoryDao(Context context) {
        dbHelper = new DbHelper(context, "history.db", null, 1);
    }

    public static synchronized HistoryDao getInstance(Context context) {
        if (instance == null) {
            instance = new HistoryDao(context);
        }
        return instance;
    }

    /**
     * 增加历史
     * @param name
     */
    public void addHistory(String name){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lasttime", DateUtils.getCurrentTime());
        if (getIdByName(name)==0) {
            values.put("name", name);

            db.insert(tabHistory, null, values);
        } else {
            db.update(tabHistory,values,"name = ?", new String[]{name});
        }
    }

    /**
     * 根据_ID删除历史记录
     * @param _id
     */
    public void delHistory(int _id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(tabHistory,"_id"+_id,null);
    }

    /**
     * 删除全部历史记录
     * @param historyRecords
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void delAllHistory(List<HistoryRecord> historyRecords){
        historyRecords.forEach(i-> delHistory(i.getId()));
    }

    /**
     * 获取全部历史记录
     * @return
     */
    public List<HistoryRecord> getAllHistoryDate(){
        //历史记录集合
        List<HistoryRecord> result = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from history order by lasttime desc;", null);
        while(cursor.moveToNext()){
            //get _id
            int _id = cursor.getInt(0);
            //get name
            String name = cursor.getString(cursor.getColumnIndex("name"));
            //add entity
            result.add(new HistoryRecord(_id,name));
        }
        cursor.close();
        SystemClock.sleep(1000);// 休眠2秒，数据比较多，比较耗时的情况
        return result;
    }

    /**
     * 根据名称获得_ID
     */
    public int getIdByName(String name) {
        int id = 0;
        //获得一个可读的数据库的一个引用
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询  表   列   条件
        Cursor cursor = db.query(tabHistory, null, "name = ?", new String[]{name}, null, null, null);

        if (cursor.moveToNext()) {// 如果查到了，移动成功
            id = cursor.getInt(0);

        }
        cursor.close();
        return id;
    }

    /**
     * 获取历史记录数量
     * @return
     */
    public int getHistoryCount(){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(tabHistory,new String[]{"count(*)"},
                null, null, null, null, null);
        cursor.moveToNext();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }


}
