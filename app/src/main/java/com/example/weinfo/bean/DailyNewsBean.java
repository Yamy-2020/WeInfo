package com.example.weinfo.bean;

import java.util.List;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:30
 **/
public class DailyNewsBean {

    /**
     * date : 20200616
     * stories : [{"image_hue":"0x6e6a42","title":"如何在一周内快速摸清一个行业？","url":"https://daily.zhihu.com/story/9724948","hint":"程毅南 · 8 分钟阅读","ga_prefix":"061607","images":["https://pic2.zhimg.com/v2-70c528c661f65f13571252c651ce09fd.jpg"],"type":0,"id":9724948},{"image_hue":"0xb3723d","title":"冷链运输会成为新冠传播的潜在风险吗？","url":"https://daily.zhihu.com/story/9724951","hint":"钱程 · 3 分钟阅读","ga_prefix":"061607","images":["https://pic3.zhimg.com/v2-319c066ec8567640cd4fed409b566a76.jpg"],"type":0,"id":9724951},{"image_hue":"0x1c0e85","title":"《乘风破浪的姐姐们》会不会让女明星们再度翻红？","url":"https://daily.zhihu.com/story/9724952","hint":"二十二岛主 · 4 分钟阅读","ga_prefix":"061607","images":["https://pic2.zhimg.com/v2-f96be7eb622f7a2bab4f9e8f028c3a8d.jpg"],"type":0,"id":9724952},{"image_hue":"0x19122b","title":"想做编剧，但是想不出故事怎么办？","url":"https://daily.zhihu.com/story/9724945","hint":"郑远 · 4 分钟阅读","ga_prefix":"061607","images":["https://pic2.zhimg.com/v2-418e65558eb2b00c16d599166c28e58d.jpg"],"type":0,"id":9724945},{"image_hue":"0x6e493b","title":"大误 · 建筑大师打造高端鸡舍","url":"https://daily.zhihu.com/story/9724936","hint":"游旭东 · 2 分钟阅读","ga_prefix":"061607","images":["https://pic1.zhimg.com/v2-fb9fbf4618bed802ceec3f947d5690c4.jpg"],"type":0,"id":9724936},{"image_hue":"0xb39373","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9724932","hint":"VOL.2420","ga_prefix":"061606","images":["https://pic1.zhimg.com/v2-94cb4a666b6510455db7cb2e41d15878.jpg"],"type":0,"id":9724932}]
     * top_stories : [{"image_hue":"0x2b373d","hint":"作者 / 一路春暖月圆","url":"https://daily.zhihu.com/story/9724896","image":"https://pic4.zhimg.com/v2-b3e72e59d6816c74ebb9d477c6e27b3b.jpg","title":"神奇动物在这里","ga_prefix":"061407","type":0,"id":9724896},{"image_hue":"0x344a3a","hint":"作者 / myosin myosin","url":"https://daily.zhihu.com/story/9724859","image":"https://pic1.zhimg.com/v2-03ddf071df9b58374797f4eb387be1f0.jpg","title":"年轻人很早洞察人事、谙于世故预示着本性平庸吗？","ga_prefix":"061307","type":0,"id":9724859},{"image_hue":"0x212f40","hint":"作者 / 黄Isabel","url":"https://daily.zhihu.com/story/9724809","image":"https://pic4.zhimg.com/v2-ebcd2881f09f31342b91f2f68fb3c2ff.jpg","title":"美学是什么？有什么用？","ga_prefix":"061207","type":0,"id":9724809},{"image_hue":"0xb3857d","hint":"作者 / 苗华栋","url":"https://daily.zhihu.com/story/9724781","image":"https://pic3.zhimg.com/v2-f04d87e0735140857e2da22f99fcb4de.jpg","title":"1000 桶水中 1 桶有毒，猪喝毒水会死，想找到这桶毒水需要几头猪？","ga_prefix":"061107","type":0,"id":9724781},{"image_hue":"0xb39256","hint":"作者 / 二氧化硅","url":"https://daily.zhihu.com/story/9724725","image":"https://pic1.zhimg.com/v2-b850e8e6e33ad7f6ef7879fe92dff554.jpg","title":"纯棉的衣服既不天然，也不环保","ga_prefix":"061007","type":0,"id":9724725}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * image_hue : 0x6e6a42
         * title : 如何在一周内快速摸清一个行业？
         * url : https://daily.zhihu.com/story/9724948
         * hint : 程毅南 · 8 分钟阅读
         * ga_prefix : 061607
         * images : ["https://pic2.zhimg.com/v2-70c528c661f65f13571252c651ce09fd.jpg"]
         * type : 0
         * id : 9724948
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private int id;
        private List<String> images;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image_hue : 0x2b373d
         * hint : 作者 / 一路春暖月圆
         * url : https://daily.zhihu.com/story/9724896
         * image : https://pic4.zhimg.com/v2-b3e72e59d6816c74ebb9d477c6e27b3b.jpg
         * title : 神奇动物在这里
         * ga_prefix : 061407
         * type : 0
         * id : 9724896
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private int id;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
