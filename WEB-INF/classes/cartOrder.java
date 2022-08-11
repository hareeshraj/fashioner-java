
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/cartorder")
public class cartOrder extends HttpServlet{
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

        JSONObject obj=new JSONObject(req.getParameter("map"));
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
            PreparedStatement st ;
            Iterator<String> it=obj.keys();
            int totalprice=0;
            while(it.hasNext())
            {
                int price=0,sellerId=0,totalquantity=0;
                String key=it.next();
                int quantity=obj.getInt(key);
                int productid=Integer.parseInt(key);
                System.out.println(productid+"  "+quantity);
                st= con.prepareStatement("select price,sellerId,productId,quantity from products where productId=?");
                st.setInt(1, productid);
                ResultSet rs=st.executeQuery();
                if(rs.next())
                {
                    totalquantity=(int)rs.getObject("quantity");
                    price=(int)rs.getObject("price");
                    sellerId=(int)rs.getObject("sellerId");
                }
                st = con.prepareStatement("insert into orders (userId,productId,price,quantity,date,time) values (?,?,?,?,?,?)");
                st.setInt(1,userid);
                st.setInt(2, productid);
                st.setInt(3, price*quantity);
                st.setInt(4, quantity);
                st.setString(5, getDate());
                st.setString(6, getTime());
                st.executeUpdate();
                totalprice=totalprice+(price*quantity);
                int updatequantity=totalquantity-quantity;
                st = con.prepareStatement("UPDATE products SET quantity=? WHERE productId=? ");
                st.setInt(1,updatequantity);
                st.setInt(2,productid);
                st.executeUpdate();
            }
            st= con.prepareStatement("DELETE FROM cart WHERE UserId=?");
            st.setInt(1, userid);
            st.executeUpdate();

            session.setAttribute("price",totalprice);  


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    

    
    }
}


