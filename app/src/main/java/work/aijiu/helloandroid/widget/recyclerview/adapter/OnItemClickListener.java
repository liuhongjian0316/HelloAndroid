package work.aijiu.helloandroid.widget.recyclerview.adapter;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.widget.recyclerview.adapter
 * @ClassName: OnItemClickListener
 * @Description: 自定义条目点击事件
 * @Author: aijiu
 * @CreateDate: 2020/12/18 16:48
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/18 16:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface OnItemClickListener{
    void onItemClick(RecyclerView parent, View view, int position, List<String> data);
}
