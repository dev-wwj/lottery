package com.scrop.entity;

/**
 * Created by Scrop on 2017/8/30.
 */

public class PlayTypeCheckedBean {
    private String gameId;     //彩种
    private String playFimaly;  //玩法分类
    private String playFimalySimplified; //玩法分类简写
    private String playName;   // 玩法名称
    private String playIntro;   // 玩法介绍

    public PlayTypeCheckedBean() {
    }

    public PlayTypeCheckedBean(String gameId, String playFimaly, String playName, String
            playIntro, String playFimalySimplified) {
        this.gameId = gameId;
        this.playFimaly = playFimaly;
        this.playName = playName;
        this.playIntro = playIntro;
        this.playFimalySimplified = playFimalySimplified;
    }

    public String getPlayFimalySimplified() {
        return playFimalySimplified;
    }

    public void setPlayFimalySimplified(String playFimalySimplified) {
        this.playFimalySimplified = playFimalySimplified;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayFimaly() {
        return playFimaly;
    }

    public void setPlayFimaly(String playFimaly) {
        this.playFimaly = playFimaly;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getPlayIntro() {
        return playIntro;
    }

    public void setPlayIntro(String playIntro) {
        this.playIntro = playIntro;
    }

    @Override
    public String toString() {
        return "PlayTypeCheckedBean{" +
                "gameId='" + gameId + '\'' +
                ", playFimaly='" + playFimaly + '\'' +
                ", playFimalySimplified='" + playFimalySimplified + '\'' +
                ", playName='" + playName + '\'' +
                ", playIntro='" + playIntro + '\'' +
                '}';
    }
}
