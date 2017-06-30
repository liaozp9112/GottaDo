package cn.com.gottado.tool.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Administrator on 2017/6/7.
 */

public class SnackbarUtil {

    public static void showNormal(View view, String text){
        Snackbar.make(view,text,Snackbar.LENGTH_LONG)
                .show();
    }


}
