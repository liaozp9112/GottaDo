package cn.com.gottado.tool.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences 工具类
 */
public class ShaPreTools {

    private static SharedPreferences sharedPreferences = null;

    public static void init(Context context) {
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 清空sharedPreferences
     */
    public static void removeAll() {
        sharedPreferences.edit().clear().commit();
    }

    /**
     * 清空指定key
     */

    public static void remove(String key) {
        if (contain(key)) {
            sharedPreferences.edit().remove(key).commit();
        }
    }

    /**
     * 判断是否包含指定key
     */
    public static Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * ************* String操作部分 ****************
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultStr) {
        return sharedPreferences.getString(key, defaultStr);
    }

    public static void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * ************* Boolean操作部分 ****************
     */
    public static Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static Boolean getBoolean(String key, Boolean defaultBool) {
        return sharedPreferences.getBoolean(key, defaultBool);
    }

    public static void putBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * ************* Interger操作部分 ****************
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultInt) {
        return sharedPreferences.getInt(key, defaultInt);
    }

    public static void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * ************* long操作部分 ****************
     */
    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static long getLong(String key, long defaultLong) {
        return sharedPreferences.getLong(key, defaultLong);
    }

    public static void putLong(String key, int value) {
        sharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * ************* Float操作部分 ****************
     */
    public static Float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public static Float getFloat(String key, Float defaultFlo) {
        return sharedPreferences.getFloat(key, defaultFlo);
    }

    public static void putFloat(String key, Float value) {
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    /**************** Set操作部分 **************
     public Set getSet(String key) {
     return getSet(key, null);
     }

     public Set getSet(String key, Set defaultSet) {
     return sharedPreferences.getStringSet(key, defaultSet);
     }

     public void putSet(String key, Set value) {
     sharedPreferences.edit().putStringSet(key, value).commit();
     }***/
}
