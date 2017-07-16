package cn.com.gottado.tool.db;

import org.xutils.DbManager;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.com.gottado.tool.db.table.MToolsItem;
import cn.com.gottado.tool.util.LogUtil;

/**
 * Created by Administrator on 2017/7/13.
 */

public class DbUtil {

    private static DbManager dbManager= x.getDb(XDaoConfig.getDaoConfig());

    public static DbManager getDbManager(){
        return dbManager;
    }

    public static void saveOrUpdate(Object object){
        try {
            dbManager.saveOrUpdate(object);
        }catch (DbException e){
            LogUtil.i(e.getMessage());
        }
    }


    public static void save(Object object){
        try {
            dbManager.save(object);
        }catch (DbException e){
            LogUtil.i(e.getMessage());
        }
    }
    public static <T> List<T> findAll(Class<T> entityType){
        try {
            return dbManager.findAll(entityType);
        }catch (DbException e){
            LogUtil.i(e.getMessage());
        }
        return null;
    }


    public static <T> ArrayList<T> find(Selector<T> selector){
        ArrayList<T> list=null;
        try {
            dbManager.selector(MToolsItem.class).orderBy("sss").findAll();

            list = (ArrayList<T>) selector.findAll();
        }catch (DbException e){
            LogUtil.i(e.getMessage());
        }
        return list;
    }
}
