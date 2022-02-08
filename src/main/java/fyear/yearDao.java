package fyear;

import connection.MysqlConnect;
import master.ClassCreationFields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class yearDao {

    public yearDao(){

    }

    public static int saveMethod(String fyear)
    {
        int status =0;
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("insert into createyear(f_year)values(?)");
            ps.setString(1,fyear);
            status = ps.executeUpdate();
           con.close();
        }catch (Exception ex){

        }
        return status;
    }
    public static ArrayList<FClass> getAll(){
        ArrayList<FClass> arrayList = new ArrayList();
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("select * from createyear");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                FClass fClass = new FClass();
                fClass.setId(rs.getInt("id"));
                fClass.setF_year(rs.getString("f_year"));
                arrayList.add(fClass);
            }
            con.close();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return arrayList;
    }
    public static int updateYear(int id,String fyear){
        int status = 0;
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("update createyear set f_year=? where id=?");
            ps.setString(1,fyear);
            ps.setInt(2,id);
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return status;
    }
    public static int deleteId(int fid){
        int status =0;
        try{
            Connection con = MysqlConnect.mysqlconnect();
            PreparedStatement ps = con.prepareStatement("delete from createyear where id=?");
            ps.setInt(1,fid);
            status = ps.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return status;
    }


}
