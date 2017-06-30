package cn.com.gottado.tool.http;

/**
 * Created by liaozp on 16/5/17.
 */
public abstract class StringCallBack extends XCallBack <String>{

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
    public String getResult(String result) {
        return result;
    }
}
