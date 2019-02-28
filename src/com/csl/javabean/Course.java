package com.csl.javabean;

/**
 * @author 陈思龙
 * @date 2018/12/8 11:04
 */

public class Course {
    private int couid;
    private String couname;
    private String counteacher;
    private String coudesc;

    public Course() {
        super();
    }

    public Course(int couid, String couname, String counteacher, String coudesc) {
        this.couid = couid;
        this.couname = couname;
        this.counteacher = counteacher;
        this.coudesc = coudesc;
    }

    public Course(String couname, String counteacher, String coudesc) {
        this.couname = couname;
        this.counteacher = counteacher;
        this.coudesc = coudesc;
    }

    public int getCouid() {
        return couid;
    }

    public void setCouid(int couid) {
        this.couid = couid;
    }

    public String getCouname() {
        return couname;
    }

    public void setCouname(String couname) {
        this.couname = couname;
    }

    public String getCounteacher() {
        return counteacher;
    }

    public void setCounteacher(String counteacher) {
        this.counteacher = counteacher;
    }

    public String getCoudesc() {
        return coudesc;
    }

    public void setCoudesc(String coudesc) {
        this.coudesc = coudesc;
    }
}
