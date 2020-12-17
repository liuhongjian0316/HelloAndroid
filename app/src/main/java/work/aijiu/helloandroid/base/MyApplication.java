package work.aijiu.helloandroid.base;

import android.app.Application;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description application
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE = null;

    public static Application getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
