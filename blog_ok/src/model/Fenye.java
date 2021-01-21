package model;

import util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Fenye {
    //当前页数
    private Integer currentPage;
    //每页的数据量
    private Integer pageSize;
    //总页数
    private Integer totalPage;
    //总条数
    private Integer totalCount;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    private List<Article> articles;
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public Integer getTotalPage() {

        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;

    }

    public static List<Article> queryStudentByPage(int currentPage, int pageSize) {
        String sql="select * from articles ORDER BY create_at desc LIMIT ?,?";
        List<Article> articles = new ArrayList<>();
//        Object[] params = {(currentPage-1)*pageSize,pageSize};
        Object[] params = {(currentPage-1)*pageSize,pageSize};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while(rs.next()) {
                Article article=new Article();
                article.setId(rs.getInt("id"));
                article.setAuthor(rs.getString("author"));
                article.setTitle(rs.getString(3));
                article.setContents(rs.getString("contents"));
                article.setCreate_at(rs.getTimestamp("create_at").toLocalDateTime());
                //封装完一个对象，添加到列表
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, DBUtil.pstmt, DBUtil.con);
        }
        return articles;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
//        this.totalPage = this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
    }



}
