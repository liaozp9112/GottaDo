package cn.com.gottado.tool.base;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import org.xutils.x;

import cn.com.gottado.service.DataSyncService;
import cn.com.gottado.tool.config.Config;
import cn.com.gottado.tool.util.DeviceInfo;

/**
 * Created by Administrator on 2017/6/8.
 */

public class BaseApp extends Application {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        Config.init(this);
        DeviceInfo.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志，开启debug会影响性能
        startService(new Intent(this, DataSyncService.class));
    }


    public static void verifyStoragePermissions(BaseActivity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
