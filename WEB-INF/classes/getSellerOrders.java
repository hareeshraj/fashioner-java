import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/getsellerorders")
public class getSellerOrders extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
                HttpSession session = req.getSession();
                int sellerId = (int) session.getAttribute("id");
                try {
                    
                    String dbDriver = "com.mysql.jdbc.Driver";
                    String dbURL = "jdbc:mysql:// localhost:3306/";
                    String dbName = "fashioner";
                    String dbUsername = "root";
                    String dbPassword = "hareesh@1705";
                    Class.forName(dbDriver);
                    Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
                    PreparedStatement st = con.prepareStatement("select products.sellerId,orders.date,orders.time,orders.quantity,orders.price,products.productname,products.image,users.name,users.mobilenumber,users.address from orders join users on orders.userId=users.userId join products on products.productId=orders.productId having sellerId=?");
                    st.setInt(1, sellerId);
                    ResultSet rs=st.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();


                    JSONArray json = new JSONArray();
                    while(rs.next()) {
                      int numColumns = rsmd.getColumnCount();
                      JSONObject obj = new JSONObject();
                      for (int i=1; i<=numColumns; i++) {
                        String column_name = rsmd.getColumnName(i);
                        obj.put(column_name, rs.getObject(column_name));
                      }
                      json.put(obj);
                    }
                    res.setContentType("application/json");
	                res.setCharacterEncoding("UTF-8");
	                res.getWriter().print(json.toString());
	                res.getWriter().flush();

                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
