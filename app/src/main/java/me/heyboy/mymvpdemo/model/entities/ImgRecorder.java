package me.heyboy.mymvpdemo.model.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 创 建 人： Henning
 * 创建时间： 17-10-25 下午9:12
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.model.entities
 *
 * 专门记录下载了多少美女图片
 */
@Entity
public class ImgRecorder {
    @Id(autoincrement = true)
    private Long id;
    private Date ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;
    @Generated(hash = 678152106)
    public ImgRecorder(Long id, Date ctime, String title, String description,
            String picUrl, String url) {
        this.id = id;
        this.ctime = ctime;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }
    @Generated(hash = 1164048147)
    public ImgRecorder() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCtime() {
        return this.ctime;
    }
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
