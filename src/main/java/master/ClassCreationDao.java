package master;
import connection.MysqlConnect;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class ClassCreationDao {

   public ClassCreationDao(){

   }

   public static int addClass(ClassCreationFields custadd)
   {
       int status=0;
       try{

           Connection con=MysqlConnect.mysqlconnect();
           PreparedStatement ps=con.prepareStatement("insert into classcreation(class_Name) values (?)");
           ps.setString(1,custadd.getClass_Name());
           status=ps.executeUpdate();
           con.close();
       }catch(Exception ex){ex.printStackTrace();}

       return status;
   }
   public static int updateClass(ClassCreationFields cls){
       int status = 0;
       try{
           Connection con = MysqlConnect.mysqlconnect();
           PreparedStatement ps = con.prepareStatement("update classcreation set class_Name=? where id=?");
           ps.setString(1,cls.getClass_Name());
           ps.setInt(2,cls.getId());
           status = ps.executeUpdate();
           con.close();
       }catch (Exception ex){
           ex.printStackTrace();
       }
       return status;
}

public static int deleteClass(int cId){
       int status = 0;
       try{
           Connection con = MysqlConnect.mysqlconnect();
           PreparedStatement ps = con.prepareStatement("delete from classcreation where id=?");
           ps.setInt(1,cId);
           status = ps.executeUpdate();
           con.close();
       }catch (Exception ex){
           ex.printStackTrace();
       }
       return status;
}
   public static List<ClassCreationFields> getAllClass(){
       List<ClassCreationFields> list = new ArrayList<ClassCreationFields>();
       try
       {
           Connection con=MysqlConnect.mysqlconnect();
           PreparedStatement ps=con.prepareStatement("select * from classcreation");
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               ClassCreationFields cl=new ClassCreationFields();
               cl.setId(rs.getInt("id"));
               cl.setClass_Name(rs.getString("class_Name"));
               list.add(cl);
           }
           con.close();
       } catch (Exception ex){
           ex.printStackTrace();
       }
       return list;
    }

}
