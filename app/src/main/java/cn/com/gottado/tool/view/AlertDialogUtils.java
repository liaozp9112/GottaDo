package cn.com.gottado.tool.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AlertDialogUtils {
    private Context mContext;
    private int layoutId;
    private DialogClickListener dialogClickLintener = null;
    private View view;
    //用于判断显示模式
    public static final int
            SHOW_MOD_YES = 0x00000001,
            SHOW_MOD_ALL = 0x00000003;
    private  int showMode =SHOW_MOD_YES;

    /***自定义View***/
    public AlertDialogUtils(Context context, int layoutId){
        this.mContext = context;
        this.layoutId = layoutId;
        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setTitle("温馨提示");
        LayoutInflater inflate = LayoutInflater.from(context);
        view = inflate.inflate(layoutId, null);
         build.setView(view);
         build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
                if(dialogClickLintener != null){
                    dialogClickLintener.clickYes();
                }
            }
        });
        if(showMode==SHOW_MOD_ALL){
            build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
              @Override
                 public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
                     if(dialogClickLintener != null){
                      dialogClickLintener.clickNo();
                 }
              }
            });
        }
        build.create().show();
    }

    /****自定义文本**/
    public AlertDialogUtils(Context context,String text){
        this.mContext = context;
        AlertDialog.Builder build = new AlertDialog.Builder(context);
        build.setTitle("温馨提示");
        build.setMessage(text);
        build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(dialogClickLintener != null){
                    dialogClickLintener.clickYes();
                }
            }
        });
        if(showMode==SHOW_MOD_ALL){
             build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                         if(dialogClickLintener != null){
                            dialogClickLintener.clickNo();
                         }
                     }
              });
        }
        build.create().show();
    }

    public View getview(int viewId){
        return view.findViewById(viewId);
    }
    public View getContentView(){
        return view;
    }
    //外类调用这两个以确定显示模式
    public void setDialogButtonShowMode(int showMode){
        this.showMode = showMode;
    }

    /**
     * 设置TextView的值
     * @param viewId
     * @param text
     * @return
     */
//    public AlertDialogUtils setText(int viewId[], String text[], View.OnClickListener[] clickListener){
//
//        for (int i=0;i<viewId.length;i++){
//            TextView tv= (TextView)getview(viewId[i]);
//            tv.setText(text[i]);
//            tv.setOnClickListener(clickListener[i]);
//        }
//
//
////        Drawable drawable = context.getResources().getDrawable(R.drawable.travel_hotel_search_star);
////        drawable.setBounds(0, 0, 100,100);
////        tv.setCompoundDrawables(null,null,drawable,null);
//        return this;
//    }



    public interface DialogClickListener{
        void clickYes();
        void clickNo();
    }
    public void setOnClickListener(DialogClickListener dialogClickListener){
        this.dialogClickLintener = dialogClickListener;
    }
}
