package work.aijiu.helloandroid.ui.activity.component.textview;

import android.app.Activity;
import android.graphics.Color;
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
        smartTable.getConfig().setShowYSequence(false);
    }
}
