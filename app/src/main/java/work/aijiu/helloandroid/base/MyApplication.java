package work.aijiu.helloandroid.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description application
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication INSTANCE = null;

    public static Application getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
