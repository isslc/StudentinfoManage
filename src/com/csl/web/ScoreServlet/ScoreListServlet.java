package com.csl.web.ScoreServlet;

import com.csl.dao.impl.IScoreDaoImpl;
import com.csl.javabean.PageBean;
import com.csl.javabean.Score;
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
 * @date 2018/12/8 11:26
 */
public class ScoreListServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IScoreDaoImpl scoreDao = new IScoreDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String scid = request.getParameter("scid");
        String scstuid = request.getParameter("stuId");
        String sccouid = request.getParameter("couid");
        String scscore = request.getParameter("scscore");

        String scscoure = request.getParameter("scscoure");
        String scscoure1 = request.getParameter("scscoure1");

        Score score = new Score();
        score.setScscore(scscore);
        if (StringUtil.isNotEmpty(scstuid)) {
            score.setScstuid(Integer.parseInt(scstuid));
        }
        if (StringUtil.isNotEmpty(sccouid)) {
            score.setSccouid(Integer.parseInt(sccouid));
        }
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try {
            con = jdbcUtil.getCon();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(scoreDao.scordList(con, pageBean, score, scscoure, scscoure1));
            int total = scoreDao.scordCount(con, score, scscoure, scscoure1);
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
