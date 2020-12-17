package work.aijiu.helloandroid.ui.title_bar;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 搜索详情界面
 */
public class SearchDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.search_cancle_layout)
    RelativeLayout search_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            //隐藏标题栏
            actionBar.hide();
        }

        search_layout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.search_cancle_layout){
            onBackPressed();
        }
    }
}
