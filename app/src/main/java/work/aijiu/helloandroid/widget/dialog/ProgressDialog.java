package work.aijiu.helloandroid.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import work.aijiu.helloandroid.R;

/**
 * @ProjectName: HelloAndroid
 * @Package: work.aijiu.helloandroid.widget.dialog
 * @ClassName: ProgressDialog
 * @Description: 自定义加载框
 * @Author: aijiu
 * @CreateDate: 2020/12/18 14:31
 * @UpdateUser: aijiu
 * @UpdateDate: 2020/12/18 14:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ProgressDialog extends Dialog {

    private Context context;
    private static ProgressDialog progressDialog;
    private OnKeyListener onKeyListener;


    public ProgressDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        //禁止back键 取消
        this.setCancelable(false);
    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    //创建dialog的样式
    public static ProgressDialog createDialog(Context context){

        progressDialog = new ProgressDialog(context, R.style.ProgressDialogStyle);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setContentView(R.layout.progressdialog);
        progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return progressDialog ;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (progressDialog == null) {
            return;
        }
        //添加控件  执行帧动画
        ImageView imageView = (ImageView) progressDialog.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    public ProgressDialog setTitle(String title) {
        return progressDialog;
    }
    public ProgressDialog setMessage(String strMessage){
        TextView tvMessage = (TextView)progressDialog.findViewById(R.id.id_tv_loadingmsg);

        if (tvMessage != null){
            tvMessage.setText(strMessage);
        }
        return progressDialog;
    }




}
