package com.wxx.ibeaconset.bean;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class DeviceList {

    /**
     * data : {"devices":[{"comment":"测试","device_id":805166,"last_active_time":1460563200,"major":10009,"minor":12143,"poi_id":0,"status":1,"uuid":"FDA50693-A4E2-4FB1-AFCF-C6EB07647825"},{"comment":"测试","device_id":805596,"last_active_time":1472227200,"major":10009,"minor":12653,"poi_id":0,"status":1,"uuid":"FDA50693-A4E2-4FB1-AFCF-C6EB07647825"}],"total_count":156}
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

    @Override
    public String toString() {
        return "DeviceList{" +
                "data=" + data +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }

    public static class DataBean {
        /**
         * devices : [{"comment":"测试","device_id":805166,"last_active_time":1460563200,"major":10009,"minor":12143,"poi_id":0,"status":1,"uuid":"FDA50693-A4E2-4FB1-AFCF-C6EB07647825"},{"comment":"测试","device_id":805596,"last_active_time":1472227200,"major":10009,"minor":12653,"poi_id":0,"status":1,"uuid":"FDA50693-A4E2-4FB1-AFCF-C6EB07647825"}]
         * total_count : 156
         */

        private int total_count;
        private List<DevicesBean> devices;

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public List<DevicesBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            this.devices = devices;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "total_count=" + total_count +
                    ", devices=" + devices +
                    '}';
        }

        public static class DevicesBean {
            /**
             * comment : 测试
             * device_id : 805166
             * last_active_time : 1460563200
             * major : 10009
             * minor : 12143
             * poi_id : 0
             * status : 1
             * uuid : FDA50693-A4E2-4FB1-AFCF-C6EB07647825
             */

            private String comment;
            private int device_id;
            private long last_active_time;
            private int major;
            private int minor;
            private int poi_id;
            private int status;
            private String uuid;

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getDevice_id() {
                return device_id;
            }

            public void setDevice_id(int device_id) {
                this.device_id = device_id;
            }

            public long getLast_active_time() {
                return last_active_time;
            }

            public void setLast_active_time(long last_active_time) {
                this.last_active_time = last_active_time;
            }

            public int getMajor() {
                return major;
            }

            public void setMajor(int major) {
                this.major = major;
            }

            public int getMinor() {
                return minor;
            }

            public void setMinor(int minor) {
                this.minor = minor;
            }

            public int getPoi_id() {
                return poi_id;
            }

            public void setPoi_id(int poi_id) {
                this.poi_id = poi_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            @Override
            public String toString() {
                return "DevicesBean{" +
                        "comment='" + comment + '\'' +
                        ", device_id=" + device_id +
                        ", last_active_time=" + last_active_time +
                        ", major=" + major +
                        ", minor=" + minor +
                        ", poi_id=" + poi_id +
                        ", status=" + status +
                        ", uuid='" + uuid + '\'' +
                        '}';
            }
        }
    }
}
