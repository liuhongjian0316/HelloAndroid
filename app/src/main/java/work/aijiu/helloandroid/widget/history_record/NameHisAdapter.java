package work.aijiu.helloandroid.widget.history_record;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import work.aijiu.helloandroid.base.BaseRvAdapter;
import work.aijiu.helloandroid.model.HistoryRecord;
import work.aijiu.helloandroid.ui.title_bar.SearchDetailActivity;

public class NameHisAdapter extends BaseRvAdapter {

    private Context context = null;
    private List<HistoryRecord> dataList = null;
    private View.OnClickListener mOnClickListener;


    public NameHisAdapter(Context context, List<HistoryRecord> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        return 0;
    }


}
