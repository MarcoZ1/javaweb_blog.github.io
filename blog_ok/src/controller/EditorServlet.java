package controller;

import model.User;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "EditorServlet",urlPatterns = "/editor.do")
public class EditorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //发表文章
        String title = request.getParameter("title");
        String contents = request.getParameter("contents").replaceAll("'","\\\\\\'");


        //添加文章的sql语句
        String sql = "insert into articles values(null,?,?,?,?,?)";
        //获取当前会话的用户名
        User loginUser=(User)request.getSession().getAttribute("loginUser");


        //添加文章的对象
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = DBUtil.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,loginUser.getUsername());
            pstmt.setString(2,title);
            pstmt.setString(3,contents);
            pstmt.setString(4,LocalDateTime.now().toString());
            pstmt.setString(5,LocalDateTime.now().toString());
            int i = pstmt.executeUpdate();
            if(i>0)
            {
                //封装对象
                request.getRequestDispatcher("index").forward(request,response);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(pstmt,conn);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("editor.jsp").forward(request,response);
    }
}
