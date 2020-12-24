package work.aijiu.helloandroid.widget.viewpager.face;

import android.content.Context;
import android.view.View;

/**
 * @author aijiu
 * @time 2020-12-24
 */
public interface ViewPagerHolder<T> {

    /**
     * 创建视图
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定数据
     * @param context
     * @param postion
     * @param data
     */
    void onBind(Context context, int postion, T data);

}
