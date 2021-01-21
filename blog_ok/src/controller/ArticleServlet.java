package controller;

import model.Article;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查看某篇文章的内容
 */
@WebServlet(name = "ArticleServlet",urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数
        String id=request.getParameter("id");
       //2. 根据id查找文章，
        String sql="select * from articles where id="+id;
        //1. 数据库的操作
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= DBUtil.getConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            Article article=new Article();
            if(rs.next()){
                article.setId(rs.getInt("id"));
                article.setAuthor(rs.getString("author"));
                article.setTitle(rs.getString(3));
                article.setContents(rs.getString("contents"));
                article.setCreate_at(rs.getTimestamp("create_at").toLocalDateTime());
            }
            System.out.println(article.toString());
            request.setAttribute("article",article);
            request.getRequestDispatcher("show.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request,response);
    }
}
