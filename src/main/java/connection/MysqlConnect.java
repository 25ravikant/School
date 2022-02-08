package connection;
import java.sql.*;

public class MysqlConnect {
    Connection conn = null;
    public static Connection mysqlconnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/appschool?useSSL=true","root","root");
            System.out.println("Success");
            return conn;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }


}
