package cn.com.gottado.tool.http;

import org.xutils.http.RequestParams;

/**
 * Created by liaozp on 16/5/23.
 */
public class HttpParams extends RequestParams {

    public HttpParams(String action){
        super(HttpAction.getServerUrl()+action);
    }
}
