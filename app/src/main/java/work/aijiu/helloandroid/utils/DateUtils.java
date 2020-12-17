package work.aijiu.helloandroid.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author aijiu
 * 时间工具类
 */
public class DateUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        Date currentTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(currentTime);
    }
}
