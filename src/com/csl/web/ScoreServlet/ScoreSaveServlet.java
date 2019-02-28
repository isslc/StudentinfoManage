package com.csl.web.ScoreServlet;

import com.csl.dao.impl.IScoreDaoImpl;
import com.csl.javabean.Score;
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
 * @date 2018/12/8 22:02
 */
public class ScoreSaveServlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IScoreDaoImpl scoreDao = new IScoreDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String stuId1 = request.getParameter("stuId");
        String couid1 = request.getParameter("couid");
        String scscore = request.getParameter("scscore");

        String scid = request.getParameter("scid");

        Score score = new Score(Integer.parseInt(stuId1), Integer.parseInt(couid1), scscore);

        if (StringUtil.isNotEmpty(scid)) {
            score.setScid(Integer.parseInt(scid));
        }
        Connection con = null;
        try {
            con = jdbcUtil.getCon();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            System.out.println(scid + "--------------------");
            if (StringUtil.isNotEmpty(scid)) {
                saveNums = scoreDao.scordModify(con, score);
            } else {
                saveNums = scoreDao.scordAdd(con, score);
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
