package work.aijiu.helloandroid.base;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

/**
 * @author aijiu
 * @time 2020-12-17
 * @description 适配器
 */
public class BaseRvViewHolder extends RecyclerView.ViewHolder {
    public BaseRvViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
