package com.csl.web;

import com.csl.dao.impl.IUserDaoImpl;
import com.csl.javabean.User;
import com.csl.util.JdbcUtil;
import com.csl.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author 陈思龙
 * @date 2018/12/5 13:48
 * 登录逻辑接口实现
 */
public class LoginServletImpl extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    IUserDaoImpl userDao = new IUserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        request.setAttribute("userName", userName);
        request.setAttribute("password", password);
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            request.setAttribute("error", "用户名或密码为空");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        User user = new User(userName, password);
        Connection conn = null;
        try {
            conn = jdbcUtil.getCon();
            User currentUser = userDao.login(conn, user);
            if (currentUser == null) {
                request.setAttribute("error", "用户名或密码错误");
                //服务器跳转
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                //权限验证 获取Session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", currentUser);
                //用户名密码正确 客户端跳转
                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                jdbcUtil.closeCon(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
