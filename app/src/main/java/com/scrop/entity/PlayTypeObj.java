package com.scrop.entity;

import java.util.List;

/**
 * Created by Scrop on 2017/8/29.
 */

public class PlayTypeObj {

    private List<String> gameid;
    private List<PlayBean> play;

    public List<String> getGameid() {
        return gameid;
    }

    public void setGameid(List<String> gameid) {
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
         * playfamily : 普通玩法
         * playmembers : [{"playname":"五星直选","playcode":5111,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"五星通选","playcode":5121,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"前四直选","playcode":4111,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"后四直选","playcode":4211,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"前三直选","playcode":3111,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"前三组三","playcode":3131,
         * "playtext":"三位号码中应有两位号码相同，顺序不限号码都选中即中奖"},{"playname":"前三组六","playcode":3141,
         * "playtext":"三位号码需各不相同，顺序不限号码都选中即中奖"},{"playname":"中三直选","playcode":3211,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"中三组三","playcode":3231,
         * "playtext":"三位号码中应有两位号码相同，顺序不限号码都选中即中奖"},{"playname":"中三组六","playcode":3241,
         * "playtext":"三位号码需各不相同，顺序不限号码都选中即中奖"},{"playname":"后三直选","playcode":3311,
         * "playtext":"每位至少选择一个号码，号码和位置都对应开奖号即中奖"},{"playname":"后三组三","playcode":3331,
         * "playtext":"三位号码中应有两位号码相同，顺序不限号码都选中即中奖"},{"playname":"后三组六","playcode":3341,
         * "playtext":"三位号码需各不相同，顺序不限号码都选中即中奖"},{"playname":"前二直选","playcode":2111,
         * "playtext":"从万位、千位至少各选一个号码。号码和位置都对应开奖号即中奖"},{"playname":"前二组选","playcode":2151,
         * "playtext":"从0-9中至少选择两个号码，所选号码包含开奖号码前两位即中奖"},{"playname":"后二直选","playcode":2311,
         * "playtext":"从十位、个位至少各选一个号码。号码和位置都对应开奖号即中奖"},{"playname":"后二组选","playcode":2351,
         * "playtext":"从0-9中至少选择两个号码，所选号码包含开奖号码后两位即中奖"}]
         */

        private String playfamily;
        private List<PlaymembersBean> playmembers;

        public String getPlayfamily() {
            return playfamily;
        }

        public void setPlayfamily(String playfamily) {
            this.playfamily = playfamily;
        }

        public List<PlaymembersBean> getPlaymembers() {
            return playmembers;
        }

        public void setPlaymembers(List<PlaymembersBean> playmembers) {
            this.playmembers = playmembers;
        }

        public static class PlaymembersBean {
            /**
             * playname : 五星直选
             * playcode : 5111
             * playtext : 每位至少选择一个号码，号码和位置都对应开奖号即中奖
             */

            private String playname;
            private int playcode;
            private String playtext;

            public String getPlayname() {
                return playname;
            }

            public void setPlayname(String playname) {
                this.playname = playname;
            }

            public int getPlaycode() {
                return playcode;
            }

            public void setPlaycode(int playcode) {
                this.playcode = playcode;
            }

            public String getPlaytext() {
                return playtext;
            }

            public void setPlaytext(String playtext) {
                this.playtext = playtext;
            }

            @Override
            public String toString() {
                return "PlaymembersBean{" +
                        "playname='" + playname + '\'' +
                        ", playcode=" + playcode +
                        ", playtext='" + playtext + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "PlayBean{" +
                    "playfamily='" + playfamily + '\'' +
                    ", playmembers=" + playmembers +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PlayTypeObj{" +
                "gameid=" + gameid +
                ", play=" + play +
                '}';
    }
}
