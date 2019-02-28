package com.csl.web.GradeServlet;

import com.csl.dao.impl.IGradeDaoImpl;
import com.csl.javabean.Grade;
import com.csl.javabean.PageBean;
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
 * @date 2018/12/5 16:52
 * 班级管理读取数据到页面
 */
public class GradeListServletImpl extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IGradeDaoImpl gradeDao = new IGradeDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String gradeName = request.getParameter("gradeName");
        if (gradeName == null) {
            gradeName = "";
        }
        Grade grade = new Grade();
        grade.setGradeName(gradeName);
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try {
            con = jdbcUtil.getCon();//打开数据链接
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, pageBean, grade));//获取到的数据转换陈JSONArray
            int total = gradeDao.gradeCount(con, grade);//获取总条数
            result.put("rows", jsonArray);//返回到前台结果
            result.put("total", total);//返回到前台结果
            ResponseUtil.write(response, result);//输出到页面

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
