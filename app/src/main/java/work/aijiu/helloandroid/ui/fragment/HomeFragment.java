package work.aijiu.helloandroid.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.ui.title_bar.SearchDetailActivity;
import work.aijiu.helloandroid.ui.title_bar.SearchTitleBarActivity;
import work.aijiu.helloandroid.utils.ComponentUtils;
import work.aijiu.helloandroid.utils.ResourceUtils;
import work.aijiu.helloandroid.widget.title_bar.SearchTitleBar;
import work.aijiu.helloandroid.widget.title_bar.adapter.SearchTitleBarAdapter;

/**
 * @author aijiu
 * @time 2020-12-12
 * @description 首页Fragment
 */
public class HomeFragment extends Fragment implements SearchTitleBar.OnSearchTitleBarListener{


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        ButterKnife.bind(this,view);
        tagetHeight = (int) (ResourceUtils.getResourcesDimension(R.dimen.search_target_height) * 0.45f);
        resetTagetHeight = (int) (ResourceUtils.getResourcesDimension(R.dimen.search_target_height) * 0.15f);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        registerListener();

        initDatas();
        initAdapter();

        return view;
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
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

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
        adapter = new SearchTitleBarAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);
    }

    private void initDatas(){
        dataList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dataList.add(new Object());
        }
    }

    @Override
    public void onClickSearch() {
        Intent intent = new Intent(getActivity(), SearchDetailActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
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

    @Override
    public void onStart() {
        super.onStart();
    }


}
