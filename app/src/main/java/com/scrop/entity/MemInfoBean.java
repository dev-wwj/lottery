package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/8.
 */

public class MemInfoBean {


    /**
     * lang : en
     * time : 20170809174815
     * source : 1000
     * code : 101
     * value : {"bankcardid":"","bankcat":"","cardid":"41152219891002****","coin":1012117.34,
     * "coinpass":"8a08096e88dda769ec8e491e24b79677","email":"hello9832@162.com","isbuy":true,
     * "islock":false,"ismod":false,"ispnt":false,"lastlogintime":"2017-08-09 16:09:08",
     * "mobile":"185****1608","moshi":1,"name":"管**","pic":"07111454074807_1.jpg","pnt":1,
     * "preid":"0","qq":"22232323","rate":0.6,"regip":"222.73.98.125","regtime":"2008-07-17
     * 11:50:25","rolemem":"0","updtime":"2008-07-17 11:50:25","upduser":"","userid":"4807",
     * "username":"test","zaitou":0,"loginstatus":true,"unreadlet":0,"issign":false,"signnum":0}
     * sign : GAxS6YbRYiupFDFwL6AT6vAOwPL317gdbRR017nh4m3fr064TV7Q5JX9e4
     * /m7lnCdbTnMQ4Cl1vEO30zIqFkqtnt7ITfPoZ1Y4uEJQaCnk8jbIN4nc4lM+NP2Qj/GtcgbpVkqn
     * +VtMFTwReKiVePiIyZn5xyqjTIqVrRg2RfVks=
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
         * bankcardid :
         * bankcat :
         * cardid : 41152219891002****
         * coin : 1012117.34
         * coinpass : 8a08096e88dda769ec8e491e24b79677
         * email : hello9832@162.com
         * isbuy : true
         * islock : false
         * ismod : false
         * ispnt : false
         * lastlogintime : 2017-08-09 16:09:08
         * mobile : 185****1608
         * moshi : 1.0
         * name : 管**
         * pic : 07111454074807_1.jpg
         * pnt : 1.0
         * preid : 0
         * qq : 22232323
         * rate : 0.6
         * regip : 222.73.98.125
         * regtime : 2008-07-17 11:50:25
         * rolemem : 0
         * updtime : 2008-07-17 11:50:25
         * upduser :
         * userid : 4807
         * username : test
         * zaitou : 0.0
         * loginstatus : true
         * unreadlet : 0
         * issign : false
         * signnum : 0
         */

        private String bankcardid;
        private String bankcat;
        private String cardid;
        private double coin;
        private String coinpass;
        private String email;
        private boolean isbuy;
        private boolean islock;
        private boolean ismod;
        private boolean ispnt;
        private String lastlogintime;
        private String mobile;
        private double moshi;
        private String name;
        private String pic;
        private double pnt;
        private String preid;
        private String qq;
        private double rate;
        private String regip;
        private String regtime;
        private String rolemem;
        private String updtime;
        private String upduser;
        private String userid;
        private String username;
        private double zaitou;
        private boolean loginstatus;
        private int unreadlet;
        private boolean issign;
        private int signnum;

        public String getBankcardid() {
            return bankcardid;
        }

        public void setBankcardid(String bankcardid) {
            this.bankcardid = bankcardid;
        }

        public String getBankcat() {
            return bankcat;
        }

        public void setBankcat(String bankcat) {
            this.bankcat = bankcat;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public double getCoin() {
            return coin;
        }

        public void setCoin(double coin) {
            this.coin = coin;
        }

        public String getCoinpass() {
            return coinpass;
        }

        public void setCoinpass(String coinpass) {
            this.coinpass = coinpass;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isIsbuy() {
            return isbuy;
        }

        public void setIsbuy(boolean isbuy) {
            this.isbuy = isbuy;
        }

        public boolean isIslock() {
            return islock;
        }

        public void setIslock(boolean islock) {
            this.islock = islock;
        }

        public boolean isIsmod() {
            return ismod;
        }

        public void setIsmod(boolean ismod) {
            this.ismod = ismod;
        }

        public boolean isIspnt() {
            return ispnt;
        }

        public void setIspnt(boolean ispnt) {
            this.ispnt = ispnt;
        }

        public String getLastlogintime() {
            return lastlogintime;
        }

        public void setLastlogintime(String lastlogintime) {
            this.lastlogintime = lastlogintime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public double getMoshi() {
            return moshi;
        }

        public void setMoshi(double moshi) {
            this.moshi = moshi;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public double getPnt() {
            return pnt;
        }

        public void setPnt(double pnt) {
            this.pnt = pnt;
        }

        public String getPreid() {
            return preid;
        }

        public void setPreid(String preid) {
            this.preid = preid;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getRegip() {
            return regip;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getRolemem() {
            return rolemem;
        }

        public void setRolemem(String rolemem) {
            this.rolemem = rolemem;
        }

        public String getUpdtime() {
            return updtime;
        }

        public void setUpdtime(String updtime) {
            this.updtime = updtime;
        }

        public String getUpduser() {
            return upduser;
        }

        public void setUpduser(String upduser) {
            this.upduser = upduser;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public double getZaitou() {
            return zaitou;
        }

        public void setZaitou(double zaitou) {
            this.zaitou = zaitou;
        }

        public boolean isLoginstatus() {
            return loginstatus;
        }

        public void setLoginstatus(boolean loginstatus) {
            this.loginstatus = loginstatus;
        }

        public int getUnreadlet() {
            return unreadlet;
        }

        public void setUnreadlet(int unreadlet) {
            this.unreadlet = unreadlet;
        }

        public boolean isIssign() {
            return issign;
        }

        public void setIssign(boolean issign) {
            this.issign = issign;
        }

        public int getSignnum() {
            return signnum;
        }

        public void setSignnum(int signnum) {
            this.signnum = signnum;
        }
    }
}
