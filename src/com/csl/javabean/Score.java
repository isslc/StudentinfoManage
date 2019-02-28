package com.csl.javabean;

/**
 * @author 陈思龙
 * @date 2018/12/8 11:25
 */
public class Score {
    private int scid;
    private int scstuid = -1;
    private int sccouid = -1;
    private String scscore;

    public Score() {
        super();
    }

    public Score(int scid, int scstuid, int sccouid, String scscore) {
        this.scid = scid;
        this.scstuid = scstuid;
        this.sccouid = sccouid;
        this.scscore = scscore;
    }

    public Score(int scstuid, int sccouid, String scscore) {
        this.scstuid = scstuid;
        this.sccouid = sccouid;
        this.scscore = scscore;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public int getScstuid() {
        return scstuid;
    }

    public void setScstuid(int scstuid) {
        this.scstuid = scstuid;
    }

    public int getSccouid() {
        return sccouid;
    }

    public void setSccouid(int sccouid) {
        this.sccouid = sccouid;
    }

    public String getScscore() {
        return scscore;
    }

    public void setScscore(String scscore) {
        this.scscore = scscore;
    }


}
