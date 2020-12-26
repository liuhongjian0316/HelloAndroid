package work.aijiu.helloandroid.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.widget.gridview
 * @ClassName: MyGridView
 * @Description: java类作用描述
 * @Author: aijiu
 * @CreateDate: 2020/12/26 14:32
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/26 14:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
