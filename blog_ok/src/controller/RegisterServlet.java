package controller;

import model.User;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取表单的数据，封装成一个注册对象
        HttpSession session = request.getSession();
        User registerUser = new User();

        registerUser.setUsername(request.getParameter("name"));
        registerUser.setPassword(request.getParameter("password"));
        registerUser.setEmail(request.getParameter("email"));

        //先判断用户名是否存在，如果存在的话，提示"用户名已经存在"错误信息，
        int flag = 0;
        //否则，试行添加用户的模块功能
        //2. 定义两个对象pstmt, conn
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //3. 获取数据库的链接-----到数据池里获取一个链接池
            conn = DBUtil.getConnection();

            //判断用户名是否存在
            String sql0 = "SELECT * FROM users";
            //String sql0 = "SELECT * FROM users where username == ?";
            pstmt = conn.prepareStatement(sql0);
            //pstmt.setString(1,registerUser.getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String rs_name = rs.getString("username");
                if (registerUser.getUsername().equals(rs_name)) {
                    flag = 1;
                }
            }

            //用户名不存在，插入数据
            System.out.println(flag);
            if (flag == 0) {
                //4. 写sql语句:insert into users(username,password,email) values('zdh','aaa','123@qq.com')
                String sql = "insert into users(username,password,email) values(?,?,?)";
                //5. 获取预处理对象：
                pstmt = conn.prepareStatement(sql);
                //6. 注入数据:
                pstmt.setString(1, registerUser.getUsername());
                pstmt.setString(2, registerUser.getPassword());
                pstmt.setString(3, registerUser.getEmail());
                //7. 执行增删改的方法：executeUpdate():
                int i = pstmt.executeUpdate();

                String token = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
                //删除session中的验证码
                request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
                String code = request.getParameter("code");

                if (token != null && token.equalsIgnoreCase(code)) {
                    request.getRequestDispatcher("index").forward(request, response);
                } else {
                    request.setAttribute("error", "验证码错误，请重新输入密码!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }

                if (i > 0) {
                    System.out.println("用户名添加成功");
                    //
                    //1. 创建Cookie，
                    Cookie cookie = new Cookie("loginUsername", registerUser.getUsername());
                    //设置保存时间一个星期：单位是秒:
                    cookie.setMaxAge(7 * 24 * 60 * 60);
                    //2. 发送Cookie对象
                    response.addCookie(cookie);

                    //跳转到登录页面
                    request.setAttribute("loginUser", registerUser);
                    session.setAttribute("user", registerUser);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    System.out.println("用户名添加失败");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "注册失败，用户名已存在");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, conn);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
