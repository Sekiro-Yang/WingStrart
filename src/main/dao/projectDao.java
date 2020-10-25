package main.dao;

import main.Utils.DBUtil;
import main.entity.Founder;
import main.entity.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class projectDao {
    public List<Project> getAllProject(){
        List<Project> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from project";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Project project = new Project();
                project.setFounderId(rs.getString("founderId"));
                project.setFounderName(rs.getString("founderName"));
                project.setProjectId(rs.getString("projectId"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectRight(rs.getInt("projectRight"));
                project.setProjectSheave(rs.getInt("projectSheave"));
                project.setEndSheave(rs.getInt("endSheave"));
                list.add(project);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    } //获取所有项目信息

    public void createProject(String projectId,String projectName,int endSheave,String founderId, String founderName){
        //创建必须
        projectDao projectDao = new projectDao();
        founderDao founderDao = new founderDao();
        Project project = new Project();
        //信息补充
        project.setFounderId(founderId);
        project.setFounderName(founderName);
        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setProjectSheave(endSheave);
        //获取founder的projectNum
        Founder founder = founderDao.getFounderByFId(founderId);
        int projectNum = founder.getProjectNum();
        //将信息project加入数据库
        projectDao.addProject(project);
        //founder的projectNum++
        founderDao.addProject(projectNum,founderId);
    }//新建项目

    public Project getProjectByPId(String projectId){
        Project project = new Project();
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        try {
            stmt = conn.prepareStatement("SELECT * FROM project WHERE projectId = ?");
            stmt.setString(1,projectId);//传入参数
            ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是username对应的所有参数集合
            if (!resultSet.next()){//判断这个集合是否有值
                System.out.println("找不到对象");
            }
            //获得数据库中的数据
            project.setFounderId(resultSet.getString("founderId"));
            project.setProjectId(resultSet.getString("projectId"));
            project.setProjectName(resultSet.getString("projectName"));
            project.setFounderName(resultSet.getString("founderName"));
            project.setProjectSheave(resultSet.getInt("projectSheave"));
            project.setProjectRight(resultSet.getInt("projectRight"));
            project.setEndSheave(resultSet.getInt("endSheave"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return project;
    }//通过projectId找到project

    public boolean addProject(Project project){
        String sql = "insert into project(founderId,founderName,projectId,projectName,projectSheave,projectRight,endSheave) values (?,?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,project.getFounderId());
            stmt.setString(2,project.getFounderName());
            stmt.setString(3,project.getProjectId());
            stmt.setString(4, project.getProjectName());
            stmt.setInt(5,1);
            stmt.setInt(6,1);
            stmt.setInt(7,project.getProjectSheave());
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //增项目

    public boolean deleteProject(String projectId){
        String sql_pro = "delete from project where projectId = ?";
        String sql_jud = "delete from judge where projectId = ?";
        String sql_com = "delete from competitor where projectId = ?";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql_pro);
            statement.setString(1,projectId);
            statement.execute();
            PreparedStatement statement1 = connection.prepareStatement(sql_jud);
            statement1.setString(1,projectId);
            statement1.execute();
            PreparedStatement statement2 = connection.prepareStatement(sql_com);
            statement2.setString(1,projectId);
            statement2.execute();
            int count = 0;
            count = statement.executeUpdate();
            statement.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //删项目

    public boolean updateProject(String projectId,String projectName){
        String sql = "update project set projectName=? where projectId=?";
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,projectName);
            statement.setString(2,projectId);
            int count = statement.executeUpdate();
            statement.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //改项目

    public Project selectProjectByProId(String projectId){
        Connection connection = DBUtil.getConnection();
        String sql = "select * from project where projectId = " + projectId;
        Project project = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                project = new Project();
                project.setProjectId(resultSet.getString("projectId"));
                project.setProjectName(resultSet.getString("projectName"));
                project.setFounderId(resultSet.getString("founderId"));
                project.setFounderName(resultSet.getString("founderName"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return project;
    } //查项目

    public int judgeLegal(String projectName,int endSheave){
        if (projectName.length() == 0){
            return 1;//若填写信息有空缺返回0
        }else if (endSheave<=0){
            return 2;//若最终轮数填写<=0，则返回1
        }else{
            return 3;//若一切正常则返回2
        }

    }//判断创建信息是否正确

    public boolean updJudgeRight(String projectId){
        //让所有的JudgeRight都变为0（默认不可以参与投票）
        String sql = "update judge set judgeRight = ? where projectId = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,projectId);
            int count = preparedStatement.executeUpdate();
            preparedStatement.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //更新裁判权限

    public boolean nextSheave(String projectId,int projectSheave){
        //设置projectSheave
        projectDao projectDao = new projectDao();
        String sql = "update project set projectSheave = ? where projectId = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,projectSheave);
            preparedStatement.setString(2,projectId);

            int count = preparedStatement.executeUpdate();
            preparedStatement.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //进入下一轮

    public boolean changeRight(String projectId){
        String sql = "update judge set judgeRight = ? where projectId = ?";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,projectId);
            int count = preparedStatement.executeUpdate();
            preparedStatement.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    } //选择裁判进入下一轮

    public boolean endProject(String projectId,int projectRight){
        judgeDao judgeDao = new judgeDao();
        competitorDao competitorDao = new competitorDao();
        String sql_pro = "update project set projectRight = ? where projectId = ?";
        String sql_jud = "update judge set judgeRight = ? where projectId = ?";
        String sql_com = "update competitor set competitorRight = ? where projectId = ?";
        if (projectRight == 0){
            projectRight = 1;
        }else{
            projectRight = 0;
        }
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement1 = connection.prepareStatement(sql_pro);
            statement1.setInt(1,projectRight);
            statement1.setString(2,projectId);
            statement1.execute();
            PreparedStatement statement2 = connection.prepareStatement(sql_jud);
            statement2.setInt(1,0);
            statement2.setString(2,projectId);
            statement2.execute();
            PreparedStatement statement3 = connection.prepareStatement(sql_com);
            statement3.setInt(1,0);
            statement3.setString(2,projectId);
            statement3.execute();
            statement1.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        judgeDao.endJudge(projectId);
        competitorDao.endCompetitor(projectId);
        return false;
    } //结束项目
}
