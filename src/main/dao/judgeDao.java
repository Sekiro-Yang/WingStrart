package main.dao;

import java.util.List;
import main.Utils.DBUtil;
import main.entity.Judge;
import main.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class judgeDao {

    public List<Judge> getAllJudge(){
        List<Judge> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from judge";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Judge judge = new Judge();
                judge.setJudgeAccountNum(rs.getString("judgeAccountNum"));
                judge.setJudgeName(rs.getString("judgeName"));
                judge.setJudgePassword(rs.getString("judgePassword"));
                judge.setProjectId(rs.getString("projectId"));
                judge.setProjectName(rs.getString("projectName"));
                judge.setJudgeVotesNum(rs.getInt("judgeVotesNum"));
                judge.setJudgeRight(rs.getInt("judgeRight"));
                list.add(judge);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean addJudge(Judge judge){
        String sql = "insert into judge(judgeName,judgeAccountNum,judgePassword,projectId,projectName,judgeVotesNum,judgeRight) values (?,?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,judge.getJudgeName());
            stmt.setString(2, judge.getJudgeAccountNum());
            stmt.setString(3, judge.getJudgePassword());
            stmt.setString(4,judge.getProjectId());
            stmt.setString(5, judge.getProjectName());
            stmt.setInt(6,1);
            stmt.setInt(7,1);
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateJud(Judge judge){
        String sql = "update judge set judgeName=?,judgePassword=? where judgeAccountNum=?";
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,judge.getJudgeName());
            stmt.setString(2,judge.getJudgePassword());
            stmt.setString(3,judge.getJudgeAccountNum());
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean voteJud(int judgeVotesNum,String judgeAccountNum){
        Connection connection = DBUtil.getConnection();
        String sql_jud = "update judge set judgeVotesNum = ? where judgeAccountNum = ?";
        try {
            PreparedStatement statement_judge = connection.prepareStatement(sql_jud);
            statement_judge.setInt(1,judgeVotesNum);
            statement_judge.setString(2,judgeAccountNum);
            int count_jud = statement_judge.executeUpdate();
            statement_judge.close();
            return count_jud>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteJud(String judgeAccountNum){
        String sql = "delete from judge where judgeAccountNum = ?";
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,judgeAccountNum);
            int count = 0;
            count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Judge selectJudgeByJudAcc(String judgeAccountNum){
        Connection connection = DBUtil.getConnection();
        String sql = "select * from judge where judgeAccountNum = " + judgeAccountNum;
        Judge judge = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                judge = new Judge();
                judge.setJudgeAccountNum(resultSet.getString("judgeAccountNum"));
                judge.setJudgeName(resultSet.getString("judgeName"));
                judge.setJudgePassword(resultSet.getString("judgePassword"));
                judge.setProjectId(resultSet.getString("projectId"));
                judge.setProjectName(resultSet.getString("projectName"));
                judge.setJudgeVotesNum(resultSet.getInt("judgeVotesNum"));
                judge.setJudgeRight(resultSet.getInt("judgeRight"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return judge;
    }

    public boolean endJudge(String projectId){
        String sql_jud = "update judge set judgeRight = ? where projectId = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement1 = connection.prepareStatement(sql_jud);
            statement1.setInt(1,0);
            statement1.setString(2,projectId);
            int count = statement1.executeUpdate();
            statement1.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Judge getJudgeByJAcc(String judgeAccountNum){
        Judge judge = new Judge();
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        try {
            stmt = conn.prepareStatement("SELECT * FROM judge WHERE judgeAccountNum = ?");
            stmt.setString(1,judgeAccountNum);//传入参数
            ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是username对应的所有参数集合
            if (!resultSet.next()){//判断这个集合是否有值
                System.out.println("找不到对象");
            }
            //获得数据库中的数据
            judge.setJudgeName(resultSet.getString("judgeName"));
            judge.setJudgeAccountNum(resultSet.getString("judgeAccountNum"));
            judge.setJudgePassword(resultSet.getString("judgePassword"));
            judge.setProjectId(resultSet.getString("projectId"));
            judge.setProjectName(resultSet.getString("projectName"));
            judge.setJudgeVotesNum(resultSet.getInt("judgeVotesNum"));
            judge.setJudgeRight(resultSet.getInt("judgeRight"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return judge;
    } //通过judgeAccountNum返回judge

    public boolean choJudge_sinPro(int judgeRight,String judgeAccountNum){
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        if (judgeRight==1){
            judgeRight=0;
        }else{
            judgeRight=1;
        }
        try {
            stmt = conn.prepareStatement("update judge set judgeRight = ? where judgeAccountNum = ?");
            stmt.setInt(1,judgeRight);
            stmt.setString(2,judgeAccountNum);//传入参数
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //在管理页面改变评委权限

    public boolean choJudge_next(int judgeRight,String judgeAccountNum){
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        if (judgeRight==1){
            judgeRight=0;
        }else{
            judgeRight=1;
        }
        try {
            stmt = conn.prepareStatement("update judge set judgeRight=? where judgeAccountNum=?");
            stmt.setInt(1,judgeRight);
            stmt.setString(2,judgeAccountNum);//传入参数
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //选择进入下一轮比赛的评委

    public boolean giveVoteNum(String projectId){
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        try {
            stmt = conn.prepareStatement("update judge set judgeVotesNum=? where projectId=?");
            stmt.setInt(1,1);
            stmt.setString(2,projectId);//传入参数
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
