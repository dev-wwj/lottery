package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/4.
 */

public class CheckMobileResBean {

    /**
     * lang : en
     * time : 20170804154048
     * source : 1000
     * code : 101
     * value : {"isok":true,"errmsg":"该手机号可以注册"}
     * sign : RnioBF/zyB170TAcgTuBuQm3ygvkfK8e+VQLAHbgn/ZI1G
     * +VdkgfjEnLa9V3Zng9NKfzKxak4M4aJNNmqWf8qt8GLMujHr2xKFwTwKzVyino3GcICC
     * +kO5zHXmsqpJ9eonIWqelTA5lMN/qjWRK7xCgrx97mM21oV+aM8/lIzbY=
     * message : null
     */

    private String lang;
    private String time;
    private int source;
    private int code;
    private ValueBean value;
    private String sign;
    private Object message;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static class ValueBean {
        /**
         * isok : true
         * errmsg : 该手机号可以注册
         */

        private boolean isok;
        private String errmsg;

        public boolean isIsok() {
            return isok;
        }

        public void setIsok(boolean isok) {
            this.isok = isok;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }
}
