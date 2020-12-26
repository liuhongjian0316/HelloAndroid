package work.aijiu.helloandroid.base;

import android.util.SparseArray;
import android.view.View;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.base
 * @ClassName: BaseViewHolder
 * @Description: java类作用描述
 * @Author: aijiu
 * @CreateDate: 2020/12/26 14:28
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/26 14:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseViewHolder {

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
