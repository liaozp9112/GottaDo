package cn.com.gottado.tool.http;


import cn.com.gottado.R;
import cn.com.gottado.tool.config.Config;
import cn.com.gottado.tool.util.ShaPreTools;

/**
 * Created by liaozp on 16/5/18.
 */
public class HttpAction {


    /**base url action*/
    public static String getServerUrl(){
        return ShaPreTools.getString("gcyd_pref_server_baseurl",
                Config.getContext().getString(R.string.pref_server_baseurl_defaultValue));
    }


    public final static String positionAction="position/uploadpositiontask";

}
