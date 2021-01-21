package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据池的工具类====创建数据库的链接池
 */
public class DBUtil {
    public static Connection con = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;

    //1. 定义数据池的变量，用静态修饰
    private  static DataSource ds;
    static{
        try {
            //1. 加载配置文件
            Properties  properties=new Properties();
            InputStream is =DBUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            //2. 通过工厂DruidDataSourceFactory获取DataSource
            ds=DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取链接池
     * @return  数据池的一个链接
     */
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }

    /**
     * 释放资源====关闭增删该的操作
     * @param stmt===执行对象
     * @param conn===链接对象
     */
    public static void close(Statement stmt, Connection conn){
        try {
            if (stmt != null) {
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 释放资源====关闭查询的操作
     * @param rs=====结果集对象
     * @param stmt===执行对象
     * @param conn===链接对象
     */
    public static void close(ResultSet rs, Statement stmt,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            close(stmt,conn);
/*            if (stmt != null) {
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }*/
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static DataSource getDataSource(){
        return ds;
    }

    //获取数据总数
    public static int getCount() {
        int count = -1;
        try {
            String sql = "select count(*) from articles";
            pstmt = createPstmt(sql, null);
            rs = pstmt.executeQuery();
            if(rs.next())
                count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
        return count;
    }
    public static PreparedStatement createPstmt(String sql,Object[] params) throws SQLException {
        pstmt = getConnection().prepareStatement(sql);
        if(params != null) {
            for(int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1, params[i]);
            }
        }
        return pstmt;
    }

    //通用查询
    public static ResultSet executeQuery(String sql, Object[] params) {
        try {
            pstmt = createPstmt(sql,params);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
