package main.dao;

import main.Utils.DBUtil;
import main.entity.Competitor;
import main.entity.Judge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class competitorDao {
    public List<Competitor> getAllCom(){
        List<Competitor> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from competitor";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Competitor competitor = new Competitor();
                competitor.setCompetitorAccountNum(rs.getString("competitorAccountNum"));
                competitor.setCompetitorPassword(rs.getString("competitorPassword"));
                competitor.setCompetitorName(rs.getString("competitorName"));
                competitor.setProjectId(rs.getString("projectId"));
                competitor.setCompetitorVotesNum(rs.getInt("competitorVotesNum"));
                competitor.setCompetitorRight(rs.getInt("competitorRight"));
                list.add(competitor);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean addCom(Competitor com){
        String sql = "insert into competitor(competitorName,competitorAccountNum,competitorPassword,projectId,competitorVotesNum,competitorRight) values (?,?,?,?,?,?)";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,com.getCompetitorName());
            stmt.setString(2,com.getCompetitorAccountNum());
            stmt.setString(3,com.getCompetitorPassword());
            stmt.setString(4,com.getProjectId());
            stmt.setInt(5,0);
            stmt.setInt(6,1);
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateCom(Competitor com){
        String sql = "update competitor set competitorName=?,competitorPassword=? where competitorAccountNum=?";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,com.getCompetitorName());
            stmt.setString(2,com.getCompetitorPassword());
            stmt.setString(3,com.getCompetitorAccountNum());
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean voteCom(int competitorVotesNum,String competitorAccountNum){
        Connection connection = DBUtil.getConnection();

        String sql_com = "update competitor set competitorVotesNum = ? where competitorAccountNum = ?";
        try {
            PreparedStatement statement_com = connection.prepareStatement(sql_com);
            statement_com.setInt(1,competitorVotesNum);
            statement_com.setString(2,competitorAccountNum);
            int count_com = statement_com.executeUpdate();
            statement_com.close();
            return count_com>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteCom(String competitorAccountNum){
        String sql = "delete from competitor where competitorAccountNum = ?";
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,competitorAccountNum);
            int count = 0;
            count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Competitor selectCompetiorByComAcc(String competitorAccountNum){
        Connection connection = DBUtil.getConnection();
        String sql = "select * from competitor where competitorAccountNum = " + competitorAccountNum;
        Competitor competitor = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                competitor = new Competitor();
                competitor.setCompetitorAccountNum(resultSet.getString("competitorAccountNum"));
                competitor.setCompetitorName(resultSet.getString("competitorName"));
                competitor.setCompetitorPassword(resultSet.getString("competitorPassword"));
                competitor.setProjectId(resultSet.getString("projectId"));
                competitor.setCompetitorVotesNum(0);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return competitor;
    }
    public boolean endCompetitor(String projectId){
        String sql_com = "update competitor set competitorRight = ? where projectId = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement2 = connection.prepareStatement(sql_com);
            statement2.setInt(1,0);
            statement2.setString(2,projectId);
            int count = statement2.executeUpdate();
            statement2.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
