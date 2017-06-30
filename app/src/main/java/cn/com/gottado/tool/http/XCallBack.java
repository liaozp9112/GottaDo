package cn.com.gottado.tool.http;


/**
 * Created by liaozp on 16/5/17.
 */
public abstract class XCallBack<T> {

    public abstract void onSuccess(T result);

    public abstract void onError(Throwable throwable, boolean b) ;

    public abstract void onCancelled(Exception e);

    public abstract void onFinished();

    public abstract T getResult(String result);
}
