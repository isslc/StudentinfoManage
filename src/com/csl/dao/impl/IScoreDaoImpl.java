package com.csl.dao.impl;

import com.csl.dao.IScoreDao;
import com.csl.javabean.PageBean;
import com.csl.javabean.Score;
import com.csl.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈思龙
 * @date 2018/12/8 11:31
 */
public class IScoreDaoImpl implements IScoreDao {
    @Override
    public ResultSet scordList(Connection con, PageBean pageBean, Score score, String scscoure, String scscoure1) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student s,t_score g,t_course c where s.stuid=g.scstuid and c.couid=g.sccouid");
        if (score != null && StringUtil.isNotEmpty(scscoure)) {
            sb.append(" AND g.scscore >" + Integer.parseInt(scscoure) + "");
        }
        if (score != null && StringUtil.isNotEmpty(scscoure1)) {
            sb.append(" AND g.scscore < " + Integer.parseInt(scscoure1) + "");
        }
        if (score != null && score.getSccouid() != -1) {
            sb.append(" AND c.couid =" + score.getSccouid() + "");
        }
        if (score != null && score.getScstuid() != -1) {
            sb.append(" AND s.stuId =" + score.getScstuid() + "");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    @Override
    public int scordCount(Connection con, Score score, String scscoure, String scscoure1) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_score g,t_course c  where s.stuid=g.scstuid and c.couid=g.sccouid");
        if (score != null && StringUtil.isNotEmpty(scscoure)) {
            sb.append(" AND g.scscore >" + Integer.parseInt(scscoure) + "");
        }
        if (score != null && StringUtil.isNotEmpty(scscoure1)) {
            sb.append(" AND g.scscore < " + Integer.parseInt(scscoure1) + "");
        }
        if (score != null && score.getSccouid() != -1) {
            sb.append(" AND c.couid ='" + score.getSccouid() + "'");
        }
        if (score != null && score.getScstuid() != -1) {
            sb.append(" AND s.stuId ='" + score.getScstuid() + "'");
        }

        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    @Override
    public int scordDelete(Connection con, String delIds) throws SQLException {
        String sql = "delete from t_score where scid in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    @Override
    public int scordAdd(Connection con, Score score) throws SQLException {
        String sql = "insert into t_score values(null,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, score.getScstuid());
        pstmt.setInt(2, score.getSccouid());
        pstmt.setString(3, score.getScscore());
        return pstmt.executeUpdate();
    }

    @Override
    public int scordModify(Connection con, Score score) throws SQLException {
        String sql = "update t_score set scstuid=?,sccouid=?,scscore=? where scid=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, score.getScstuid());
        pstmt.setInt(2, score.getSccouid());
        pstmt.setString(3, score.getScscore());
        pstmt.setInt(4, score.getScid());
        return pstmt.executeUpdate();
    }

    @Override
    public boolean getScordByGradeId(Connection con, String scoreId) throws SQLException {
        return false;
    }
}
