import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateproduct")

public class updateProduct extends HttpServlet {
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        int productid=Integer.parseInt( req.getParameter("productid"));
        String name=req.getParameter("name");
        int quantity=Integer.parseInt(req.getParameter("quantity"));
        int price=Integer.parseInt(req.getParameter("price"));

        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            PreparedStatement st = con.prepareStatement("UPDATE products SET productname=?, quantity=?  ,price=? WHERE productId=?");
            st.setString(1, name);
            st.setInt(2, quantity);
            st.setInt(3, price);
            st.setInt(4, productid);
            st.executeUpdate();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
