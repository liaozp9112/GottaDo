package cn.com.gottado.tool.config;


import android.content.Context;
import android.os.Environment;

import cn.com.gottado.tool.util.ShaPreTools;

public class Config {
	 private static Context mContext;

	    public static void init(Context context){
	        mContext=context;
	       if(mContext!=null){
	    	   ShaPreTools.init(mContext);
	       }
	    }

	//保存到SD卡的路径
	public static final String APP_PATH= Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath()+"/gottado" : "/mnt/sdcard/gottado";

	public static Context getContext() {
	        return mContext;
	    }
}
