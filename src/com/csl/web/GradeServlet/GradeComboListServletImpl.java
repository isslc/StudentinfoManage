package com.csl.web.GradeServlet;

import com.csl.dao.impl.IGradeDaoImpl;
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
 * @date 2018/12/6 10:10
 * 加载班级名称到学生搜索框
 */
public class GradeComboListServletImpl extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IGradeDaoImpl gradeDao = new IGradeDaoImpl();

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
            jsonObject.put("id", "");
            jsonObject.put("gradeName", "请选择...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con, null, null)));
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
