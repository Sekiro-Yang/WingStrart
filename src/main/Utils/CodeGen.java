package main.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CodeGen {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String GetCode() throws SQLException {
        CodeGen codeGen = new CodeGen();
        String code = codeGen.Random();
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM judge WHERE code = ?");
        stmt.setString(1, code);//传入参数
        ResultSet resultSet = stmt.executeQuery();//返回一个结果集，是personId对应的所有参数集合
        if (!resultSet.next()){ //judgeAccountNum对应的对象不存在
            return code;
        }else{
            return codeGen.GetCode();
        }
    }
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
}
