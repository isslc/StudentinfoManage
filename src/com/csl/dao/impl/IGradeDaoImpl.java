package com.csl.dao.impl;

import com.csl.dao.IGradeDao;
import com.csl.javabean.Grade;
import com.csl.javabean.PageBean;
import com.csl.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/5 17:05
 */
public class IGradeDaoImpl implements IGradeDao {
    public ResultSet gradeList(Connection con, PageBean pageBean, Grade grade) throws Exception {
        StringBuffer sb = new StringBuffer("SELECT * FROM t_grade");
        if (grade != null && StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    public int gradeCount(Connection con, Grade grade) throws Exception {
        StringBuffer sb = new StringBuffer("SELECT count(*) as total FROM t_grade");
        if (StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
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
    public int gradeDelete(Connection con, String delIds) throws SQLException {
        String sql = "DELETE FROM t_grade WHERE id in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    @Override
    public int gradeAdd(Connection con, Grade grade) throws SQLException {
        String sql = "insert into t_grade values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        return pstmt.executeUpdate();
    }

    @Override
    public int gradeModify(Connection con, Grade grade) throws SQLException {
        String sql = "update t_grade set gradeName=?,gradeDesc=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        pstmt.setInt(3, grade.getId());
        return pstmt.executeUpdate();
    }
}
