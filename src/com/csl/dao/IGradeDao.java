package com.csl.dao;

import com.csl.javabean.Grade;
import com.csl.javabean.PageBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/5 17:03
 */
public interface IGradeDao {
    /**
     * 获取班级记录存在数组
     *
     * @param con
     * @param pageBean
     * @param grade
     * @return
     * @throws SQLException
     */
    public ResultSet gradeList(Connection con, PageBean pageBean, Grade grade) throws SQLException, Exception;

    /**
     * 获取记录总条数
     *
     * @param con
     * @param grade
     * @return
     */
    public int gradeCount(Connection con, Grade grade) throws SQLException, Exception;

    public int gradeDelete(Connection con, String delIds) throws SQLException;

    public int gradeAdd(Connection con, Grade grade) throws SQLException;

    public int gradeModify(Connection con, Grade grade) throws SQLException;
}
