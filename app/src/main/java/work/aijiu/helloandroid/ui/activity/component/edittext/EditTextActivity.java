package work.aijiu.helloandroid.ui.activity.component.edittext;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;
import work.aijiu.helloandroid.R;
import work.aijiu.helloandroid.model.TableBean;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.ui.activity.component.edittext
 * @ClassName: EditTextActivity
 * @Description: EditText组件显示页
 * @Author: aijiu
 * @CreateDate: 2020/12/28 11:59
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/28 11:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EditTextActivity extends AppCompatActivity {

    @BindView(R.id.codeview)
    CodeView codeView;

    @BindView(R.id.title)
    TextView tv_title;

    @BindView(R.id.table)
    com.bin.david.form.core.SmartTable smartTable;

    @BindView(R.id.aijiu_edit)
    EditText et;

    @BindView(R.id.desciption_edit)
    TextView tv_desciption;

    @BindView(R.id.editCode)
    CodeView editCode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editview);
        ButterKnife.bind(this);
        //初始化
        init();


    }

    /**
     * 初始化
     */
    private void init() {
        //设置文本
        tv_title.setText("一个用于输入和修改文本的用户界面元素，当你定义一个编辑文本小组件时，你必须指定R.styleable.TextView_inputType属性。当你定义一个编辑文本部件时，你必须指定R.styleable.TextView_inputType属性。例如，对于纯文本输入，设置inputType为 \"text\"。");
        tv_title.setTextSize(15f);

        //设置代码主题
        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();
        //显示代码
        codeView.showCode("<EditText\n" +
                "     android:id=\"@+id/plain_text_input\"\n" +
                "     android:layout_height=\"wrap_content\"\n" +
                "     android:layout_width=\"match_parent\"\n" +
                "     android:inputType=\"text\"/>");

        //设置表格
        //普通列
        Column<String> param = new Column<>("参数", "param");
        Column<Integer> effect = new Column<>("描述", "effect");

        //设置自动合并
        effect.setAutoMerge(true);
        //设置左对齐
        effect.setTextAlign(Paint.Align.LEFT);
        List<TableBean> list = new ArrayList<>();
        list.add(new TableBean("************","继承TextView,参数可参考TextView"));


        TableData<TableBean> tableData = new TableData<TableBean>("EditText xml 参数",list,param,effect);

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


        tv_desciption.setText("一个圆角的输入框，主要代码：");

        //设置代码主题
        editCode.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        //显示代码
        editCode.showCode("在drawable文件下创建xml，然后editText的backgroud属性引入此xml\n" +
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                "    <solid android:color=\"#FFFFFF\" />\n" +
                "    <corners\n" +
                "        android:bottomRightRadius=\"20dp\"\n" +
                "        android:bottomLeftRadius=\"20dp\"\n" +
                "        android:topLeftRadius=\"20dp\"\n" +
                "        android:topRightRadius=\"20dp\">\n" +
                "    </corners>\n" +
                "</shape>");


    }
}
