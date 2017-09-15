package com.scrop.entity;

import java.util.List;

/**
 * Created by Scrop on 2017/7/25.
 */

public class LotterysObj {

    private List<LotteryBean> cp;

    public List<LotteryBean> getCp() {
        return cp;
    }

    public void setCp(List<LotteryBean> cp) {
        this.cp = cp;
    }

    public static class LotteryBean {
        /**
         * gameId : CQSSC
         * name : 重庆时时彩
         * logo : cqssc_logo
         */

        private String gameId;
        private String name;
        private String logo;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
