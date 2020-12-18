package work.aijiu.helloandroid.widget.recyclerview.adapter;

import android.view.View;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import work.aijiu.helloandroid.widget.recyclerview.viewholder.RvViewHolder;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.widget.recyclerview.adapter
 * @ClassName: RvAdapter
 * @Description: 自定义RevycleView 适配器
 * @Author: aijiu
 * @CreateDate: 2020/12/18 16:49
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/18 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RvAdapter extends RecyclerView.Recycler<RvViewHolder> implements View.OnClickListener{

    private List<String> myList;

    private OnItemClickListener onItemClickListener;
    
    public RvAdapter(List<String> list){
        this.myList = list;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public void onClick(View v) {
        //根据RecyclerView获得当前View的位置
        int position = rv.getChildAdapterPosition(v);
        //程序执行到此，会去执行具体实现的onItemClick()方法
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(rv,v,position,mapList.get(position));
        }
    }
}
