package cn.com.gottado.tool.db.table;

import org.xutils.db.Selector;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.Date;

import cn.com.gottado.tool.db.DbUtil;
import cn.com.gottado.tool.util.LogUtil;

/**
 * Created by liaozp on 2017/7/11.
 */
@Table(name="mtools_item")
public class MToolsItem {

    @Column(name="id",isId = true)
    private Integer id;
    @Column(name="is_deleted")
    private Integer isDeleted;
    @Column(name="creator")
    private String creator;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="modifier")
    private String modifier;
    @Column(name="modify_time")
    private Date modifyTime;
    @Column(name="title")
    private String title;
    @Column(name="background_picture_url")
    private String bgPicUrl;
    @Column(name="type")
    private Integer type;
    @Column(name="content")
    private String content;
    @Column(name="scan_count")
    private Integer scanCount;
    @Column(name="use_count")
    private Integer useCount;
    @Column(name="share_count")
    private Integer shareCount;
    @Column(name="sort")
    private Integer sort;

    public MToolsItem() {
    }

    public MToolsItem(Integer id) {
        this.id = id;
    }


    public static  ArrayList<MToolsItem> findBySort(){
        ArrayList<MToolsItem> list=null;
        try {
            list = (ArrayList<MToolsItem>)DbUtil.getDbManager().selector(MToolsItem.class).orderBy("sort").findAll();
        }catch (DbException e){
            LogUtil.i(e.getMessage());
        }
        return list;
    }


    public static  ArrayList<MToolsItem> findByTitleKey(String key){
        ArrayList<MToolsItem> list=null;
        try {
            WhereBuilder whereBuilder = WhereBuilder.b("title", "like", "%"+key+"%");
            list = (ArrayList<MToolsItem>)DbUtil.getDbManager().selector(MToolsItem.class).where(whereBuilder).orderBy("sort").findAll();
        }catch (DbException e){
            LogUtil.i(e.getMessage());
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBgPicUrl() {
        return bgPicUrl;
    }

    public void setBgPicUrl(String bgPicUrl) {
        this.bgPicUrl = bgPicUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScanCount() {
        return scanCount;
    }

    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
