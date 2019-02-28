package com.csl.dao;

import com.csl.javabean.PageBean;
import com.csl.javabean.Score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/8 11:30
 */
public interface IScoreDao {
    public ResultSet scordList(Connection con, PageBean pageBean, Score score, String scscoure, String scscoure1) throws Exception;

    public int scordCount(Connection con, Score score, String scscoure, String scscoure1) throws Exception;

    public int scordDelete(Connection con, String delIds) throws SQLException;

    public int scordAdd(Connection con, Score score) throws SQLException;

    public int scordModify(Connection con, Score score) throws SQLException;

    public boolean getScordByGradeId(Connection con, String scoreId) throws SQLException;
}
