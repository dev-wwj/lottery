package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/3.
 */

public class LoginBean {

    /**
     * lang : en
     * time : 20170803154652
     * source : 1000
     * code : 101
     * value : {"islogin":false,"userid":"2982","errmsg":"密码错误,拒绝登录","accesstoken":""}
     * sign : kqDkirTV3mk1J9p/UI77jp2KuzYU3mzhCAsRugdWzECu9BbGMW
     * /qA0tDYTHcz149XRxdgZ4pTcy19rexmnx93XZq3qvTZ1CuSm1zw9SsZSAB7INLxWAHmgsImaoiyzCLxkdOMfLKiZXl1Q7m7goPw9Av79hYP71K/LZqW1I48W4=
     * message : null
     */

    private String lang;
    private String time;
    private int source;
    private int code;
    private LoginMsg value;
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

    public LoginMsg getValue() {
        return value;
    }

    public void setValue(LoginMsg value) {
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

    public static class LoginMsg {
        /**
         * islogin : false
         * userid : 2982
         * errmsg : 密码错误,拒绝登录
         * accesstoken :
         */

        private boolean islogin;
        private String userid;
        private String errmsg;
        private String accesstoken;

        public boolean isIslogin() {
            return islogin;
        }

        public void setIslogin(boolean islogin) {
            this.islogin = islogin;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getAccesstoken() {
            return accesstoken;
        }

        public void setAccesstoken(String accesstoken) {
            this.accesstoken = accesstoken;
        }
    }
}
