package controller;

import model.User;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单的请求数据，
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        // 把用户的信息封装成登录对象
        User loginUser=new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        //定义数据库的三个对象：
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            //1. 用工具类去数据池获取一个链接对象
            conn= DBUtil.getConnection();
            //2. 定义sql语句
            String sql="select * from users where username=?";
            //3. 先处理？，执行sql语句
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,loginUser.getUsername());
            rs=pstmt.executeQuery();
            if(rs.next()){
                //该用户已经是存在的，进行判断密码是否准确
                //把数据表的查询结果封装对象。
                User resultUser=new User();
                resultUser.setUsername(rs.getString("username"));
                resultUser.setPassword(rs.getString("password"));

                //判断登录用户的密码和查询结果的密码进行匹配，如果正确则把登录的用户写入到共享域中，并跳转到index.jsp
                //否则提示错误信息：密码错误
                if(loginUser.getPassword().equals(resultUser.getPassword())){
                    //用户登录成功
                    //把当前用户写到cookie
                    //1. 创建Cookie，
                    Cookie loginUsername=new Cookie("loginUsername",username);
                    //设置保存时间一个星期：单位是秒:
                    loginUsername.setMaxAge(7*24*60*60);
                    //2. 发送Cookie对象
                    resp.addCookie(loginUsername);
                    //把当前的用户信息保存到session
                    // req.setAttribute("loginUser",loginUser);
                    //1. 获取session对象
                    HttpSession session= req.getSession();
                     // 2. 保存用户信息
                    session.setAttribute("loginUser",loginUser);
//                    req.getRequestDispatcher("index").forward(req,resp);
                    //获取session中的验证码
                    String token=(String)req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
                    //删除session中的验证码
                    req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
                    String code=req.getParameter("code");
                    if(token!=null && token.equalsIgnoreCase(code)) {
                        req.getRequestDispatcher("index").forward(req,resp);
                    }
                    else{
                        req.setAttribute("error","验证码错误，请重新输入密码!");
                        req.getRequestDispatcher("login.jsp").forward(req,resp);
                    }
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

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pstmt,conn);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);

    }
}
