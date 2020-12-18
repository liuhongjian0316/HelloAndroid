package work.aijiu.helloandroid.ui.title_bar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.dao.history_record.HistoryDao;
import work.aijiu.helloandroid.model.HistoryRecord;


import io.reactivex.ObservableOnSubscribe;
import work.aijiu.helloandroid.widget.dialog.ProgressDialog;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 搜索详情界面
 */
public class SearchDetailActivity extends AppCompatActivity implements View.OnClickListener {

    /**********************************普通变量*********************************************/
    private HistoryDao historyDao;

    List<HistoryRecord> historyRecords = new ArrayList<>();

    private TagAdapter mRecordsAdapter;
    //自动提示输入框 数组适配器
    private ArrayAdapter<String> autoTextAdapter;
    //自动提示数据
    private List<String> autoData;
    //默认加载历史记录数
    private final int DEFAULT_RECORD_NUMBER = 10;

    /**********************************组件初始化*********************************************/
    @BindView(R.id.search_cancle_layout)
    RelativeLayout search_layout;

    @BindView(R.id.btn_clear_history)
    Button btn_clear_history;

    @BindView(R.id.history_flowlayout)
    TagFlowLayout history_flowlayout;

    @BindView(R.id.search_edit)
    AutoCompleteTextView searchEdit;
    /**
     * 加载框
     */
    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);
        //添加点击监听事件
        search_layout.setOnClickListener(this);
        btn_clear_history.setOnClickListener(this);

        //初始化
        initAutoData();
        init();
        initData();
        initEvent();

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
        //初始化数据库
        historyDao = HistoryDao.getInstance(this);

        //初始化数据
        historyRecords = historyDao.getCurrentHistory(DEFAULT_RECORD_NUMBER);

        autoTextAdapter = new ArrayAdapter<String>(SearchDetailActivity.this,
                android.R.layout.simple_spinner_item, autoData);

        searchEdit.setAdapter(autoTextAdapter);

        //加载框
        dialog = ProgressDialog.createDialog(SearchDetailActivity.this);
        dialog.setMessage("数据加载中...");


    }

    /**
     * 初始化历史数据
     */
    public void initData(){
        //初始化适配器
        mRecordsAdapter = new TagAdapter<HistoryRecord>(historyRecords) {

            @Override
            public View getView(FlowLayout parent, int position, HistoryRecord historyRecord) {
                TextView tv = (TextView) LayoutInflater.from(SearchDetailActivity.this).inflate(R.layout.tv_history,
                        history_flowlayout, false);
                //为标签设置对应的内容
                tv.setText(historyRecord.getName());
                return tv;
            }
        };
        history_flowlayout.setAdapter(mRecordsAdapter);
    }

    /**
     * 初始化事件
     */
    public void initEvent(){
        //回车事件监听
        searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    Log.e(Constant.LOG_TAG,"回车执行");
                    showProgressDialog();

                    //将搜索的内容放到数据库中
                    historyDao.addHistory(searchEdit.getText().toString());

                    //延时两秒（模拟真实环境）
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                if(dialog != null){
                                    dialog.dismiss();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    return true;
                }
                return false;
            }
        });

        //条目点击监听
        searchEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showProgressDialog();
                //将搜索的内容放到数据库中
                historyDao.addHistory(autoTextAdapter.getItem(position).toString());
                //延时两秒（模拟真实环境）
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            if(dialog != null){
                                dialog.dismiss();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        /**
         * 输入框的监听
         */
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(Constant.LOG_TAG,"输入框改变监听");
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                Log.e(Constant.LOG_TAG,"输入框改变之前监听");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e(Constant.LOG_TAG,"输入框改变之后监听");
            }
        });

    }

    /**
     * 初始化自动文本数据
     */
    public void initAutoData(){
        autoData = new ArrayList<>();
        autoData.add("你好");
        autoData.add("你好啊");
        autoData.add("你好亲爱的");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你好程序员");
        autoData.add("你不");
        autoData.add("你不豪");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_cancle_layout:
                onBackPressed();
                break;
            case R.id.btn_clear_history:
                //清除全部记录
                historyDao.delAllHistory(historyRecords);
                init();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 显示进度框
     */
    public void showProgressDialog(){
        dialog.show();
    }

}
