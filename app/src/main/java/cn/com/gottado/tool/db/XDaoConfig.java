package cn.com.gottado.tool.db;

import org.xutils.DbManager;

import java.io.File;

/**
 * Created by Administrator on 2017/6/21.
 */

public class XDaoConfig {

    private final static int DB_VERSION=1;

    private final static String DB_PATH="";

    private final static String DB_NAME="gottado.db";

    private static DbManager.DaoConfig daoConfig=null;

    public static  DbManager.DaoConfig getDaoConfig() {
        if(daoConfig==null){
            initDbConfig();
        }
        return daoConfig;
    }

    private static void initDbConfig(){
        daoConfig = new DbManager.DaoConfig()
                .setDbName(DB_NAME)//设置数据库的名称
                .setAllowTransaction(true)//设置是否允许事务，默认true
                .setDbDir(new File(DB_PATH)) // 设置数据库的存放路径, 默认存储在app的私有目录(数据库默认存储在/data/data/你的应用程序/database/xxx.db)
                .setDbVersion(DB_VERSION)//设置数据库的版本号
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });
    }
}
