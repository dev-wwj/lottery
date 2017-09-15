package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/16.
 */

public class IndexPicResBean {

    /**
     * lang : en
     * time : 20170816151558
     * source : 1000
     * code : 101
     * value : {"indexpic":"07031009190.png|07031020141.png","isok":true}
     * sign : giheIiTK45ijva+x2tjrgF1AOtr0SOD6DkxYxu5L+yaYgAvravMDu9ajibaOnGh
     * +G2l8UUn8KN4EUscdH5Xn6kbeDQedCcliIZ6LQ277nWdB6bueY2XEAlCsloOTxPiXTS13b6UxlAk1SDKgd5zSiWef
     * +sLrgPrQAxPtHCb+Yk8=
     * message : null
     */

    private String lang;
    private String time;
    private int source;
    private int code;
    private IndexPicRes value;
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

    public IndexPicRes getValue() {
        return value;
    }

    public void setValue(IndexPicRes value) {
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

    public static class IndexPicRes {
        /**
         * indexpic : 07031009190.png|07031020141.png
         * isok : true
         */

        private String indexpic;
        private boolean isok;

        public String getIndexpic() {
            return indexpic;
        }

        public void setIndexpic(String indexpic) {
            this.indexpic = indexpic;
        }

        public boolean isIsok() {
            return isok;
        }

        public void setIsok(boolean isok) {
            this.isok = isok;
        }
    }
}
