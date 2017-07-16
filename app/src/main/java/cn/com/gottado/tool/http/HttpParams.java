package cn.com.gottado.tool.http;

import org.xutils.http.RequestParams;

import cn.com.gottado.R;
import cn.com.gottado.tool.config.Config;
import cn.com.gottado.tool.util.DeviceInfo;
import cn.com.gottado.tool.util.ShaPreTools;

/**
 * Created by liaozp on 16/5/23.
 */
public class HttpParams extends RequestParams {

    public HttpParams(String action){
        super(HttpAction.getServerUrl()+action);
        addBaseParams();
    }


    private void addBaseParams(){
        this.addBodyParameter("system","android");
        this.addBodyParameter("username","1");
        this.addBodyParameter("password","2");
        this.addBodyParameter("appVersion", DeviceInfo.getAppVersionCode());
        this.addBodyParameter("lastUpdateTime", ShaPreTools.getString(Config.getContext().getString(R.string.pre_last_update_time)));
    }
}
