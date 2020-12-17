package work.aijiu.helloandroid.ui.activity.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.utils.ComponentUtils;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 程序主界面
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 底部导航栏
     */
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            //隐藏标题栏
            actionBar.hide();
        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        navController.setGraph(R.navigation.navigation);
        NavOptions navOptions = new NavOptions.Builder()
                .setEnterAnim(R.animator.slide_left_in) //进入动画
                .setExitAnim(R.animator.slide_left_out)    //退出动画
                .setPopEnterAnim(R.animator.slide_right_in)    //弹出进入动画
                .setPopExitAnim(R.animator.slide_right_out)  //弹出退出动画
                .build();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
}
