package main.dao;

import main.Utils.DBUtil;
import main.entity.Founder;
import main.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class founderDao {
    public String getCode(String founderId){
        String sql = "select code from founder where founderId = ?";
        String code = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,founderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                System.out.println("不存在");
            }
            code = resultSet.getString("code");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return code;
    }

    public int loginJudge(String founderId, String password){
        //设置必须
        String input_founderId = founderId;
        String input_password = password;
        Founder founder = new Founder();

        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        try {
            stmt = conn.prepareStatement("SELECT * FROM founder WHERE founderId = ?");
            stmt.setString(1,input_founderId);//传入参数
            ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是username对应的所有参数集合
            if (!resultSet.next()){//判断这个集合是否有值
                return -1;//如果没有则返回-1
            }
            //获得数据库中的数据
            founder.setFounderId(resultSet.getString("founderId"));
            founder.setPassword(resultSet.getString("password"));
            founder.setFounderName(resultSet.getString("founderName"));
            founder.setSex(resultSet.getString("sex"));
            founder.setAge(resultSet.getInt("age"));
            founder.setEmail(resultSet.getString("email"));

            if (input_password.length() == 0){
                return -2;//如果密码有空缺则返回-2
            }
            if (founder.getPassword().equals(input_password)){//密码正确，登录成功
                return 1;
            }else{//密码错误，登录失败
                return 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -3;
        }
    }//判断用户登录条件是否合法

    public int homejudge(String founderId){
        //设置必须
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        Founder founder = new Founder();
        try {
            stmt = conn.prepareStatement("SELECT * FROM founder WHERE founderId = ?");
            stmt.setString(1,founderId);//传入参数
            ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是username对应的所有参数集合
            if (!resultSet.next()){//判断这个集合是否有值
                return -1;//如果没有则返回-1
            }
            //获得数据库中的数据
            founder.setFounderId(resultSet.getString("founderId"));
            founder.setPassword(resultSet.getString("password"));
            founder.setFounderName(resultSet.getString("founderName"));
            founder.setSex(resultSet.getString("sex"));
            founder.setAge(resultSet.getInt("age"));
            founder.setEmail(resultSet.getString("email"));
        } catch (SQLException throwables) {
            System.out.println("返回页面不合法");
            throwables.printStackTrace();
        }
        return 0;
    }//判断从其他页面返回到用户信息页面的条件是否合法

    public Founder getFounderByFId(String founderId){
        Founder founder = new Founder();
        Connection conn = DBUtil.getConnection();//传入数据库
        PreparedStatement stmt = null;//防止sql注入
        try {
            stmt = conn.prepareStatement("SELECT * FROM founder WHERE founderId = ?");
            stmt.setString(1,founderId);//传入参数
            ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是username对应的所有参数集合
            if (!resultSet.next()){//判断这个集合是否有值
                System.out.println("找不到对象");
            }
            //获得数据库中的数据
            founder.setFounderId(resultSet.getString("founderId"));
            founder.setPassword(resultSet.getString("password"));
            founder.setFounderName(resultSet.getString("founderName"));
            founder.setSex(resultSet.getString("sex"));
            founder.setAge(resultSet.getInt("age"));
            founder.setEmail(resultSet.getString("email"));
            founder.setProjectNum(resultSet.getInt("projectNum"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return founder;
    }//通过founderId找到founder

    public int judgeFounder(String founderName,String password1,String password2,String sex,String age){
        if (founderName.length()==0 || password1.length()==0 || password2.length()==0 || sex.length()==0 || age.length()==0){
            return 1;//如果填写信息有空缺:返回1
        }else if(!password1.equals(password2)){
            return 2;//如果两个密码的值不同:返回2
        }else{//如果没有问题:信息更新+返回3
            return 3;
        }
    }//改变founder信息操作是否正确

    public void changeFounder(String founderId,String founderName,String password,String sex,int age){
        Connection conn = DBUtil.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE founder SET founderName=?,password=?,sex=?,age=? WHERE founderId=?");
            stmt.setString(1,founderName);
            stmt.setString(2,password);
            stmt.setString(3,sex);
            stmt.setInt(4, age) ;
            stmt.setString(5,founderId);
            stmt.execute(); //控制执行语句
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }//修改founder的信息

    public void deleteProject(int projectNum,String founderId){
        Connection conn = DBUtil.getConnection();
        projectNum--;
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE founder SET projectNum=? WHERE founderId=?");
            stmt.setInt(1,projectNum);
            stmt.setString(2,founderId);
            stmt.execute(); //控制执行语句
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }//将founder的projectNum--

    public void addProject(int projectNum,String founderId){
        Connection conn = DBUtil.getConnection();
        projectNum++;
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE founder SET projectNum=? WHERE founderId=?");
            stmt.setInt(1,projectNum);
            stmt.setString(2,founderId);
            stmt.execute(); //控制执行语句
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
