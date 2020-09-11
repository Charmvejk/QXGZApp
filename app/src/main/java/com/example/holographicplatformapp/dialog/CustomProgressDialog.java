package com.example.holographicplatformapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.example.holographicplatformapp.R;

/**
 * /**
 * Created by wsy on 2020/9/11 16:46.
 * ┏┛ ┻━━━━━┛ ┻┓
 * ┃　　　　　　 ┃
 * ┃　　　━　　　┃
 * ┃　┳┛　  ┗┳　┃
 * ┃　　　　　　 ┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　 ┃
 * ┗━┓　　　┏━━━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━━━━━━━┓
 * ┃　　　　　　　    ┣┓
 * ┃　　　　         ┏┛
 * ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * ┃ ┫ ┫   ┃ ┫ ┫
 * ┗━┻━┛   ┗━┻━┛
 */

public class CustomProgressDialog extends Dialog {

    public TextView messageTv;

    public CustomProgressDialog(Context context) {
        this(context, R.style.MyDialogStyle, "");
    }

    public CustomProgressDialog(Context context, String string) {
        this(context, R.style.MyDialogStyle, string);
    }

    public CustomProgressDialog(Context context, int theme, String string) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.custom_progress_dialog);
        messageTv = (TextView) findViewById(R.id.tv_message);
        messageTv.setText("waiting...");
        getWindow().getAttributes().gravity = Gravity.CENTER;
        getWindow().getAttributes().dimAmount = 0f;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
