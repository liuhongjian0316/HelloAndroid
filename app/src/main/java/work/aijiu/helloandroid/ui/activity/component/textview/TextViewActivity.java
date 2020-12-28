package work.aijiu.helloandroid.ui.activity.component.textview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bin.david.form.annotation.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.model.TableBean;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.ui.activity.component.textview
 * @ClassName: TextViewActivity
 * @Description: java类作用描述
 * @Author: aijiu
 * @CreateDate: 2020/12/26 15:42
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/26 15:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TextViewActivity extends AppCompatActivity {

    @BindView(R.id.codeview)
    CodeView codeView;

    @BindView(R.id.title)
    TextView tv;

    @BindView(R.id.table)
    com.bin.david.form.core.SmartTable smartTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
        ButterKnife.bind(this);
        //初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        tv.setText("下面的代码示例展示了一个典型的使用，其中有一个XML布局和修改文本视图内容的代码。");
        tv.setTextSize(15f);

        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();
        codeView.showCode("<LinearLayout\n" +
                "      xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "      android:layout_width=\"match_parent\"\n" +
                "      android:layout_height=\"match_parent\">\n" +
                "     <TextView\n" +
                "         android:id=\"@+id/text_view_id\"\n" +
                "         android:layout_height=\"wrap_content\"\n" +
                "         android:layout_width=\"wrap_content\"\n" +
                "         android:text=\"@string/hello\" />\n" +
                "  </LinearLayout>");



        //普通列
        Column<String> param = new Column<>("参数", "param");
        Column<Integer> effect = new Column<>("描述", "effect");

        //设置自动合并
        effect.setAutoMerge(true);
        //设置左对齐
        effect.setTextAlign(Paint.Align.LEFT);
        List<TableBean> list = new ArrayList<>();
        list.add(new TableBean("android:allowUndo","一般用在EditText中，是否应该允许对可编辑的文本进行撤销"));
        list.add(new TableBean("android:autoLink","自动找到email或url等链接，并将其转换可点击的链接，默认值为none，禁用此功能。" +
                "参数可以是all,相当于匹配所有(包括web/email/phone/map),email匹配电子邮件地址，map匹配地图地址，不推荐使用，none不匹配任何，phone匹配电话号码,web匹配网页链接,如果使用" +
                "一个多个由'|'分格" +
                "代码中setAutoLinkMask (int mask)"));

        list.add(new TableBean("android:autoSizeMaxTextSize","自动调整文字大小时使用的最大文字大小限制"));
        list.add(new TableBean("android:autoSizeMinTextSize","自动调整文字大小时使用的最小文字大小限制"));
        list.add(new TableBean("android:autoSizePresetSizes","要与设置为统一的autoSizeTextType一起使用的尺寸资源数组。"));
        list.add(new TableBean("android:autoSizeStepGranularity","如果autoSizeType被设置为uniform，则指定自动大小步长。"));
        list.add(new TableBean("android:autoSizeType","指定自动尺寸的类型"));
        list.add(new TableBean("android:autoText","如果设置，指定该TextView具有文本输入法，将自动纠正一些常见的拼写错误"));
        list.add(new TableBean("android:breakStrategy","折行策略，提供了三个参数 simple(尽可能显示更多的字符，知道这一行不能显示更多字符) " +
                "balanced(尽可能保证每个段落的每一行的宽度相同，必要时添加连词符) high_quality(会对整段文本的折行进行布局优化，必要时会自动添加连词符但对性能有影响，适合只读文本) "));
        list.add(new TableBean("android:bufferType","指定getText()返回的文本类型，editable类似StringBuilder可追加字符，spannable则可在给定的字符区域使用样式，normal"));
        list.add(new TableBean("android:capitalize","如果设置指定输入的内容大写,characters(每个英文字母都大写)，word(每个英文的首字母大写)，sentences(每一句话的首字母大写)，none(不指定)"));
        list.add(new TableBean("android:cursorVisible","设置光标可见，true or false"));
        list.add(new TableBean("android:digits","限制输入，只能输入自己设置的字符。默认值为false,设置了即为true"));
        list.add(new TableBean("android:drawableBottom","在TextView下方输出一个drawable,如果指定一个颜色，会把textView的背景色设为改颜色，" +
                "如果同时设置background属性会覆盖掉background"));
        list.add(new TableBean("android:drawableEnd","文本框结尾显示drawable资源"));
        list.add(new TableBean("android:drawableLeft","文本框左侧显示drawable资源"));
        list.add(new TableBean("android:drawablePadding","设置文本框与drawable资源的距离，" +
                "要与drawableLeft、drawableRight、drawableTop、drawableBottom，可以设置复数，注单独使用没效果"));
        list.add(new TableBean("android:drawableRight","文本框右侧显示drawable资源"));
        list.add(new TableBean("android:drawableStart","绘制在文本框的开头的drawable"));
        list.add(new TableBean("android:drawableTint","给文本进行着色，不用弄很多套颜色的资源"));
        list.add(new TableBean("android:drawableTintMode","官网查看"));
        list.add(new TableBean("android:drawableTop","文本框上部显示drawable资源"));
        list.add(new TableBean("android:editable","如果设置可以输入"));
        list.add(new TableBean("android:editorExtras","设置文本输入的额外数据"));
        list.add(new TableBean("android:elegantTextHeight","增加顶部和底部边界，为更高的文本提供空间"));
        list.add(new TableBean("android:ellipsize","如果设置了，则会使比视图宽度长的文字变成椭圆型，而不是从中间断开，你通常还想设置scrollHorizontally或singleLine，这样整个文本也会被限制在单行，" +
                "而不是仍然允许段成多行"));
        list.add(new TableBean("android:ems","使得TextView的宽度正好是这么多ems"));
        list.add(new TableBean("android:enabled","组件是否被启动，true or false"));
        list.add(new TableBean("android:fallbackLineSpacing","是否尊重显示文本时使用的后备字体的上升和下降。当为真时，最终被使用的后备字体可以增加其使用的行的上升和下降。"));
        list.add(new TableBean("android:fontFamily","设置使用的字体"));
        list.add(new TableBean("android:fontFeatureSettings","字体特征设置"));
        list.add(new TableBean("android:fontVariationSettings","字体变化设置"));
        list.add(new TableBean("android:freezesText","如果设置，文本视图将在其冻结的冰柱中包含当前完整的文本，以及元数据（如当前光标位置）。默认情况下，这是禁用的；当文本视图的内容不存储在一个持久性的地方（如内容提供者）时，它可能是有用的。对于android.widget.EditText来说，无论属性的值是多少，它总是被启用。"));
        list.add(new TableBean("android:gravity","内部的对齐方式"));
        list.add(new TableBean("android:height","设置高度"));
        list.add(new TableBean("android:hint","当文本为空时要显示的提示文本。"));
        list.add(new TableBean("android:hyphenationFrequency","自动连字的频率"));
        list.add(new TableBean("android:imeActionId","为EditorInfo.actionId提供一个值，当输入方法连接到文本视图时使用。"));
        list.add(new TableBean("android:imeActionLabel","为EditorInfo.actionLabel提供一个值，当输入方法连接到文本视图时使用。"));
        list.add(new TableBean("android:imeOptions","您可以在与编辑器相关联的IME中启用其他功能，以改善与应用程序的集成。这里的常量对应于android.view.inputmethod.EditorInfo#imeOptions所定义的常量。"));
        list.add(new TableBean("android:includeFontPadding","为上升和下降留出足够的空间，而不是严格使用字体上升和下降。"));
        list.add(new TableBean("android:inputMethod","指定这个TextView应该使用指定的输入方法"));
        list.add(new TableBean("android:inputType","被放置在文本字段中的数据类型，用于帮助输入法决定如何让用户输入文本。这里的常量与android.text.InputType定义的常量相对应。一般来说，你可以选择一个单一的值，不过有些值可以按照指示组合在一起。将该属性设置为除none以外的任何值，也意味着文本是可编辑的。"));

        list.add(new TableBean("android:justificationMode","对齐模式"));
        list.add(new TableBean("android:letterSpacing","文字间距"));
        list.add(new TableBean("android:lineSpacingMultiplier","文本行间的额外间距，作为倍数。该值不适用最后一行文字。"));
        list.add(new TableBean("android:lines","设置文本的行数"));
        list.add(new TableBean("android:linksClickable","如果设置为false，即使自动链接导致链接被找到，也不会将移动方式设置为链接移动方式。"));
        list.add(new TableBean("android:marqueeRepeatLimit","重复播放marquee动画的次数。只有在TextView启用了marquee时才适用。"));
        list.add(new TableBean("android:maxEms","设置TextView最大ems宽。"));
        list.add(new TableBean("android:maxHeight","设置最高高度"));
        list.add(new TableBean("android:maxLength","限制文本长度"));
        list.add(new TableBean("android:maxLines","使TextView最多只能有这么多行。当用于可编辑的文本时，inputType属性的值必须与textMultiLine标志相结合，" +
                "才能应用maxLines属性。"));
        list.add(new TableBean("android:maxWidth","设置最宽宽度"));
        list.add(new TableBean("android:minEms","设置至少有多少ems"));
        list.add(new TableBean("android:minHeight","设置最小的高度"));
        list.add(new TableBean("android:minLines","使TextView至少有这么多行高。当使用在一个可编辑的文本上时，inputType属性的值必须与textMultiLine标志结合起来才能应用minLines属性。"));
        list.add(new TableBean("android:minWidth","设置最小宽度"));
        list.add(new TableBean("android:numeric","如果设置，指定该TextView具有数字输入法。默认值为false。{@deprecated 使用inputType代替}。"));
        list.add(new TableBean("android:password","是否将字段中的字符以密码点的形式显示，而不是自己。{@deprecated 使用输入类型代替}。"));
        list.add(new TableBean("android:phoneNumber","如果设置，指定该TextView有一个电话号码输入法。默认为false。{@deprecated 使用inputType代替}。"));
        list.add(new TableBean("android:privateImeOptions","一个附加的内容类型描述，要提供给文本视图所连接的输入法，它是输入法实现的私有内容。这只是在连接输入法时填写EditorInfo.privateImeOptions字段。"));
        list.add(new TableBean("android:scrollHorizontally","是否允许文本比视图宽（因此可以水平滚动）"));
        list.add(new TableBean("android:selectAllOnFocus","如果文本是可选择的，则在视图聚焦时将其全部选择"));
        list.add(new TableBean("android:shadowColor","在文字下方放置一个模糊的文字阴影，用指定的颜色绘制。产生的文字阴影与View上负责实时阴影、仰角和translationZ的属性不交互。"));
        list.add(new TableBean("android:shadowDx","文字阴影的水平偏移"));
        list.add(new TableBean("android:shadowDy","文字阴影的垂直偏移"));
        list.add(new TableBean("android:shadowRadius","文字阴影的模糊半径"));
        list.add(new TableBean("android:singleLine","将文本限制为单行水平滚动，而不是让它缠绕在多行上，并且当你按下回车键时，" +
                "推进焦点而不是插入一个新行。对于不可编辑的文本，默认值为false(多行环绕文本模式)，但如果你为inputType指定了任何值，" +
                "默认值为true(单行输入域模式)。{@deprecated 此属性已被废弃。使用maxLines来代替改变*静态文本的布局" +
                "，并使用* inputType属性中的textMultiLine标志来代替可编辑的文本视图（如果同时提供了singleLine和inputType*，" +
                "inputType标志将覆盖singleLine的值）。}"));
        list.add(new TableBean("android:text","设置显示的文本"));
        list.add(new TableBean("android:textAppearance","基础文本颜色、字体、大小和样式。"));
        list.add(new TableBean("android:textColor","设置字体颜色。"));
        list.add(new TableBean("android:textColorHighlight","文本选择高亮的颜色"));
        list.add(new TableBean("android:textColorHint","提示文字的颜色"));
        list.add(new TableBean("android:textColorLink","链接文字的颜色"));
        list.add(new TableBean("android:textCursorDrawable","参考将在插入光标下绘制的可绘制文件"));
        list.add(new TableBean("android:textScaleX","设置文本的水平缩放系数"));
        list.add(new TableBean("android:textSelectHandle","参考一个可绘制的文件，该文件将用于显示文本选择锚，以便在文本中定位光标。"));
        list.add(new TableBean("android:textSelectHandleLeft","参考将用于在选择区域左侧显示文本选择锚的可绘制文件。"));
        list.add(new TableBean("android:textSelectHandleRight","参考一个可绘制的对象，该对象将用于在选择区域的右侧显示文本选择锚。"));
        list.add(new TableBean("android:textSize","设置文字的大小"));
        list.add(new TableBean("android:textStyle","设置文字的样式"));
        list.add(new TableBean("android:typeface","设置文字的字体"));
        list.add(new TableBean("android:width","文字的宽度"));









        TableData<TableBean> tableData = new TableData<TableBean>("TextView xml 参数",list,param,effect);

        smartTable.setTableData(tableData);
        smartTable.getConfig().setContentStyle(new FontStyle(30, Color.BLACK));
        //固定左侧
        smartTable.getConfig().setFixedYSequence(true);
        //固定顶部
        smartTable.getConfig().setFixedXSequence(true);
        //隐藏顶部
        smartTable.getConfig().setShowXSequence(false);
        //隐藏左侧
        smartTable.getConfig().setShowYSequence(true);
    }
}
