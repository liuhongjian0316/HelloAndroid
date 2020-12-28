package work.aijiu.helloandroid.ui.activity.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.ui.activity.component.button.ButtonActivity;
import work.aijiu.helloandroid.ui.activity.component.edittext.EditTextActivity;
import work.aijiu.helloandroid.ui.activity.component.textview.TextViewActivity;
import work.aijiu.helloandroid.widget.gridview.MyGridAdapter;
import work.aijiu.helloandroid.widget.gridview.MyGridView;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.ui.activity.component
 * @ClassName: ComponentActivity
 * @Description: android组件Activity
 * @Author: aijiu
 * @CreateDate: 2020/12/26 14:26
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/26 14:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ComponentActivity extends AppCompatActivity {

    @BindView(R.id.gridview)
    MyGridView gridview;

    private MyGridAdapter myGridAdapter;

    private Context mContext;

    AdapterView.OnItemClickListener myItemClickListener;

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ComponentActivity.this;
        setContentView(R.layout.activity_component);
        ButterKnife.bind(this);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        String[] img_text = {"TextView", "余额宝", "手机充值", "信用卡还款", "淘宝电影", "彩票",
                "当面付", "亲密付", "机票", "当面付", "亲密付", "机票"};
        int[] imgs = {R.drawable.app_transfer, R.drawable.app_fund,
                R.drawable.app_phonecharge, R.drawable.app_creditcard,
                R.drawable.app_movie, R.drawable.app_lottery,
                R.drawable.app_movie, R.drawable.app_lottery,
                R.drawable.app_movie, R.drawable.app_lottery,
                R.drawable.app_facepay, R.drawable.app_close, R.drawable.app_plane};
        myGridAdapter = new MyGridAdapter(this);
        myGridAdapter.setImgs(imgs);
        myGridAdapter.setImg_text(img_text);
        gridview.setAdapter(myGridAdapter);

        myItemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
//                Toast.makeText(mContext,""+id,Toast.LENGTH_SHORT).show();
                switch ((int) id){
                    case 0:
                        mIntent = new Intent(ComponentActivity.this, TextViewActivity.class);
                        break;
                    case 1:
                        mIntent = new Intent(ComponentActivity.this, EditTextActivity.class);
                        break;
                    case 2:
                        mIntent = new Intent(ComponentActivity.this, ButtonActivity.class);
                        break;
                }
                if(mIntent != null){
                    startActivity(mIntent);
                }
//                Toast.makeText(mContext, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
            }
        };


        gridview.setOnItemClickListener(myItemClickListener);
    }
}
