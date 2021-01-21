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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexServlet",urlPatterns ="/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 数据库的操作
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= DBUtil.getConnection();
            String sql="select * from articles ORDER BY create_at desc LIMIT 5";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            //处理结果集对象
            List<Article> articles=new ArrayList<Article>();
            while(rs.next()){
                Article article=new Article();
                article.setId(rs.getInt("id"));
                article.setAuthor(rs.getString("author"));
                article.setTitle(rs.getString(3));
                article.setContents(rs.getString("contents"));
                article.setCreate_at(rs.getTimestamp("create_at").toLocalDateTime());
                //封装完一个对象，添加到列表
                articles.add(article);
            }
            //把对象列表保存到共享域
            request.setAttribute("articles",articles);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doPost(request,response);
    }
}
