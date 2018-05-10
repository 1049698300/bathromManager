package repeat;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCTools {
    /**
     * 获取数据库链接
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream in=reviewTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties=new Properties();
        properties.load(in);
        String usr=properties.getProperty("user");
        String password=properties.getProperty("password");
        String jdbcUrl=properties.getProperty("jdbcUrl");
        String driverClass=properties.getProperty("driver");
        Class.forName(driverClass);
        Connection conn= DriverManager.getConnection(jdbcUrl,usr,password);
        return conn;
    }
    /**
     * 释放资源
     */
    public static void releaseDB(ResultSet resultSet, Statement statement, Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 更新
     */
    public static void update(String sql){
        Connection conn=null;
        Statement statement=null;
        try {
            conn=getConnection();
            statement=conn.createStatement();
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseDB(null,statement,conn);
        }
    }
}
