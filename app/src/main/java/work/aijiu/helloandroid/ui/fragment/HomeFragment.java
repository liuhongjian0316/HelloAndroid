package work.aijiu.helloandroid.ui.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.ui.activity.component.ComponentActivity;
import work.aijiu.helloandroid.ui.title_bar.SearchDetailActivity;
import work.aijiu.helloandroid.ui.title_bar.SearchTitleBarActivity;
import work.aijiu.helloandroid.utils.ComponentUtils;
import work.aijiu.helloandroid.utils.ResourceUtils;
import work.aijiu.helloandroid.widget.title_bar.SearchTitleBar;
import work.aijiu.helloandroid.widget.title_bar.adapter.SearchTitleBarAdapter;
import work.aijiu.helloandroid.widget.viewpager.adapter.MyViewPager;
import work.aijiu.helloandroid.widget.viewpager.adapter.ShufflingAdapter;

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

    @BindView(R.id.shuffling)
    MyViewPager mShuffling;

    ListView myListView;

    private SearchTitleBarAdapter adapter = null;
    private ArrayList<Object> dataList = null;

    private int tagetHeight = 0;
    private int resetTagetHeight = 0;
    private View firstVisibleView = null;
    private int searchBarState = 0;// 0初始状态  1展开状态

    private ShufflingAdapter mShufflingAdapter;

    private static List<Integer> sList = new ArrayList<>();

    private boolean mIsTouch = false;

    static {
        sList.add(R.drawable.p1);
        sList.add(R.drawable.p2);
        sList.add(R.drawable.p3);
        sList.add(R.drawable.p4);
        sList.add(R.drawable.p5);
        sList.add(R.drawable.p6);
        sList.add(R.drawable.p7);
    }

    private Handler mHandler;
    private LinearLayout mLinearLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        ButterKnife.bind(this,view);


        registerListener();

        initDatas();
        initAdapter(view);
        initListView(view);

        mHandler = new Handler();
        return view;
    }

    /**
     * 加载ListView
     */
    private void initListView(View view) {
        myListView = (ListView)view.findViewById(R.id.list_function);
        String[] names = { "组件", "图表", "数据库", "视频图片", "测试", "测试","测试","测试","测试" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        myListView.setAdapter(adapter); myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), ComponentActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(getActivity(),"第"+position+"个item", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"第"+position+"个item", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"第"+position+"个item", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity(),"第"+position+"个item", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(),"第"+position+"个item", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    private void initAdapter(View view){
        adapter = new SearchTitleBarAdapter(getActivity(), dataList);
        adapter.setOnItemClickListener(position -> {
            Toast.makeText(getActivity(), "click " + position, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnItemLongClickListener(position -> Toast.makeText(getActivity(), "long click " + position, Toast.LENGTH_SHORT).show());
        recyclerView.setAdapter(adapter);

        tagetHeight = (int) (ResourceUtils.getResourcesDimension(R.dimen.search_target_height) * 0.45f);
        resetTagetHeight = (int) (ResourceUtils.getResourcesDimension(R.dimen.search_target_height) * 0.15f);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //设置监听
        mShuffling.setOnViewPagerTouchListen(new MyViewPager.OnViewPagerTouchListen() {
            @Override
            public void onViewPagerTouch(boolean isTouch) {
                mIsTouch = isTouch;

            }
        });

        //设置监听器
        mShufflingAdapter = new ShufflingAdapter();
        mShufflingAdapter.setData(sList);
        mShufflingAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getVerticalScrollbarPosition()){
                    case 0:
                        Uri baidu_uri = Uri.parse("https://www.baidu.com/");
                        Intent intent = new Intent(Intent.ACTION_VIEW, baidu_uri);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }
            }
        });
        mShuffling.setAdapter(mShufflingAdapter);
        mShuffling.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = 0;
                if (mShufflingAdapter.getDataRealSize() != 0) {
                    realPosition = position % mShufflingAdapter.getDataRealSize();
                }
                selectedPoint(realPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLinearLayout = (LinearLayout)view.findViewById(R.id.points);
        initPoints();
        mShuffling.setCurrentItem(mShufflingAdapter.getDataRealSize() * 100 - 1, false);

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


    private void initPoints() {
        for (int i = 0; i < mShufflingAdapter.getDataRealSize(); i++) {
            View point = new View(getActivity());
            point.setBackgroundResource(R.drawable.shape_point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.leftMargin = 20;
            point.setLayoutParams(params);
            mLinearLayout.addView(point);
        }
    }

    private void selectedPoint(int realPosition) {

        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View point = mLinearLayout.getChildAt(i);
            if (i == realPosition) {
                point.setBackgroundResource(R.drawable.shape_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shape_point_normal);
            }
        }


    }

    @Override
    public void onStart() {
        super.onStart();
    }


}
