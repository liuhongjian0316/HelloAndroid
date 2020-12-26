package work.aijiu.helloandroid.ui.activity.component.textview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;
import work.aijiu.helloandroid.R;

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
public class TextViewActivity extends Activity {


    CodeView codeView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);

        codeView=(CodeView)findViewById(R.id.codeview);
        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();
        codeView.showCode("hello");



    }
}
