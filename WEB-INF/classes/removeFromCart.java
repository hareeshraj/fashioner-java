import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/removefromcart")
public class removeFromCart extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
                HttpSession session = req.getSession();
                int userId = (int) session.getAttribute("id");
                int productid=Integer.parseInt(req.getParameter("id"));
                try {
                    
                    String dbDriver = "com.mysql.jdbc.Driver";
                    String dbURL = "jdbc:mysql:// localhost:3306/";
                    String dbName = "fashioner";
                    String dbUsername = "root";
                    String dbPassword = "hareesh@1705";
                    Class.forName(dbDriver);
                    Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
                    PreparedStatement st = con.prepareStatement("delete from cart where userId=? and productId=?");
                    st.setInt(1, userId);
                    st.setInt(2, productid);
                    st.executeUpdate();
                
                }
                catch(Exception e){

                }
            }
        }
