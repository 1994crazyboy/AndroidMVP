package me.heyboy.mymvpdemo.model.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * 创 建 人： Henning
 * 创建时间： 17-11-1 下午11:57
 * 工程名称： MyApplication
 * 包   名： me.heyboy.mymvpdemo.model.entities
 */
@Entity
public class ImgRecorder2 {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-03-06 14:11","title":"性感黛欣霓内衣写真","description":"美女图片","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_4e1ed310e8b3553d266bd619949ec01c3-760x500.jpg","url":"http://m.xxxiao.com/435"}]
     */
    @Id(autoincrement = true)
    private Long id;
    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static List<ImgRecorder> transToImg(ImgRecorder2 imgRecorder2) {
        List<ImgRecorder> imgRecorderList = new ArrayList<>();

        List<NewslistBean> newslistBeanList = imgRecorder2.getNewslist();
        for (NewslistBean newslistBean : newslistBeanList) {
            ImgRecorder imgRecorder = new ImgRecorder();
            imgRecorder.setDescription(newslistBean.getDescription());
            imgRecorder.setTitle(newslistBean.getTitle());
            //imgRecorder.setCtime(newslistBean.getCtime());
            imgRecorder.setPicUrl(newslistBean.getPicUrl());
            imgRecorder.setUrl(newslistBean.getUrl());

            imgRecorderList.add(imgRecorder);

        }

        return imgRecorderList;
    }

    public static class NewslistBean {
        /**
         * ctime : 2016-03-06 14:11
         * title : 性感黛欣霓内衣写真
         * description : 美女图片
         * picUrl : http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_4e1ed310e8b3553d266bd619949ec01c3-760x500.jpg
         * url : http://m.xxxiao.com/435
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
