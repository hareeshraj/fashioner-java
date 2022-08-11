import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletecategory")

public class deletecategory extends HttpServlet {
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        int categoryid=Integer.parseInt(req.getParameter("id"));
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            PreparedStatement st = con.prepareStatement("DELETE FROM category WHERE categoryId=?");
            st.setInt(1,categoryid);
            st.executeUpdate();


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}