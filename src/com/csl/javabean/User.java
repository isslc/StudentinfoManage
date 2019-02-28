package com.csl.javabean;

/**
 * 用户实体
 *
 * @author 陈思龙
 * @date 2018/12/5 11:12
 */
public class User {
    private int id;//用户ID
    private String userName;//用户名
    private String password;//用户密码

    public User() {
        super();
    }

    public User(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
