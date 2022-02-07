package jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/1/26 5:15 下午
 * @description JDBC工具类，封装druid连接池
 */
public class JDBCUtils {

    private static DataSource ds;

    static{
        try {
            //1.加载配置文件
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("/Users/mac/IdeaProjects/helloIDEA/ModuleTest/src/jdbc/druid.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            //2.获取连接池对象
             ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        close(null,stmt,conn);
    }



    public static void close(ResultSet rs , Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池对象
     * @return DataSource
     */
    public static DataSource getDataSource(){
        return ds;
    }


}
