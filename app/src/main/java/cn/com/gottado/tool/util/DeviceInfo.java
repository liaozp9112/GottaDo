package cn.com.gottado.tool.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by liaozp on 16/5/15.
 */
public class DeviceInfo {

    private static String TAG="DeviceInfo";

    private static Context mContext;

    private static TelephonyManager tm;

    private static WifiInfo wifiInfo ;

    public static void init(Context context){
        mContext=context;
        tm= (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
        WifiManager wifi = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        wifiInfo=wifi.getConnectionInfo();
    }

    /**
     * 设备id/GSM手机的 IMEI 和 CDMA手机的 MEID
     */
    public static String getDeviceId(){
        return tm.getDeviceId();
    }

    /**
     * 设备的软件版本号
     */
    public static String getDeviceSoftwareVersion(){
        return tm.getDeviceSoftwareVersion();
    }

    /**
     * 手机号：
     * GSM手机的 MSISDN.
     */
    public static String getPhoneNumber(){
        return tm.getLine1Number();
    }

    /*
     * 获取ISO标准的国家码，即国际长途区号。
     * 注意：仅当用户已在网络注册后有效。
     *       在CDMA网络中结果也许不可靠。
     */
    public static String getNetworkCountryIso(){
        return tm.getNetworkCountryIso();
    }

    /*
   * 当前使用的网络类型：
   * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0
     NETWORK_TYPE_GPRS     GPRS网络  1
     NETWORK_TYPE_EDGE     EDGE网络  2
     NETWORK_TYPE_UMTS     UMTS网络  3
     NETWORK_TYPE_HSDPA    HSDPA网络  8
     NETWORK_TYPE_HSUPA    HSUPA网络  9
     NETWORK_TYPE_HSPA     HSPA网络  10
     NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4
     NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5
     NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6
     NETWORK_TYPE_1xRTT    1xRTT网络  7
   */
    public static int getNetworkType(){
        return tm.getNetworkType();
    }

    /*
   * 手机类型：
   * 例如： PHONE_TYPE_NONE  无信号
     PHONE_TYPE_GSM   GSM信号
     PHONE_TYPE_CDMA  CDMA信号
   */
    public static int getPhoneType(){
        return tm.getPhoneType();
    }

    /*
   * Returns the ISO country code equivalent for the SIM provider's country code.
   * 获取ISO国家码，相当于提供SIM卡的国家码。
   *
   */
    public static String getSimCountryIso(){
        return tm.getSimCountryIso();
    }

    /*
   * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.
   * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
   */
    public static String getSimOperator(){
        return tm.getSimOperator();
    }

    /*
   * 服务商名称：
   * 例如：中国移动、联通
   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
   */
    public static String getSimOperatorName(){
        return tm.getSimState()==tm.SIM_STATE_READY?tm.getSimOperatorName():"UNKNOW";
    }

    /*
     * SIM的状态信息：
     * SIM_STATE_UNKNOWN          未知状态 0
     * SIM_STATE_ABSENT           没插卡 1
     * SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2
     * SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3
     * SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4
     * SIM_STATE_READY            就绪状态 5
     */
    public static int getSimState(){
        return tm.getSimState();
    }

    /*
    * 唯一的用户ID：
    * 例如：IMSI(国际移动用户识别码) for a GSM phone.
    * 需要权限：READ_PHONE_STATE
    */
    public static String getSubscriberId(){
        return tm.getSubscriberId();
    }

    /*
     *  获取设备类型
     */
    public static String getDeviceType(){
        return Build.MANUFACTURER+"_"+ Build.MODEL;
    }

    /*
     *  获取android版本号
     */
    public static String getAndroidVersion(){
        return Build.VERSION.RELEASE;
    }

    /**获得mac地址**/
    public static String getMacAddress(){
        return wifiInfo.getMacAddress();
    }



    public static String getDeviceInfoString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDeviceId(IMEI) = " + getDeviceId());
        sb.append("\nDeviceSoftwareVersion = " + getDeviceSoftwareVersion());
        sb.append("\nLine1Number = " + getPhoneNumber());
        sb.append("\nNetworkCountryIso = " + getNetworkCountryIso());
        sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
        sb.append("\nNetworkType = " + getNetworkType());
        sb.append("\nPhoneType = " + getPhoneType());
        sb.append("\nSimCountryIso = " + getSimCountryIso());
        sb.append("\nSimOperator = " + getSimOperator());
        sb.append("\nSimOperatorName = " + getSimOperatorName());
        sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
        sb.append("\nSimState = " + tm.getSimState());
        sb.append("\nSubscriberId(IMSI) = " + getSubscriberId());
        sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
        sb.append("\nDeviceType = " + getDeviceType());
        sb.append("\nAndroidVersion = " + getAndroidVersion());
        sb.append("\nMacAddress = " + getMacAddress());
        return sb.toString();
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName() {
        String versionName = "";
        int versioncode=1;
        try {
            // ---get the package info---
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


    /**
     * 返回当前程序版本号
     */
    public static String getAppVersionCode() {
        String versionName = "";
        int versioncode=1;
        try {
            // ---get the package info---
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return String.valueOf(versioncode);
    }
}

