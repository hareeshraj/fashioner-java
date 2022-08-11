
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/addcategory")
public class addCategory extends HttpServlet{
    public String getDate()
    {
        String date=java.time.LocalDate.now().toString();
        return date;

    }
    public String getTime()
    {
        String time=java.time.LocalTime.now().toString();
        return time;

    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{

        String categoryname=(req.getParameter("categoryname")).toLowerCase();
        HttpSession session = req.getSession();
        int sellerid = (int) session.getAttribute("id");    
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            PreparedStatement st = con.prepareStatement("select * from category where categoryname=?");
            st.setString(1,categoryname);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                res.setStatus(406);
                res.getWriter().print("Category already exist");
                return;
            }
            else{
                st = con.prepareStatement("insert into category(categoryname,date,time,sellerId) values(?,?,?,?)");
		        st.setString(1,categoryname);
		        st.setString(2,getDate());
                st.setString(3,getTime());
                st.setInt(4, sellerid);
		        st.executeUpdate();
		        st.close();

            }

    }catch(Exception e){
        e.printStackTrace();
    }

    }
    
}
