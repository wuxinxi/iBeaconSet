package com.wxx.ibeaconset.bean;

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

        public static class PagesBean {
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
        }
    }
}
