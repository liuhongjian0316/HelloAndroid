package work.aijiu.helloandroid.widget.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.BaseViewHolder;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.widget.gridview
 * @ClassName: MyGridAdapter
 * @Description: gridview 适配器
 * @Author: aijiu
 * @CreateDate: 2020/12/26 14:29
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/26 14:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyGridAdapter extends BaseAdapter {
    private Context mContext;

    private String[] img_text;
    private int[] imgs;

    public MyGridAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void setImg_text(String[] img_text) {
        this.img_text = img_text;
    }

    public void setImgs(int[] imgs) {
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.my_grid_item, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);

        tv.setText(img_text[position]);
        return convertView;
    }

}
