package work.aijiu.helloandroid.widget.viewpager;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.widget.viewpager.face.ViewPagerHolder;
import work.aijiu.helloandroid.widget.viewpager.face.ViewPagerHolderCreator;

public class CommonViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mDatas;
    private ViewPagerHolderCreator mCreator;

    public CommonViewPagerAdapter(List<T> datas, ViewPagerHolderCreator creator) {
        mDatas = datas;
        mCreator = creator;
    }

    @Override
    public int getCount() {
        return this.mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(position,null,container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 获取viewPager 页面展示View
     * @param position
     * @param view
     * @param container
     * @return
     */
    private View getView(int position,View view ,ViewGroup container){

        ViewPagerHolder holder =null;
        if(view == null){
            //创建Holder
            holder = mCreator.createViewHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.common_view_pager_item_tag,holder);
        }else{
            holder = (ViewPagerHolder) view.getTag(R.id.common_view_pager_item_tag);
        }
        if(holder!=null && mDatas!=null && mDatas.size()>0){
            // 数据绑定
            holder.onBind(container.getContext(),position,mDatas.get(position));
        }

        return view;
    }
}
