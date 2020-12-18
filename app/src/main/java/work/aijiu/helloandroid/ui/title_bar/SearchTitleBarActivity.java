package work.aijiu.helloandroid.ui.title_bar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.utils.ResourceUtils;
import work.aijiu.helloandroid.widget.title_bar.SearchTitleBar;
import work.aijiu.helloandroid.widget.title_bar.adapter.SearchTitleBarAdapter;

public class SearchTitleBarActivity extends AppCompatActivity
    implements SearchTitleBar.OnSearchTitleBarListener {

    @BindView(R.id.recyler_view)
    RecyclerView recyclerView;

    @BindView(R.id.search_title_bar)
    SearchTitleBar titleBar;

    private SearchTitleBarAdapter adapter = null;
    private ArrayList<Object> dataList = null;

    private int tagetHeight = 0;
    private int resetTagetHeight = 0;
    private View firstVisibleView = null;
    private int searchBarState = 0;// 0初始状态  1展开状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_title_bar);
        ButterKnife.bind(this);

        tagetHeight = (int) (ResourceUtils.getResourcesDimension(R.dimen.search_target_height) * 0.45f);
        resetTagetHeight = (int) (ResourceUtils.getResourcesDimension(R.dimen.search_target_height) * 0.15f);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        registerListener();

        initDatas();
        initAdapter();
    }

    private void registerListener(){
        titleBar.setOnSearchTitleBarListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setupTitleBar();
            }
        });
    }

    public void setupTitleBar() {
        if (recyclerView == null || titleBar == null) {
            return;
        }

        if (firstVisibleView == null) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            firstVisibleView = layoutManager.getChildAt(0);
        }

        if(firstVisibleView == null){
            return;
        }

        int top = firstVisibleView.getTop();

        if (top > (-resetTagetHeight)
                && searchBarState == 1) {
            searchBarState = 0;
            titleBar.performAnimate(false);
        } else if (top <= (-resetTagetHeight)
                && top >= (-tagetHeight)) {
            titleBar.hideDivide();
        } else if (top < (-tagetHeight)
                && searchBarState == 0) {
            searchBarState = 1;
            titleBar.performAnimate(true);
        }

        if (Math.abs(top) <= tagetHeight) {
            float precent = (Math.abs(top) * 1.0f / tagetHeight);
            int alpha = (int) (precent * 255);
            titleBar.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
        } else {
            titleBar.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }

    private void initAdapter(){
        adapter = new SearchTitleBarAdapter(this, dataList);
        Log.e(Constant.LOG_TAG,(adapter == null)+"");
        adapter.setOnItemClickListener(position -> {
            Toast.makeText(SearchTitleBarActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnItemLongClickListener(position -> Toast.makeText(SearchTitleBarActivity.this, "long click " + position, Toast.LENGTH_SHORT).show());
        recyclerView.setAdapter(adapter);
    }

    private void initDatas(){
        dataList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            dataList.add(new Object());
        }
    }

    @Override
    public void onClickSearch() {
        Intent intent = new Intent(this, SearchDetailActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair.create(titleBar.getSearchLayout()
                            , ResourceUtils.getResourcesString(R.string.trans_anim_search_layout)),
                    Pair.create(titleBar.getSearchTv()
                            , ResourceUtils.getResourcesString(R.string.trans_anim_search_tv)),
                    Pair.create(titleBar.getSearchIv()
                            , ResourceUtils.getResourcesString(R.string.trans_anim_search_iv)),
                    Pair.create(titleBar.getSearchEt()
                            , ResourceUtils.getResourcesString(R.string.trans_anim_search_edit))).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
