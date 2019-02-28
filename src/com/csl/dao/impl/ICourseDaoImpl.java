package com.csl.dao.impl;

import com.csl.dao.ICourseDao;
import com.csl.javabean.Course;
import com.csl.javabean.PageBean;
import com.csl.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/8 11:09
 */
public class ICourseDaoImpl implements ICourseDao {
    @Override
    public ResultSet courseList(Connection con, PageBean pageBean, Course course) throws SQLException, Exception {
        StringBuffer sb = new StringBuffer("SELECT * FROM t_course");
        if (course != null && StringUtil.isNotEmpty(course.getCouname())) {
            sb.append(" and couname like '%" + course.getCouname() + "%'");
        }
        if (course != null && StringUtil.isNotEmpty(course.getCounteacher())) {
            sb.append(" and counteacher like '%" + course.getCounteacher() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    @Override
    public int courseCount(Connection con, Course course) throws SQLException, Exception {
        StringBuffer sb = new StringBuffer("SELECT count(*) as total FROM t_course");
        if (StringUtil.isNotEmpty(course.getCouname())) {
            sb.append(" and counteacher like '%" + course.getCouname() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    @Override
    public int courseDelete(Connection con, String course) throws SQLException {
        String sql = "DELETE FROM t_course WHERE couid in(" + course + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    @Override
    public int courseAdd(Connection con, Course course) throws SQLException {
        String sql = "insert into t_course values(null,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, course.getCouname());
        pstmt.setString(2, course.getCounteacher());
        pstmt.setString(3, course.getCoudesc());
        return pstmt.executeUpdate();
    }

    @Override
    public int courseModify(Connection con, Course course) throws SQLException {
        String sql = "update t_course set couname=?,counteacher=?,coudesc=? where couid=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, course.getCouname());
        pstmt.setString(2, course.getCounteacher());
        pstmt.setString(3, course.getCoudesc());
        pstmt.setInt(4, course.getCouid());
        return pstmt.executeUpdate();
    }


    public boolean getCourseByGradeId(Connection con, String gradeId) throws SQLException {
        String sql = "select * from t_score where sccouid=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, gradeId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }
}