package jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2022/1/25 11:15 下午
 */
public class JDBCTest {

    @Test
    public void testJDBCTemplateUpdate(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update user set name = ? where id = ?";
        int rows = jdbcTemplate.update(sql, "刘璞测试", 1);
        System.out.println(rows);
    }

    @Test
    public void testJDBCTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        list.forEach(map -> {
            System.out.println();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.print(entry.getKey() + " : " + entry.getValue() + " ");
            }
        });
        System.out.println();

    }


    @Test
    public void testJDBCUtils() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from user";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + " : " + name);
        }

        JDBCUtils.close(resultSet, ps, connection);

    }


    @Test
    public void testJDBC() throws Exception {

        //属性文件读取
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/mac/IdeaProjects/helloIDEA/ModuleTest/src/config.properties");
        properties.load(fileInputStream);
        fileInputStream.close();

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Connection connection = null;
        Statement statement = null;

        try {
            //1.驱动加载
            Class.forName(driver);

            //2.获取连接
            connection = DriverManager.getConnection(url, user, password);

            //3.根据连接创建sql封装对象
            statement = connection.createStatement();

            //4.执行sql（DQL）并返回结果集
            ResultSet resultSet = statement.executeQuery("select * from user");

            //DML
//            int rows = statement.executeUpdate("udpate user set name = '' where id = 1");

            //5.解析结果集
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(id + " : " + name);
            }

            //6.释放资源
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
