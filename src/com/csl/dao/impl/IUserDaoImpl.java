package com.csl.dao.impl;

import com.csl.dao.IUserDao;
import com.csl.javabean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 陈思龙
 * @date 2018/12/5 14:41
 */
public class IUserDaoImpl implements IUserDao {
    @Override
    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from t_user where userName=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}
