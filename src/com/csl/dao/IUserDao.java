package com.csl.dao;

import com.csl.javabean.User;

import java.sql.Connection;

/**
 * @author 陈思龙
 * @date 2018/12/5 11:10
 */
public interface IUserDao {
    /**
     * 用户登录j接口
     *
     * @return
     */
    public User login(Connection con, User user) throws Exception;

}
