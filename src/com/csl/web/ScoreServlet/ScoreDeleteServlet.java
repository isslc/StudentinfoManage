package com.csl.web.ScoreServlet;

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
 * @date 2018/12/8 21:46
 */
public class ScoreDeleteServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
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
            con = jdbcUtil.getCon();//打开数据链接
            JSONObject result = new JSONObject();
            int delNums = scoreDao.scordDelete(con, delIds);//传入删除的ID
            if (delNums > 0) {
                result.put("success", "true");//返回到前台结果成功
                result.put("delNums", delNums);//返回到前台结果删除条数
            } else {
                result.put("errorMsg", "删除失败");
            }
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
