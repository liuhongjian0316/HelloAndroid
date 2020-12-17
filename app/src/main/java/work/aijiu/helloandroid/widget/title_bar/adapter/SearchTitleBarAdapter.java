package work.aijiu.helloandroid.widget.title_bar.adapter;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.BaseRvAdapter;
import work.aijiu.helloandroid.widget.title_bar.viewholder.SearchBannerViewHolder;
import work.aijiu.helloandroid.widget.title_bar.viewholder.SearchListViewHolder;


public class SearchTitleBarAdapter extends BaseRvAdapter {
    private final int TYPE_BANNER = 1;
    private final int TYPE_NORMAL = 2;

    private Context context = null;
    private ArrayList<Object> dataList = null;

    public SearchTitleBarAdapter(Context context, ArrayList<Object> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder holder = null;
        if(viewType == TYPE_BANNER){
            View view = LayoutInflater.from(context).inflate(R.layout.item_search_banner,
                    parent, false);
            holder = new SearchBannerViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_search_list,
                    parent, false);
            holder = new SearchListViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_BANNER;
        } else {
            return TYPE_NORMAL;
        }
    }
}
