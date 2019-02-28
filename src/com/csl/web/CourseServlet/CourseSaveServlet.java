package com.csl.web.CourseServlet;

import com.csl.dao.impl.ICourseDaoImpl;
import com.csl.javabean.Course;
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
 * @date 2018/12/8 21:17
 */
public class CourseSaveServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ICourseDaoImpl courseDao = new ICourseDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//接收是转换成utf-8
        String couname = request.getParameter("couname");
        String counteacher = request.getParameter("counteacher");
        String coudesc = request.getParameter("coudesc");

        String id = request.getParameter("id");

        Course course = new Course(couname, counteacher, coudesc);
        if (StringUtil.isNotEmpty(id)) {//如果id不为空
            course.setCouid(Integer.parseInt(id));//强转Integer.parseInt
        }
        Connection con = null;
        try {
            con = jdbcUtil.getCon();//打开数据链接
            JSONObject result = new JSONObject();
            int saveNums = 0;
            if (StringUtil.isNotEmpty(id)) {//如果ID不为空
                saveNums = courseDao.courseModify(con, course);//修改
            } else {
                saveNums = courseDao.courseAdd(con, course);//添加
            }

            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("success", "true");
                result.put("errorMsg", "添加失败");
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
