package com.example.weinfo.bean;

import java.util.List;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   22:32
 **/
public class HotBean {
    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        /**
         * news_id : 9724805
         * url : http://news-at.zhihu.com/api/2/news/9724805
         * thumbnail : https://pic4.zhimg.com/v2-0e402b49234af4a7989f86d62135cd97.jpg
         * title : 瞎扯 · 如何正确地吐槽
         */

        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
