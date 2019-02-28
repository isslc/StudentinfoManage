package com.csl.javabean;

/**
 * @author 陈思龙
 * @date 2018/12/5 16:49
 */
public class Grade {
    private int id;//班级ID
    private String gradeName;//班级名
    private String gradeDesc;//班级描述

    public Grade() {
        super();
    }

    public Grade(int id, String gradeName, String gradeDesc) {
        this.id = id;
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }

    public Grade(String gradeName, String gradeDesc) {

        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeName='" + gradeName + '\'' +
                ", gradeDesc='" + gradeDesc + '\'' +
                '}';
    }
}
