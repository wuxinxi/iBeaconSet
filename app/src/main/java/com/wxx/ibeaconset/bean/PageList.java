package com.wxx.ibeaconset.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */

public class PageList {

    /**
     * data : {"pages":[{"comment":"just for test","description":"test","icon_url":"https://www.baidu.com/img/bd_logo1","page_id":28840,"page_url":"http://xw.qq.com/testapi1","title":"测试1"},{"comment":"just for test","description":"test","icon_url":"https://www.baidu.com/img/bd_logo1","page_id":28842,"page_url":"http://xw.qq.com/testapi2","title":"测试2"}],"total_count":2}
     * errcode : 0
     * errmsg : success.
     */

    private DataBean data;
    private int errcode;
    private String errmsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static class DataBean {
        /**
         * pages : [{"comment":"just for test","description":"test","icon_url":"https://www.baidu.com/img/bd_logo1","page_id":28840,"page_url":"http://xw.qq.com/testapi1","title":"测试1"},{"comment":"just for test","description":"test","icon_url":"https://www.baidu.com/img/bd_logo1","page_id":28842,"page_url":"http://xw.qq.com/testapi2","title":"测试2"}]
         * total_count : 2
         */

        private int total_count;
        private List<PagesBean> pages;

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public List<PagesBean> getPages() {
            return pages;
        }

        public void setPages(List<PagesBean> pages) {
            this.pages = pages;
        }

        public static class PagesBean implements Parcelable {
            /**
             * comment : just for test
             * description : test
             * icon_url : https://www.baidu.com/img/bd_logo1
             * page_id : 28840
             * page_url : http://xw.qq.com/testapi1
             * title : 测试1
             */

            private String comment;
            private String description;
            private String icon_url;
            private int page_id;
            private String page_url;
            private String title;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public int getPage_id() {
                return page_id;
            }

            public void setPage_id(int page_id) {
                this.page_id = page_id;
            }

            public String getPage_url() {
                return page_url;
            }

            public void setPage_url(String page_url) {
                this.page_url = page_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "PagesBean{" +
                        "comment='" + comment + '\'' +
                        ", description='" + description + '\'' +
                        ", icon_url='" + icon_url + '\'' +
                        ", page_id=" + page_id +
                        ", page_url='" + page_url + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.comment);
                dest.writeString(this.description);
                dest.writeString(this.icon_url);
                dest.writeInt(this.page_id);
                dest.writeString(this.page_url);
                dest.writeString(this.title);
            }

            public PagesBean() {
            }

            protected PagesBean(Parcel in) {
                this.comment = in.readString();
                this.description = in.readString();
                this.icon_url = in.readString();
                this.page_id = in.readInt();
                this.page_url = in.readString();
                this.title = in.readString();
            }

            public static final Parcelable.Creator<PagesBean> CREATOR = new Parcelable.Creator<PagesBean>() {
                @Override
                public PagesBean createFromParcel(Parcel source) {
                    return new PagesBean(source);
                }

                @Override
                public PagesBean[] newArray(int size) {
                    return new PagesBean[size];
                }
            };
        }
    }
}
