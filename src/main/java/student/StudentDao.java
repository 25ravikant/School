package student;

import connection.MysqlConnect;
import course.CourseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

public class StudentDao {
    public StudentDao(){}



    public static int searchN(String cName)
    {
        int status = 0;
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("select id from classcreation where class_Name=?");
            ps.setString(1,cName);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
               status = rs.getInt("id");
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return status;
    }

    public static int searchCn(String courseN){
        int status =0;
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("select id from course where courseName=?");
            ps.setString(1,courseN);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                status = rs.getInt("id");
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return status;
    }

    public static int saveStudent(StudentModel st){
         int status = 0;
         try {
             Connection con = MysqlConnect.mysqlconnect();
             PreparedStatement ps = con.prepareStatement("insert into student(studentName,contact,email,classid,courseid)values(?,?,?,?,?)");
             ps.setString(1,st.getStudentName());
             ps.setString(2,st.getContact());
             ps.setString(3,st.getEmail());
             ps.setInt(4,st.getClassid());
             ps.setInt(5,st.getCourseid());
             status =  ps.executeUpdate();
             con.close();
         }catch (Exception ex){
             ex.printStackTrace();
         }

         return status;

    }
    public static int updateStudent(StudentModel sm){
        int status = 0;
        try(Connection con = MysqlConnect.mysqlconnect()){
            PreparedStatement ps = con.prepareStatement("update student set studentName=?,contact=?,email=?,classid=?,courseid=? where id=?");
            ps.setString(1,sm.getStudentName());
            ps.setString(2,sm.getContact());
            ps.setString(3,sm.getEmail());
            ps.setInt(4,sm.getClassid());
            ps.setInt(5,sm.getCourseid());
            ps.setInt(6,sm.getId());
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return status;
    }

    public static int deleteStudent(int id){
        int status =0;
        try {
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("delete from student where id=?");
            ps.setInt(1,id);
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return status;
    }

    public static LinkedList<StudentModel> getAll(){
        LinkedList<StudentModel> list = new LinkedList<>();
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("SELECT classcreation.class_Name,course.courseName,student.id,student.studentName,student.contact,student.email FROM student INNER JOIN classcreation ON classcreation.id = student.classid INNER JOIN course ON course.id=student.courseid");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                StudentModel sm = new StudentModel();
                sm.setId(rs.getInt("id"));
                sm.setStudentName(rs.getString("studentName"));
                sm.setContact(rs.getString("contact"));
                sm.setEmail(rs.getString("email"));
                sm.setClassName(rs.getString("class_Name"));
                sm.setCourseName(rs.getString("courseName"));
                list.add(sm);
            }
            con.close();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
}
