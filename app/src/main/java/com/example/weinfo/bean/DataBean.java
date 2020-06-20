package com.example.weinfo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class DataBean implements Serializable {
        /**
         * children : []
         * courseId : 13
         * id : 408
         * name : 鸿洋
         * order : 190000
         * parentChapterId : 407
         * userControlSetTop : false
         * visible : 1
         */
        public static final long serialVersionUID = 1L;
        private int courseId;
        private int id;
        @Id
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;
        private boolean isInterested=true;
        private int index;
        @Generated(hash = 1470721578)
        public DataBean(int courseId, int id, String name, int order,
                int parentChapterId, boolean userControlSetTop, int visible,
                boolean isInterested, int index) {
            this.courseId = courseId;
            this.id = id;
            this.name = name;
            this.order = order;
            this.parentChapterId = parentChapterId;
            this.userControlSetTop = userControlSetTop;
            this.visible = visible;
            this.isInterested = isInterested;
            this.index = index;
        }
        @Generated(hash = 908697775)
        public DataBean() {
        }
        public int getCourseId() {
            return this.courseId;
        }
        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }
        public int getId() {
            return this.id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getOrder() {
            return this.order;
        }
        public void setOrder(int order) {
            this.order = order;
        }
        public int getParentChapterId() {
            return this.parentChapterId;
        }
        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }
        public boolean getUserControlSetTop() {
            return this.userControlSetTop;
        }
        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }
        public int getVisible() {
            return this.visible;
        }
        public void setVisible(int visible) {
            this.visible = visible;
        }
        public boolean getIsInterested() {
            return this.isInterested;
        }
        public void setIsInterested(boolean isInterested) {
            this.isInterested = isInterested;
        }
        public int getIndex() {
            return this.index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
       
}