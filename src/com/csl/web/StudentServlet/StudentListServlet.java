package com.csl.web.StudentServlet;

import com.csl.dao.impl.IStudentDaoImpl;
import com.csl.javabean.PageBean;
import com.csl.javabean.Student;
import com.csl.util.JdbcUtil;
import com.csl.util.JsonUtil;
import com.csl.util.ResponseUtil;
import com.csl.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author 陈思龙
 * @date 2018/12/6 9:18
 */
public class StudentListServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IStudentDaoImpl studentDao = new IStudentDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String bbirthday = request.getParameter("bbirthday");
        String ebirthday = request.getParameter("ebirthday");
        String gradeId = request.getParameter("gradeId");

        Student student = new Student();
        if (stuNo != null) {
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            if (StringUtil.isNotEmpty(gradeId)) {
                student.setGradeId(Integer.parseInt(gradeId));
            }
        }

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try {
            con = jdbcUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean, student, bbirthday, ebirthday));
            int total = studentDao.studentCount(con, student, bbirthday, ebirthday);
            result.put("rows", jsonArray);
            result.put("total", total);
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
