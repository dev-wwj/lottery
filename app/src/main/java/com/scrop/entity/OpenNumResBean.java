package com.scrop.entity;

import java.util.List;

/**
 * Created by Scrop on 2017/9/18.
 */

public class OpenNumResBean {


    /**
     * lang : en
     * time : 20170918140114
     * source : 1000
     * code : 101
     * value : {"total":402674,"pagenum":40268,"pageindex":1,"pagesize":10,
     * "result":[{"buytime":"2017-09-18 13:48:30","chktime":"2017-09-18 13:51:19","num":"9,0,1,0,
     * 6","opentime":"2017-09-18 13:50:00","term":"20170918047"},{"buytime":"2017-09-18
     * 13:38:30","chktime":"2017-09-18 13:41:22","num":"8,5,4,4,1","opentime":"2017-09-18
     * 13:40:00","term":"20170918046"},{"buytime":"2017-09-18 13:28:30","chktime":"2017-09-18
     * 13:30:54","num":"3,2,0,9,6","opentime":"2017-09-18 13:30:00","term":"20170918045"},
     * {"buytime":"2017-09-18 13:18:30","chktime":"2017-09-18 13:21:17","num":"0,8,6,0,4",
     * "opentime":"2017-09-18 13:20:00","term":"20170918044"},{"buytime":"2017-09-18 13:08:30",
     * "chktime":"2017-09-18 13:11:19","num":"7,4,8,6,8","opentime":"2017-09-18 13:10:00",
     * "term":"20170918043"},{"buytime":"2017-09-18 12:58:30","chktime":"2017-09-18 13:01:28",
     * "num":"1,8,3,2,5","opentime":"2017-09-18 13:00:00","term":"20170918042"},
     * {"buytime":"2017-09-18 12:48:30","chktime":"2017-09-18 12:51:24","num":"3,8,8,3,9",
     * "opentime":"2017-09-18 12:50:00","term":"20170918041"},{"buytime":"2017-09-18 12:38:30",
     * "chktime":"2017-09-18 12:41:20","num":"2,6,4,1,2","opentime":"2017-09-18 12:40:00",
     * "term":"20170918040"},{"buytime":"2017-09-18 12:28:30","chktime":"2017-09-18 12:31:17",
     * "num":"5,7,4,5,5","opentime":"2017-09-18 12:30:00","term":"20170918039"},
     * {"buytime":"2017-09-18 12:18:30","chktime":"2017-09-18 12:21:18","num":"0,2,6,0,4",
     * "opentime":"2017-09-18 12:20:00","term":"20170918038"}],"isok":true}
     * sign : HM7FznePV0fpaf7bokd/uBCvnUd3
     * +1d8NjXUuJhtZC83TQ8oum12qSEdBB6tMYJPPHzC0mnqXQdIfdvlMoSVPOBJVYMAj3gJrZig51ZMNTUu48OW5JFpYmgwjqnUl8a4aBLsD/9zk1NB0gP/mFS5ZBr9CyL7M4RHsnqnlANf8eg=
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
         * total : 402674
         * pagenum : 40268
         * pageindex : 1
         * pagesize : 10
         * result : [{"buytime":"2017-09-18 13:48:30","chktime":"2017-09-18 13:51:19","num":"9,0,
         * 1,0,6","opentime":"2017-09-18 13:50:00","term":"20170918047"},{"buytime":"2017-09-18
         * 13:38:30","chktime":"2017-09-18 13:41:22","num":"8,5,4,4,1","opentime":"2017-09-18
         * 13:40:00","term":"20170918046"},{"buytime":"2017-09-18 13:28:30","chktime":"2017-09-18
         * 13:30:54","num":"3,2,0,9,6","opentime":"2017-09-18 13:30:00","term":"20170918045"},
         * {"buytime":"2017-09-18 13:18:30","chktime":"2017-09-18 13:21:17","num":"0,8,6,0,4",
         * "opentime":"2017-09-18 13:20:00","term":"20170918044"},{"buytime":"2017-09-18
         * 13:08:30","chktime":"2017-09-18 13:11:19","num":"7,4,8,6,8","opentime":"2017-09-18
         * 13:10:00","term":"20170918043"},{"buytime":"2017-09-18 12:58:30","chktime":"2017-09-18
         * 13:01:28","num":"1,8,3,2,5","opentime":"2017-09-18 13:00:00","term":"20170918042"},
         * {"buytime":"2017-09-18 12:48:30","chktime":"2017-09-18 12:51:24","num":"3,8,8,3,9",
         * "opentime":"2017-09-18 12:50:00","term":"20170918041"},{"buytime":"2017-09-18
         * 12:38:30","chktime":"2017-09-18 12:41:20","num":"2,6,4,1,2","opentime":"2017-09-18
         * 12:40:00","term":"20170918040"},{"buytime":"2017-09-18 12:28:30","chktime":"2017-09-18
         * 12:31:17","num":"5,7,4,5,5","opentime":"2017-09-18 12:30:00","term":"20170918039"},
         * {"buytime":"2017-09-18 12:18:30","chktime":"2017-09-18 12:21:18","num":"0,2,6,0,4",
         * "opentime":"2017-09-18 12:20:00","term":"20170918038"}]
         * isok : true
         */

        private int total;
        private int pagenum;
        private int pageindex;
        private int pagesize;
        private boolean isok;
        private List<ResultBean> result;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPagenum() {
            return pagenum;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }

        public int getPageindex() {
            return pageindex;
        }

        public void setPageindex(int pageindex) {
            this.pageindex = pageindex;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public boolean isIsok() {
            return isok;
        }

        public void setIsok(boolean isok) {
            this.isok = isok;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * buytime : 2017-09-18 13:48:30
             * chktime : 2017-09-18 13:51:19
             * num : 9,0,1,0,6
             * opentime : 2017-09-18 13:50:00
             * term : 20170918047
             */

            private String buytime;
            private String chktime;
            private String num;
            private String opentime;
            private String term;

            public String getBuytime() {
                return buytime;
            }

            public void setBuytime(String buytime) {
                this.buytime = buytime;
            }

            public String getChktime() {
                return chktime;
            }

            public void setChktime(String chktime) {
                this.chktime = chktime;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getOpentime() {
                return opentime;
            }

            public void setOpentime(String opentime) {
                this.opentime = opentime;
            }

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
            }
        }
    }
}
