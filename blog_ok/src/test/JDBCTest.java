package test;


import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试工具类的使用
 */
public class JDBCTest {

    public static void main(String[] args) {
        //1. 定义一个注册对象
        User registerUser=new User();
        registerUser.setUsername("zdh");
        registerUser.setPassword("aaa");
        registerUser.setEmail("111@qq.com");
        //2. 定义两个对象pstmt, conn
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            //3. 获取数据库的链接-----到数据池里获取一个链接池
            conn = DBUtil.getConnection();
            //4. 写sql语句:insert into users(username,password,email) values('zdh','aaa','123@qq.com')
            String sql="insert into users(username,password,email) values(?,?,?)";
            //5. 获取预处理对象：
            pstmt=conn.prepareStatement(sql);
            //6. 注入数据:
            pstmt.setString(1,registerUser.getUsername());
            pstmt.setString(2,registerUser.getPassword());
            pstmt.setString(3,registerUser.getEmail());
            //7. 执行增删改的方法：executeUpdate():
            int i=pstmt.executeUpdate();
            if(i>0){
                System.out.println("用户名添加成功");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(pstmt,conn);
        }

    }

}
