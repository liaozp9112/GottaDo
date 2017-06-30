package cn.com.gottado.main.play.config;


import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/6/8.
 */
public class PlayItem {

    private String title;

    private String tip;

    private Integer height;

    private int imageViewRes;

    private String className;

    /***启动参数json格式***/
    public String params;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return tip;
    }


    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public int getImageViewRes() {
        return imageViewRes;
    }

    public void setImageViewRes(int imageViewRes) {
        this.imageViewRes = imageViewRes;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
