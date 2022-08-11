
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

@WebServlet("/buynow")
public class buyNow extends HttpServlet{
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

        int productid=Integer.parseInt(req.getParameter("buynowProductid"));
        int price=Integer.parseInt(req.getParameter("buynowtotal"));
        int quantity=Integer.parseInt(req.getParameter("buynowquantity"));
        int totalquantity=Integer.parseInt(req.getParameter("totalquantity"));
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
            PreparedStatement st = con.prepareStatement("insert into orders (userId,productId,price,quantity,date,time) values (?,?,?,?,?,?)");
            st.setInt(1,userid);
            st.setInt(2, productid);
            st.setInt(3, price);
            st.setInt(4, quantity);
            st.setString(5, getDate());
            st.setString(6, getTime());
            st.executeUpdate();

            int updatequantity=totalquantity-quantity;
            st = con.prepareStatement("UPDATE products SET quantity=? WHERE productId=? ");
            st.setInt(1,updatequantity);
            st.setInt(2,productid);
            st.executeUpdate();

            session.setAttribute("price",price);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    

    
    }
}


