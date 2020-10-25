package main.Utils;

import main.dao.founderDao;
import main.entity.Founder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ActivateCodeGen {
    public boolean getActivateCode(Founder founder){
        String founderId = founder.getFounderId();
        //获得邮箱验证码
        StringBuffer Id = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<6;i++){
            int temp = random.nextInt(10);
            Id.append(temp);
        }
        String emailCode = Id.toString();
        //将邮箱验证码设置到数据库中
        Connection conn = DBUtil.getConnection();
        String sql = "update founder set code=? where founderId=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, emailCode);
            stmt.setString(2, founderId);
            int count = stmt.executeUpdate();
            stmt.close();
            return count>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        founder.setCode(emailCode);
        return false;
    }

    public String Random(String founderId){
        //设置必须
        founderDao founderDao = new founderDao();
        //获得founder对象
        Founder founder = founderDao.getFounderByFId(founderId);
        //生成emailCode并将它设置到MySQL中
        getActivateCode(founder);
        //得到result
        String result = null;
        Connection conn = DBUtil.getConnection();
        String sql = "select * from founder where founderId="+founderId;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(!resultSet.next()){
                System.out.println("找不到对象");
            }
            result = resultSet.getString("code");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //返回结果
        return result;
    }
}
