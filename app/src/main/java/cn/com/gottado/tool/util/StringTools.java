package cn.com.gottado.tool.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


import android.widget.EditText;

/**
 * string 工具类
 *
 * @author liaozp
 */
public class StringTools {
    /**
     * 判断是否为空字符串
     *
     * @return {@link Boolean}
     */
    public static boolean strEmpty(String str) {
        return (str == null || str.trim().equals(""));
    }

    /**
     * 判断是否不为空字符串
     *
     * @return {@link Boolean}
     */
    public static boolean strNotEmpty(String str) {
        return !strEmpty(str);
    }

    /**
     * 获取EditText的无空格内容
     *
     * @return {@link String}
     */
    public static String getEdittxt(EditText edittxt) {
        return edittxt.getText().toString().trim();
    }

    /**
     * 判断EditText是否为空
     *
     * @return {@link Boolean}
     */
    public static boolean editEmpty(EditText edittxt) {
        return strEmpty(getEdittxt(edittxt));
    }

    /**
     * 判断EditText是否不为空
     *
     * @return {@link Boolean}
     */
    public static boolean editNotEmpty(EditText edittxt) {
        return strNotEmpty(getEdittxt(edittxt));
    }

    /**
     * 为一个字符串进行md5加密
     *
     * @return {@link String}
     */
    public static String toMD5(String string) {

        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return strNotEmpty(hex.toString()) ? hex.toString().trim() : "";
    }

    /**
     * 生成一个随机的UUID
     */
    public static String getUUIDString() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

}
