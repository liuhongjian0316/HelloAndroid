package work.aijiu.helloandroid.ui.activity.component.button;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import work.aijiu.helloandroid.base.Constant;
import work.aijiu.helloandroid.model.TableBean;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.ui.activity.component.button
 * @ClassName: ButtonActivity
 * @Description: button组件页面
 * @Author: aijiu
 * @CreateDate: 2020/12/28 14:18
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/28 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ButtonActivity extends Activity{

    @BindView(R.id.codeview)
    CodeView codeView;

    @BindView(R.id.title)
    TextView tv_title;

    @BindView(R.id.table)
    com.bin.david.form.core.SmartTable smartTable;


    @BindView(R.id.desciption_edit)
    TextView tv_desciption;

    @BindView(R.id.buttonCode)
    CodeView buttonCode;

    /**
     * 整个Activity视图的根视图
     */
    View decorView;

    /**
     * 手指按下的xy轴坐标
     */
    private float downX, downY;
    /**
     * 屏幕宽高
     */
    float screenWidth, screenHeight;

    @BindView(R.id.ex_btn1)
    Button btn1;

    private View.OnClickListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        ButterKnife.bind(this);

        initEvent();
        //初始化
        init();
    }




    /**
     * 初始化
     */
    private void init(){

        //设置文本
        tv_title.setText("一个用户界面元素，用户可以通过点击来执行一个动作，下面时xml例子");
        tv_title.setTextSize(15f);

        //设置代码主题
        codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO).fillColor();
        //显示代码
        codeView.showCode("<Button\n" +
                "     android:id=\"@+id/button_id\"\n" +
                "     android:layout_height=\"wrap_content\"\n" +
                "     android:layout_width=\"wrap_content\"\n" +
                "     android:text=\"@string/self_destruct\" />");

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


        TableData<TableBean> tableData = new TableData<TableBean>("Button xml 参数",list,param,effect);

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


        tv_desciption.setText("一些button案例");

        //设置代码主题
        buttonCode.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        //显示代码
        buttonCode.showCode("在drawable文件下创建xml，然后editText的backgroud属性引入此xml\n" +
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

    /**
     * 加载事件
     */
    private void initEvent(){
        // 获得手机屏幕的宽度和高度，单位像素
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ButtonActivity.this,"触发点击事件",Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        Log.e(Constant.LOG_TAG,event.getX()+"");
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            downX = event.getX();

        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > 0){
                decorView.setX(moveDistanceX);
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > screenWidth / 2){
                // 如果滑动的距离超过了手机屏幕的一半, 滑动处屏幕后再结束当前Activity
                continueMove(moveDistanceX);
            }else{
                // 如果滑动距离没有超过一半, 往回滑动
                rebackToLeft(moveDistanceX);
            }
        }
        return super.onTouchEvent(event);
    }
    /**
     * 从当前位置一直往右滑动到消失。
     * 这里使用了属性动画。
     */
    private void continueMove(float moveDistanceX){
        // 从当前位置移动到右侧。
        ValueAnimator anim = ValueAnimator.ofFloat(moveDistanceX, screenWidth);
        anim.setDuration(1000); // 一秒的时间结束, 为了简单这里固定为1秒
        anim.start();

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 位移
                float x = (float) (animation.getAnimatedValue());
                decorView.setX(x);
            }
        });

        // 动画结束时结束当前Activity
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

        });
    }

    /**
     * Activity被滑动到中途时，滑回去~
     */
    private void rebackToLeft(float moveDistanceX){
        ObjectAnimator.ofFloat(decorView, "X", moveDistanceX, 0).setDuration(300).start();
    }


}
