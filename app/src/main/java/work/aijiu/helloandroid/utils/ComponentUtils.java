package work.aijiu.helloandroid.utils;

import android.app.Activity;
import android.view.Window;

import androidx.appcompat.app.ActionBar;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 组件工具类
 */
public class ComponentUtils {

    /**
     * 隐藏标题栏
     * @param activity
     */
    public static void hidenTitleBar(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
