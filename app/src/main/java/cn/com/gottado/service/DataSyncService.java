package cn.com.gottado.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ypy.eventbus.EventBus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.com.gottado.tool.db.DbUtil;
import cn.com.gottado.tool.db.table.MToolsItem;
import cn.com.gottado.tool.eventbus.model.ArrayListEvent;
import cn.com.gottado.tool.http.HttpAction;
import cn.com.gottado.tool.http.HttpParams;
import cn.com.gottado.tool.http.HttpUtil;
import cn.com.gottado.tool.http.JsonCallBack;
import cn.com.gottado.tool.util.JsonTools;
import cn.com.gottado.tool.util.LogUtil;

/**
 * 数据同步服务
 * Created by liaozp on 2017/7/16.
 */

public class DataSyncService extends Service {

    /***
     *是否有新数据
     */
    private boolean newItem=false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getPlayItemData();
        return super.onStartCommand(intent, flags, startId);
    }


    /****
     * 获取列表数据接口
     *
     */

    private void getPlayItemData(){
        HttpParams params=new HttpParams(HttpAction.listAction);
        new HttpUtil().invokePost(params, new JsonCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
                if(JsonTools.getJsonString(result,"code").equals("200")){
                    JSONArray dataArr=JsonTools.getJArrFromJObj(result,"data");
                    for (int i=0;i<dataArr.length();i++){
                        JSONObject obj=JsonTools.getJsonFromJArr(dataArr,i);
                        MToolsItem item=new MToolsItem();
                        item.setId(JsonTools.getJsonInteger(obj,"id"));
                        item.setTitle(JsonTools.getJsonString(obj,"title"));
                        item.setBgPicUrl(JsonTools.getJsonString(obj,"backgroundPictureUrl"));
                        item.setType(JsonTools.getJsonInteger(obj,"type"));
                        item.setScanCount(JsonTools.getJsonInteger(obj,"scanCount"));
                        item.setUseCount(JsonTools.getJsonInteger(obj,"useCount"));
                        item.setShareCount(JsonTools.getJsonInteger(obj,"shareCount"));
                        item.setSort(JsonTools.getJsonInteger(obj,"sort"));


                        JSONObject content=new JSONObject();
                        JsonTools.put(content,"android_path","cn.com.gottado.main.play.function.BDEditFragment");
                        JSONObject paramJson=new JSONObject();
                        JsonTools.put(paramJson,"title",item.getTitle());
                        JsonTools.put(content,"param",paramJson);
                        item.setContent(content.toString());
                        DbUtil.saveOrUpdate(item);
                        newItem=true;
                    }
                    if(newItem){
                        //通知主界面更新
                        ArrayList dataResult = (ArrayList<MToolsItem>) DbUtil.findAll(MToolsItem.class);
                        dataResult=(ArrayList<MToolsItem>) DbUtil.findAll(MToolsItem.class);
                        if(dataResult!=null&&dataResult.size()>0){
                            LogUtil.i(dataResult.size()+"");
                            EventBus.getDefault().post(new ArrayListEvent(dataResult));
                        }
                    }
                }
            }
        });
    }



}
