package com.csl.web.CourseServlet;

import com.csl.dao.impl.ICourseDaoImpl;
import com.csl.dao.impl.IScoreDaoImpl;
import com.csl.util.JdbcUtil;
import com.csl.util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author 陈思龙
 * @date 2018/12/8 20:59
 */
public class CourseDeleteServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ICourseDaoImpl courseDao = new ICourseDaoImpl();
    IScoreDaoImpl scoreDao = new IScoreDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds = request.getParameter("delIds");//获取删除的ID
        Connection con = null;

        try {
            con = jdbcUtil.getCon();
            JSONObject result = new JSONObject();
            String str[] = delIds.split(",");
            for (int i = 0; i < str.length; i++) {
                boolean f = courseDao.getCourseByGradeId(con, str[i]);
                if (f) {
                    result.put("errorIndex", i);
                    result.put("errorMsg", "有学生选择该课程，不能删除！");
                    ResponseUtil.write(response, result);
                    return;
                }
            }
            int delNums = courseDao.courseDelete(con, delIds);
            if (delNums > 0) {
                result.put("success", "true");
                result.put("delNums", delNums);
            } else {
                result.put("errorMsg", "删除失败");
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
