package cn.com.gottado.tool.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @class:px dp  sp之间的转换工具
 * @description:
 * @author:lanhaizhong
 * @version:v4.0
 * @date:2014年9月28日 下午3:34:20
 */
public class DisplayUtil {

        /**
         * 将px值转换为dip或dp值，保证尺寸大小不变
         * 
         * @param pxValue
         * @param scale
         *            （DisplayMetrics类中属性density）
         * @return
         */ 
        public static int px2dip(Context context, float pxValue) { 
            final float scale = context.getResources().getDisplayMetrics().density; 
            return (int) (pxValue / scale + 0.5f); 
        } 
       
        /**
         * 将dip或dp值转换为px值，保证尺寸大小不变
         * 
         * @param dipValue
         * @param scale
         *            （DisplayMetrics类中属性density）
         * @return
         */ 
        public static int dip2px(Context context, float dipValue) { 
            final float scale = context.getResources().getDisplayMetrics().density; 
            return (int) (dipValue * scale + 0.5f); 
        } 
       
        /**
         * 将px值转换为sp值，保证文字大小不变
         * 
         * @param pxValue
         * @param fontScale
         *            （DisplayMetrics类中属性scaledDensity）
         * @return
         */ 
        public static int px2sp(Context context, float pxValue) { 
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
            return (int) (pxValue / fontScale + 0.5f); 
        } 
       
        /**
         * 将sp值转换为px值，保证文字大小不变
         * 
         * @param spValue
         * @param fontScale
         *            （DisplayMetrics类中属性scaledDensity）
         * @return
         */ 
        public static int sp2px(Context context, float spValue) { 
            final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
            return (int) (spValue * fontScale + 0.5f); 
        }

     /**
      * 获取屏幕原始尺寸高度，包括虚拟功能键高度
      */
     public static int getRawScreenHeight(Context context) {
         int dpi = 0;
         WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
         Display display = windowManager.getDefaultDisplay();
         DisplayMetrics displayMetrics = new DisplayMetrics();
         @SuppressWarnings("rawtypes")
         Class c;
         try {
             c = Class.forName("android.view.Display");
             @SuppressWarnings("unchecked")
             Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
             method.invoke(display, displayMetrics);
             dpi = displayMetrics.heightPixels;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return dpi;
     }

     /**
      * 获取屏幕高度
      *
      * @return 返回当前屏幕高度
      */
     public static int getScreenHeight(Context context) {
         WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
         DisplayMetrics metrics = new DisplayMetrics();
         manager.getDefaultDisplay().getMetrics(metrics);
         return metrics.heightPixels;
     }

     /**
      * 获取虚拟按键栏的高度(有虚拟按键栏时有值,没有虚拟按键栏时返回0)
      */
     public static int getBottomStatusHeight(Context context) {
         int totalHeight = getRawScreenHeight(context);

         int contentHeight = getScreenHeight(context);

         return totalHeight - contentHeight;
     }


 }

