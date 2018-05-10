package repeat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest {
    public void testGetStu(){
        int searchType=getSearchTypeFromConsole();
        Student student=searchStudent(searchType);
        printStuInfo(student);
    }
    public void printStuInfo(Student student){
        if (student!=null){
            System.out.println(student);
        }else {
            System.out.println("查无此人");
        }

    }
    private Student searchStudent(int searchType){
        String sql="select * from stu where ";
        if (searchType==1){
            System.out.println("");

        }
        Student stu=getStudent(sql);
        System.out.println("");
        return stu;
    }
    private Student getStudent(String sql){
        Student stu=null;
        Connection conn=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try{
            conn=JDBCTools.getConnection();
            statement=conn.createStatement();
            resultSet=statement.executeQuery(sql);
            if (resultSet.next()){
                stu=new Student(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getDate(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCTools.releaseDB(resultSet,statement,conn);
        }
        return stu;
    }
    private int getSearchTypeFromConsole(){
        int i=0;
        System.out.print("请输入查询类型： ");
        Scanner scanner=new Scanner(System.in);
        i=scanner.nextInt();
        if (i!=1&&i!=2){
            System.out.println("输入有误");
            throw new RuntimeException();
        }
        return i;
    }
    public static Student getStudentFromConsole(){
        Scanner scanner=new Scanner(System.in);
        Student stu=new Student();
        System.out.println("name");
        stu.setName(scanner.next());
        System.out.println("age");
        stu.setAge(scanner.nextInt());
        System.out.println("gender");
        stu.setGender(scanner.next());
        System.out.println("birth");
        stu.setBirth(Date.valueOf(scanner.next()));
        return stu;
    }
    public void testAddNewStu(){
        Student student=getStudentFromConsole();
        addNewStu(student);
    }
    public void addNewStu(Student student){
        String sql="insert into stu(`name`,age,gender,birth) values('"
                +student.getName()+"',"+student.getAge()+",'"
                +student.getGender()+"','"+student.getBirth()+"')";
        System.out.println(sql);
        JDBCTools.update(sql);
    }
    public static void main(String[] args) {
        JDBCTest jdbcTest=new JDBCTest();
        jdbcTest.testAddNewStu();

    }
}
