package cn.com.gottado.tool.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 *  系统助手类
 */
public class SystemTools {
    public static Context CONTEXT = null;

    public static void initSystemTools(Context context) {
        CONTEXT = context;
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkConnected() {
        if (CONTEXT != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) CONTEXT
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断wifi是否可用
     */
    public static boolean isWifiConnected() {
        ConnectivityManager mgrConn = (ConnectivityManager) CONTEXT
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) CONTEXT
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断流量是否可用
     */
    public static boolean isMobileConnected() {
        ConnectivityManager cm = (ConnectivityManager) CONTEXT
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断GPS是否开启
     *
     * @return true 表示开启
     */
    public static final boolean isGpsOPen() {
        LocationManager locationManager = (LocationManager) CONTEXT
                .getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        // boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps;
    }


    /***
     * 发送短信
     * @param phoneNumber 手机号码
     * @param message 短信内容
     */
    public  static void sendSMS(String phoneNumber,String message){
        SmsManager.getDefault().sendTextMessage(phoneNumber,null,message,null,null);
    }
}
