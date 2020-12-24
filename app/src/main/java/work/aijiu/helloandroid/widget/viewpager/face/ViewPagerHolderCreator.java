package work.aijiu.helloandroid.widget.viewpager.face;

/**
 * @author aijiu
 * @time 2020-12-24
 */
public interface ViewPagerHolderCreator<VH extends ViewPagerHolder>{
    public VH createViewHolder();
}
