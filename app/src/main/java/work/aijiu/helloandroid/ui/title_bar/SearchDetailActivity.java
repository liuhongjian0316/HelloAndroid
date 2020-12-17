package work.aijiu.helloandroid.ui.title_bar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.dao.history_record.HistoryDao;
import work.aijiu.helloandroid.model.HistoryRecord;
import work.aijiu.helloandroid.widget.history_record.NameHisAdapter;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 搜索详情界面
 */
public class SearchDetailActivity extends AppCompatActivity implements View.OnClickListener {

    /**********************************普通变量*********************************************/
    private HistoryDao historyDao;

    List<HistoryRecord> historyRecords = new ArrayList<>();

    private NameHisAdapter adapter;

    private Handler handler;

    private int HAS_NEW_MESSAGE_FLAG = 0xff;

    /**********************************组件初始化*********************************************/
    @BindView(R.id.search_cancle_layout)
    RelativeLayout search_layout;

    @BindView(R.id.btn_clear_history)
    Button btn_clear_history;

    private RecyclerView rvHistory;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);
        //添加点击监听事件
        search_layout.setOnClickListener(this);
        btn_clear_history.setOnClickListener(this);

        init();
        initHistoryData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            //隐藏标题栏
            actionBar.hide();
        }
    }


    /**
     * 初始化
     */
    public void init(){
        historyDao = HistoryDao.getInstance(this);

        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            public void handleMessage(Message msg){
                switch (msg.what){
                    case HAS_NEW_MESSAGE_FLAG:
                        if(historyRecords.size() == 0){

                        }else{
                            if (adapter == null) { //第一次加载
                                LinearLayoutManager layoutmanager = new LinearLayoutManager(SearchDetailActivity.this);
                                //设置RecyclerView 布局
                                rvHistory = new RecyclerView(SearchDetailActivity.this);
                                rvHistory.setLayoutManager(layoutmanager);
                                adapter = new NameHisAdapter(SearchDetailActivity.this, historyRecords);
                                rvHistory.setAdapter(adapter);
                                adapter.setOnDelClickListener(new NameHisAdapter.OnDelClickListener() {
                                    @Override
                                    public void onDelClick(View view, int position, int count) {
                                        // 删除当前历史记录     historyDao.delHistory(historyDao.getIDByName(beans.get(position).getName().toString().trim()));
                                        historyRecords.remove(position);
                                        adapter.notifyDataSetChanged();

                                    }
                                });
                                adapter.setOnItemClickListener(new NameHisAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        // 获得对应名称
                                        String name= historyRecords.get(position).getName();

                                        // 添加历史记录
                                        historyDao.addHistory(historyRecords.get(position).getName());


                                    }
                                });
                            } else {
                                //追加数据
                                adapter.notifyDataSetChanged();// 刷新listView 否则仍会从头开始 显示
                            }
                            llData.setVisibility(View.VISIBLE);
                            vNodata.setVisibility(View.GONE);
                        }
                        break;
                }
            }
        };
    }

    /**
     * 初始化历史记录
     */
    public void initHistoryData(){
        new Thread(){
            @Override
            public void run() {
                if(historyRecords == null){
                    historyRecords =historyDao.getAllHistoryDate();
                }else {
                    historyRecords.clear();
                    historyRecords.addAll(historyDao.getAllHistoryDate());
                }

                Message msg = Message.obtain();
                msg.what = HAS_NEW_MESSAGE_FLAG;
                handler.sendMessage(msg);
            }
        }.start();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_cancle_layout:
                onBackPressed();
                break;
            case R.id.btn_clear_history:
                //清除全部记录
                historyDao.delAllHistory(historyRecords);
                break;
        }
    }



}
