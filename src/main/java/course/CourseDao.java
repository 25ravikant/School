package course;

import connection.MysqlConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class CourseDao
{
    public CourseDao(){}



    public static int saveCourse(CourseModel cd){
        int status =0;
        try
        {
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("insert into course(courseName,coursePrice)values(?,?)");
            ps.setString(1,cd.getCourseName());
            ps.setInt(2, cd.getCoursePrice());
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return status;
    }


    public static LinkedList<CourseModel> getAll(){
        LinkedList<CourseModel> list = new LinkedList<>();
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("select * from course");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CourseModel cm = new CourseModel();
                cm.setId(rs.getInt("id"));
                cm.setCourseName(rs.getString("courseName"));
                cm.setCoursePrice(rs.getInt("coursePrice"));
                list.add(cm);
            }
            con.close();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public static int updateCourse(CourseModel cm){
        int status = 0;
        try(Connection con = MysqlConnect.mysqlconnect()){
            PreparedStatement ps = con.prepareStatement("update course set courseName=?,coursePrice=? where id=?");
            ps.setString(1,cm.getCourseName());
            ps.setInt(2,cm.getCoursePrice());
            ps.setInt(3,cm.getId());
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return status;
    }

    public static int deleteCourse(int id){
        int status =0;
        try {
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("delete from course where id=?");
            ps.setInt(1,id);
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return status;
    }
}
