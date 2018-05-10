package repeat;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class reviewTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        reviewTest reviewTest=new reviewTest();
       // reviewTest.testGetConnection();
       // reviewTest.testGetConnection2();
        reviewTest.testStatement();
       // reviewTest.testResultSet();
    }
    public void testResultSet(){
        Connection conn=null;
        Statement statement=null;
        ResultSet rs=null;
        try {
            conn=getConnection();
            statement=conn.createStatement();
            String sql="SELECT * FROM stu";
            rs=statement.executeQuery(sql);
            while (rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                int age=rs.getInt(3);
                String gender=rs.getString(4);
                Date date=rs.getDate(5);
                System.out.println("-------------------------");
                System.out.println(id);
                System.out.println(name);
                System.out.println(age);
                System.out.println(gender);
                System.out.println(date);

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseDB(rs,statement,conn);
        }
    }
    public void testStatement(){
        Connection conn=null;
        Statement statement=null;
        try {
            conn=getConnection();
            statement=conn.createStatement();
            String sql="INSERT INTO stu(`name`,age,gender,birth) VALUES('ccc',11,'man','1999-09-09')";
            statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            releaseDB(null,statement,conn);
        }
    }
    public void releaseDB(ResultSet resultSet,Statement statement,Connection connection){
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
    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
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
    public void testGetConnection2() throws ClassNotFoundException, SQLException, IOException {
        InputStream in=reviewTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties=new Properties();
        properties.load(in);
        String usr=properties.getProperty("user");
        String password=properties.getProperty("password");
        String jdbcUrl=properties.getProperty("jdbcUrl");
        String driverClass=properties.getProperty("driver");
        Class.forName(driverClass);
        Connection conn= DriverManager.getConnection(jdbcUrl,usr,password);
        System.out.println(conn);
    }
    public void testGetConnection() throws ClassNotFoundException, SQLException {
        String usr="root";
        String password="123456";
        String jdbcUrl="jdbc:mysql://localhost:3306/school";
        String driverClass="com.mysql.jdbc.Driver";
        Class.forName(driverClass);
        Connection conn= DriverManager.getConnection(jdbcUrl,usr,password);
        System.out.println(conn);
    }
}
