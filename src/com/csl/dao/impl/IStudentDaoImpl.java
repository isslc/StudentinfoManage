package com.csl.dao.impl;

import com.csl.dao.IStudentDao;
import com.csl.javabean.PageBean;
import com.csl.javabean.Student;
import com.csl.util.DateUtil;
import com.csl.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/6 9:11
 */
public class IStudentDaoImpl implements IStudentDao {
    @Override
    public ResultSet studentList(Connection con, PageBean pageBean, Student student, String bbirthday, String ebirthday) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
        if (student != null && StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" AND s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (student != null && StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" AND s.stuName like '%" + student.getStuName() + "%'");
        }
        if (student != null && StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" AND s.sex ='" + student.getSex() + "'");
        }
        if (student != null && student.getGradeId() != -1) {
            sb.append(" AND s.gradeId ='" + student.getGradeId() + "'");
        }
        if (student != null && StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" AND TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (student != null && StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" AND TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        if (student != null && pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public ResultSet studentList(Connection con) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student");

        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    @Override
    public int studentCount(Connection con, Student student, String bbirthday, String ebirthday) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    @Override
    public int studentDelete(Connection con, String delIds) throws SQLException {
        String sql = "delete from t_student where stuId in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    @Override
    public int studentAdd(Connection con, Student student) throws SQLException {
        String sql = "insert into t_student values(null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getGradeId());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getStuDesc());
        return pstmt.executeUpdate();

    }

    @Override
    public int studentModify(Connection con, Student student) throws SQLException {
        String sql = "update t_student set stuNo=?,stuName=?,sex=?,birthday=?,gradeId=?,email=?,stuDesc=? where stuId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getGradeId());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getStuDesc());
        pstmt.setInt(8, student.getStuId());
        return pstmt.executeUpdate();
    }

    @Override
    public boolean getStudentByGradeId(Connection con, String gradeId) throws SQLException {
        String sql = "select * from t_student where gradeId=?";
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
