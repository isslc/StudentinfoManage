package com.csl.javabean;

/**
 * @author 陈思龙
 * @date 2018/12/5 17:00
 */
public class PageBean {
    private int page;//第几页
    private int rows;//每一页记录数
    private int start;//启始页

    public PageBean(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return (page - 1) * rows;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
