
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
@WebServlet("/addtocart")
public class addToCart extends HttpServlet{
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

        int productid=Integer.parseInt(req.getParameter("productid"));
        HttpSession session = req.getSession();
        int userid = (int) session.getAttribute("id");    
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            PreparedStatement st = con.prepareStatement("select * from cart where productId=? AND userId=?");
            st.setInt(1,productid);
            st.setInt(2, userid);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                res.setStatus(406);
                res.getWriter().print("product already exist");
                return;
            }
            else{
                st = con.prepareStatement("insert into cart(userId,date,time,productId) values(?,?,?,?)");
		        st.setInt(1,userid);
		        st.setString(2,getDate());
                st.setString(3,getTime());
                st.setInt(4, productid);
		        st.executeUpdate();
		        st.close();

            }

    }catch(Exception e){
        e.printStackTrace();
    }

    }
    
}
