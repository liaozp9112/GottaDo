package cn.com.gottado.tool.util;

import android.location.LocationManager;
import android.provider.Settings;

import cn.com.gottado.tool.config.Config;


public class DeviceUtil {
	
	// 打开第二种方法
    public static void openGPSSettings() {
      //获取GPS现在的状态（打开或是关闭状态）
      boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(Config.getContext().getContentResolver(), LocationManager.GPS_PROVIDER);
      if (gpsEnabled) {
          //关闭GPS
          Settings.Secure.setLocationProviderEnabled(Config.getContext().getContentResolver(), LocationManager.GPS_PROVIDER, false);
      } else {
          //打开GPS
          Settings.Secure.setLocationProviderEnabled(Config.getContext().getContentResolver(), LocationManager.GPS_PROVIDER, true);
      }
  }
}
