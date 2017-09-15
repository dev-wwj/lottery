package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/15.
 */

public class RegisterResBean {


    /**
     * lang : en
     * time : 20170815162157
     * source : 1000
     * code : 101
     * value : {"accesstoken":"ce1d3384-8192-11e7-b75c-00163e13a8e6","isok":true,"errmsg":"注册成功"}
     * sign : ThRR2kJDsM8AH3lebp8zFzygB7kayeH9fp3FVGd1cBmoC1xKCheAWO1ZeRjZldOI56EJF/du
     * +y0mp5Aih88beNOWlUwHfh87j8pbqtT4aN86bcNfzj4sw8vmkRkxetWvidyXgspeCgzDyfQAOwrk47ZEOio4/b
     * /iWJ4ElmNLBjY=
     * message : null
     */

    private String lang;
    private String time;
    private int source;
    private int code;
    private RegisterRes value;
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

    public RegisterRes getValue() {
        return value;
    }

    public void setValue(RegisterRes value) {
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

    public static class RegisterRes {
        /**
         * accesstoken : ce1d3384-8192-11e7-b75c-00163e13a8e6
         * isok : true
         * errmsg : 注册成功
         */

        private String accesstoken;
        private boolean isok;
        private String errmsg;

        public String getAccesstoken() {
            return accesstoken;
        }

        public void setAccesstoken(String accesstoken) {
            this.accesstoken = accesstoken;
        }

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
