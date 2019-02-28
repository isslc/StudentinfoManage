package com.csl.web.StudentServlet;

import com.csl.dao.impl.IStudentDaoImpl;
import com.csl.javabean.Student;
import com.csl.util.DateUtil;
import com.csl.util.JdbcUtil;
import com.csl.util.ResponseUtil;
import com.csl.util.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author 陈思龙
 * @date 2018/12/7 20:51
 */
public class StudentSaveServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IStudentDaoImpl studentDao = new IStudentDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");

        Student student = null;
        try {
            student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
                    Integer.parseInt(gradeId), email, stuDesc);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }
        Connection con = null;
        try {
            con = jdbcUtil.getCon();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            if (StringUtil.isNotEmpty(stuId)) {
                saveNums = studentDao.studentModify(con, student);
            } else {
                saveNums = studentDao.studentAdd(con, student);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "保存失败");
            }
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
