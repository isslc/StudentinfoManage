package com.csl.web.GradeServlet;

import com.csl.dao.impl.IGradeDaoImpl;
import com.csl.javabean.Grade;
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
 * @date 2018/12/5 22:20
 */
public class GradeSaveSerVlet extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IGradeDaoImpl gradeDao = new IGradeDaoImpl();
    JSONObject result = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//接收是转换成utf-8
        String gradeName = request.getParameter("gradeName");
        String gradeDesc = request.getParameter("gradeDesc");
        String id = request.getParameter("id");
        Grade grade = new Grade(gradeName, gradeDesc);
        if (StringUtil.isNotEmpty(id)) {//如果id不为空
            grade.setId(Integer.parseInt(id));//强转Integer.parseInt
        }
        Connection con = null;
        try {
            con = jdbcUtil.getCon();//打开数据链接
            JSONObject result = new JSONObject();
            int saveNums = 0;
            if (StringUtil.isNotEmpty(id)) {//如果ID不为空
                saveNums = gradeDao.gradeModify(con, grade);//修改
            } else {
                saveNums = gradeDao.gradeAdd(con, grade);//添加
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
