package controller;

import model.Article;
import model.Fenye;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AricleListServlet",urlPatterns = "/list")
public class ArticleListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 数据库的操作
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            conn= DBUtil.getConnection();
//            String sql="select * from articles ORDER BY create_at desc LIMIT ?,?";
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setInt(1,8);
//            pstmt.setInt(2,15);
//            rs=pstmt.executeQuery();
//            pstmt = DBUtil.createPstmt(sql,null);
            HttpSession session = request.getSession();
            int count = DBUtil.getCount();
            Fenye fenye = new Fenye();
            String cPage = request.getParameter("currentPage");
            if(cPage == null)
                cPage = "1";
            //当前页数
            int currentPage = Integer.parseInt(cPage);

            String cPageSize = request.getParameter("pageSize");
            if(cPageSize == null)
                cPageSize = "6";
            //每页数据量
            int pageSize = Integer.parseInt(cPageSize);
//            /打印当前页数的数据
            List<Article> articles = Fenye.queryStudentByPage(currentPage, pageSize);
            fenye.setPageSize(pageSize);
            fenye.setCurrentPage(currentPage);
            fenye.setTotalCount(count);
            fenye.setArticles(articles);
            session.setAttribute("currentPage",currentPage);
            session.setAttribute("count",count);
//            List<Article> articles = Fenye.queryStudentByPage(1,8);
            //处理结果集对象
//            List<Article> articles=new ArrayList<Article>();
//            while(rs.next()){
//                Article article=new Article();
//                article.setId(rs.getInt("id"));
//                article.setAuthor(rs.getString("author"));
//                article.setTitle(rs.getString(3));
//                article.setContents(rs.getString("contents"));
//                article.setCreate_at(rs.getTimestamp("create_at").toLocalDateTime());
//                //封装完一个对象，添加到列表
//                articles.add(article);
//            }
            //把对象列表保存到共享域
            request.setAttribute("articles",articles);
            request.getRequestDispatcher("list.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,pstmt,conn);
        }

    }
}
