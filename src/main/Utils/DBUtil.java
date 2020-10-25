package main.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC与数据库产生连接
 */

//1、将mysql-connector-java-8.0.20.jar放到lib文件夹中

public class DBUtil {
    //当做一个工具类来使用
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//2、加载MySQL驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","2206726208");//3、获得数据库连接
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();//e是Throwable的实例化异常对象，在用catch语句中，相当于一个形参，一旦try捕捉到了异常那么就将这个异常返回给 e，由e处理
            System.out.println("没有文件夹");
        }
        return conn;
    }
}
