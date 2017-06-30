package cn.com.gottado.tool.http;

import org.json.JSONArray;

import cn.com.gottado.tool.util.JsonTools;


/**
 * Created by liaozp on 16/5/17.
 */
public abstract class JsonArrCallBack extends XCallBack<JSONArray>{

    @Override
    public void onCancelled(Exception e) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onError(Throwable throwable, boolean b) {

    }

    @Override
    public JSONArray getResult(String result) {
        return JsonTools.generateJsonArr(result);
    }
}
