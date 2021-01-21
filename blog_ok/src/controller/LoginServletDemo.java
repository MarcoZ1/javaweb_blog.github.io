package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

//@WebServlet("/login")
public class LoginServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //定义数据库的三个对象：
        Connection conn=null;
        Statement stmt=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        //获取表单的请求数据，
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        // 把用户的信息封装成登录对象
        User loginUser=new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        //访问数据库：JDBC技术处理
        try {
            //1. 加载Driver类
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取数据库连接对象 Connection
            // 1)先定义数据库链接的字符串： 数据库名、服务器的登录用户名和密码
            String url = "jdbc:mysql://localhost:3306/blog_db3?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String pwd = "123456";
           // 2)获取数据库连接
            conn= DriverManager.getConnection(url,user,pwd);
           //3. 定义sql语句：查询该用户是否存在?====>改进：1. 用占位符?来替代注入
           // String sql="select * from users where username='"+username+"'";
            String sql="select * from users where username=?";
            //4.1.创建执行sql语句的对象 Statement===>改进：2. 用链接对象获取预处理对象
            //stmt =conn.createStatement();
            pstmt=conn.prepareStatement(sql);
            //改进：3. 注入数据
            pstmt.setString(1,username);

            //4.2 执行sql： 查询功能: executeQuery  结果就ResultSet, 相当于是一个指针，指向结果表的第一条记录之前.
           // rs =stmt.executeQuery(sql);
            //===>改进：4. 执行功能, 不要再方法里面写sql字符串
           rs=pstmt.executeQuery();
           //5. 处理结果
           if(rs.next()){
               //该用户已经是存在的，进行判断密码是否准确
               //把数据表的查询结果封装对象。
               User resultUser=new User();
               resultUser.setUsername(rs.getString("username"));
               resultUser.setPassword(rs.getString("password"));
               //判断登录用户的密码和查询结果的密码进行匹配，如果正确则把登录的用户写入到共享域中，并跳转到index.jsp
               //否则提示错误信息：密码错误
               if(loginUser.getPassword().equals(resultUser.getPassword())){
                   req.setAttribute("loginUser",loginUser);
                   req.getRequestDispatcher("index.jsp").forward(req,resp);
               }
               else{
                   //提示错误信息：密码错误，请重新输入密码。并重新定位到login.jsp
                   req.setAttribute("error","密码错误，请重新输入密码!");
                   req.getRequestDispatcher("login.jsp").forward(req,resp);
               }

           }
           else{
               //提示错误信息：该用户不存在，请重新输入用户名。并重新定位到login.jsp
               req.setAttribute("error","该用户不存在，请重新输入用户名!");
               req.getRequestDispatcher("login.jsp").forward(req,resp);
           }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源, rs,stmt,conn
           try {
               rs.close();
               stmt.close();
               conn.close();
           }
           catch (SQLException e){
               e.printStackTrace();
           }

        }

        //响应用户的请求信息
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
