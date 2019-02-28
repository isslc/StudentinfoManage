package com.csl.web.CourseServlet;

import com.csl.dao.impl.ICourseDaoImpl;
import com.csl.util.JdbcUtil;
import com.csl.util.JsonUtil;
import com.csl.util.ResponseUtil;
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
 * @date 2018/12/8 19:21
 */
public class CourseComboListServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    ICourseDaoImpl courseDao = new ICourseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        try {
            con = jdbcUtil.getCon();
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("couid", "");
            jsonObject.put("couname", "请选择...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(courseDao.courseList(con, null, null)));
            ResponseUtil.write(response, jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
