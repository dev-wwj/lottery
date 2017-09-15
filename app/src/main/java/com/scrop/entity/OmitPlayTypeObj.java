package com.scrop.entity;

import java.util.List;

/**
 * Created by Scrop on 2017/7/26.
 */

public class OmitPlayTypeObj {


    private List<OptBean> opt;

    public List<OptBean> getOpt() {
        return opt;
    }

    public void setOpt(List<OptBean> opt) {
        this.opt = opt;
    }

    public static class OptBean {
        /**
         * gamename : 重庆时时彩
         * gameid : CQSSC
         * play : [{"playtype":"定位选号","playname":["万位","千位","百位","十位","个位"]},{"playtype":"大小单双",
         * "playname":["后二星"]},{"playtype":"前二星组选","playname":["包号2码","包号3码","包号4码","包号5码",
         * "包号6码","包号7码"]},{"playtype":"后二星组选","playname":["包号2码","包号3码","包号4码","包号5码","包号6码",
         * "包号7码"]},{"playtype":"前三星直选","playname":["012路比","012路形态","号码","奇偶比","大小形态","大小比",
         * "单胆"]},{"playtype":"后三星直选","playname":["012路比","012路形态","号码","奇偶比","大小形态","大小比",
         * "单胆"]},{"playtype":"前三星组选3","playname":["单式","包号2码","包号3码","包号4码","包号5码","包号6码",
         * "包号7码","包号8码"]},{"playtype":"后三星组选3","playname":["单式","包号2码","包号3码","包号4码","包号5码",
         * "包号6码","包号7码","包号8码"]},{"playtype":"前三星组选6","playname":["包号3码","包号4码","包号5码","包号6码",
         * "包号7码","包号8码"]},{"playtype":"后三星组选6","playname":["包号3码","包号4码","包号5码","包号6码","包号7码",
         * "包号8码"]},{"playtype":"前二星直选","playname":["012路比","012路形态","大小比","单胆","号码","和值",
         * "奇偶比"]},{"playtype":"后二星直选","playname":["012路比","012路形态","大小比","单胆","号码","和值","奇偶比"]}]
         */

        private String gamename;
        private String gameid;
        private List<PlayBean> play;

        public String getGamename() {
            return gamename;
        }

        public void setGamename(String gamename) {
            this.gamename = gamename;
        }

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }

        public List<PlayBean> getPlay() {
            return play;
        }

        public void setPlay(List<PlayBean> play) {
            this.play = play;
        }

        public static class PlayBean {
            /**
             * playtype : 定位选号
             * playname : ["万位","千位","百位","十位","个位"]
             */

            private String playtype;
            private List<String> playname;

            public String getPlaytype() {
                return playtype;
            }

            public void setPlaytype(String playtype) {
                this.playtype = playtype;
            }

            public List<String> getPlayname() {
                return playname;
            }

            public void setPlayname(List<String> playname) {
                this.playname = playname;
            }

            @Override
            public String toString() {
                return "PlayBean{" +
                        "playtype='" + playtype + '\'' +
                        ", playname=" + playname +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "OptBean{" +
                    "gamename='" + gamename + '\'' +
                    ", gameid='" + gameid + '\'' +
                    ", play=" + play +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OmitPlayTypeObj{" +
                "opt=" + opt +
                '}';
    }
}
