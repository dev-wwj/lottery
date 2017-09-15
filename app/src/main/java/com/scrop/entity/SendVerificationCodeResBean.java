package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/4.
 */

public class SendVerificationCodeResBean {


    /**
     * lang : en
     * time : 20170804154900
     * source : 1000
     * code : 101
     * value : {"isok":true,"jym":"4ffb3d1071964955b819f1d97e17d9c5","errmsg":"短信发送成功",
     * "mobile":"1106"}
     * sign : CGjs2qRWRcf77bAvLROsmmWX3Zqi4q4Ti4Ced7BCGSHGtXeOrry5Vqbwva
     * +cMZXcvxRc0xXqBdXenU6VVwTJloX6j4/Z4a3Jc8enA4cky73
     * +eA08DsrIiwvEU1N94lhXYLmyQ5b1HQnIDsb8Xl7dvPcglwtiHzrp9sF4Xf/sAlg=
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
         * jym : 4ffb3d1071964955b819f1d97e17d9c5
         * errmsg : 短信发送成功
         * mobile : 1106
         */

        private boolean isok;
        private String jym;
        private String errmsg;
        private String mobile;

        public boolean isIsok() {
            return isok;
        }

        public void setIsok(boolean isok) {
            this.isok = isok;
        }

        public String getJym() {
            return jym;
        }

        public void setJym(String jym) {
            this.jym = jym;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
