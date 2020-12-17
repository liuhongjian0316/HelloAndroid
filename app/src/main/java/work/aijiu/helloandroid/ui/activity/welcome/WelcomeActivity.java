package work.aijiu.helloandroid.ui.activity.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.ui.activity.main.MainActivity;
import work.aijiu.helloandroid.utils.ComponentUtils;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 欢迎界面
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        ComponentUtils.hidenTitleBar(WelcomeActivity.this);
        setContentView(R.layout.activity_welcome);
        startMainActivity();
    }

    /**
     * 延时3秒进入主界面
     */
    private void startMainActivity() {
        TimerTask delayTask = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                WelcomeActivity.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(delayTask,3000);
    }
}
