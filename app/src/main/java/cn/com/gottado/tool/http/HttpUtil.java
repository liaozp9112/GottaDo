package cn.com.gottado.tool.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.com.gottado.tool.util.StringTools;


/**
 * Created by liaozp on 16/5/16.
 */
public class HttpUtil {

    private final String TAG ="HttpUtil";
    private Context mContext;

    private String progressString="";

    private ProgressDialog progressDialog=null;
    public HttpUtil() {
        this(null,"");
    }

    public HttpUtil(Context mContext) {
        this(mContext,"正在请求数据...");
    }

    public HttpUtil(Context mContext, String progressString) {
        this.mContext = mContext;
        this.progressString = progressString;
        onPreInvoke();

    }

    private void onPreInvoke(){
        if(mContext!=null&& StringTools.strNotEmpty(progressString)){
            progressDialog=new ProgressDialog(mContext,ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
            progressDialog.setMessage(progressString);
        }
    }

    private void onPostInvoke(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    /**
     *
     * @param params
     * @param callBack
     */
    public void invokePost(RequestParams params, final XCallBack callBack){
        if(progressDialog!=null&&!progressDialog.isShowing()){
            progressDialog.show();
        }
        x.http().post(params,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Object object = callBack.getResult(result);
                callBack.onSuccess(object);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                if(mContext!=null) {
                    Toast.makeText(mContext, "数据请求失败,请检查网络或联系系统管理员,ErrorCode:" + throwable.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                callBack.onError(throwable, b);
            }

            @Override
            public void onCancelled(CancelledException e) {
                callBack.onCancelled(e);
            }

            @Override
            public void onFinished() {
                onPostInvoke();
                callBack.onFinished();
            }
        });
    }
}
