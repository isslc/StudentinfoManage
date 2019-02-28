package com.csl.dao;

import com.csl.javabean.Course;
import com.csl.javabean.PageBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/8 11:07
 */
public interface ICourseDao {
    public ResultSet courseList(Connection con, PageBean pageBean, Course course) throws SQLException, Exception;

    /**
     * 获取记录总条数
     *
     * @param con
     * @param course
     * @return
     */
    public int courseCount(Connection con, Course course) throws SQLException, Exception;

    public int courseDelete(Connection con, String course) throws SQLException;

    public int courseAdd(Connection con, Course course) throws SQLException;

    public int courseModify(Connection con, Course course) throws SQLException;
}
