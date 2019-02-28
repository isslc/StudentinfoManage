package com.csl.dao;

import com.csl.javabean.PageBean;
import com.csl.javabean.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/6 9:07
 */
public interface IStudentDao {
    /**
     * 显示学生
     *
     * @param con
     * @param pageBean
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     */
    public ResultSet studentList(Connection con, PageBean pageBean, Student student, String bbirthday, String ebirthday) throws Exception;

    /**
     * 查询学生总数
     *
     * @param con
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return
     */
    public int studentCount(Connection con, Student student, String bbirthday, String ebirthday) throws Exception;

    /**
     * 删除学生
     *
     * @param con
     * @param delIds
     * @return
     */
    public int studentDelete(Connection con, String delIds) throws SQLException;

    /**
     * 添加学生
     *
     * @param con
     * @param student
     * @return
     */
    public int studentAdd(Connection con, Student student) throws SQLException;

    /**
     * 修改学生
     *
     * @param con
     * @param student
     * @return
     */
    public int studentModify(Connection con, Student student) throws SQLException;

    /**
     * 通过ID查询学生是否存在
     *
     * @param con
     * @param gradeId
     * @return
     */
    public boolean getStudentByGradeId(Connection con, String gradeId) throws SQLException;
}
