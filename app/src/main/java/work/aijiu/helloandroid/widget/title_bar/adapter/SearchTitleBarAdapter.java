package work.aijiu.helloandroid.widget.title_bar.adapter;



import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.BaseRvAdapter;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.widget.recyclerview.adapter.MyRecylerViewAdapter;
import work.aijiu.helloandroid.widget.title_bar.viewholder.SearchBannerViewHolder;
import work.aijiu.helloandroid.widget.title_bar.viewholder.SearchListViewHolder;


public class SearchTitleBarAdapter extends BaseRvAdapter{
    private final int TYPE_BANNER = 1;
    private final int TYPE_NORMAL = 2;

    private Context context = null;
    private ArrayList<Object> dataList = null;

    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }


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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onClick(position);
                }
                return true;
            }
        });
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
