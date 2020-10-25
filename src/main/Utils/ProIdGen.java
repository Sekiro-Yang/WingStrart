package main.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ProIdGen {
    public String Random(){
        StringBuffer Id = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<6;i++){
            int temp = random.nextInt(10);
            Id.append(temp);
        }
        String result = Id.toString();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String GetProId() throws SQLException {
        ProIdGen proIdGen = new ProIdGen();
        String projectId = proIdGen.Random();
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM project WHERE projectId = ?");
        stmt.setString(1, projectId);//传入参数
        ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是personId对应的所有参数集合
        if (!resultSet.next()){ //personId对应的对象不存在
            return projectId;
        }else{
            return proIdGen.GetProId();
        }
    }
}
