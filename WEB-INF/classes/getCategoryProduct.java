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

@WebServlet("/getcategoryproduct")
public class getCategoryProduct extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
                int categoryid=Integer.parseInt(req.getParameter("id"));
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
                    PreparedStatement st = con.prepareStatement("select category.categoryname,category.categoryId,products.productId,products.productname,products.quantity,products.price,products.image,products.sellerId from products inner join category on products.categoryId=category.categoryId having categoryId=?");
                    st.setInt(1, categoryid);
                    ResultSet rs=st.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();

                    
                    PreparedStatement st1 = con.prepareStatement("select * from users where userId=?");
                    st1.setInt(1, userid);
                    ResultSet rs1=st1.executeQuery();
                    ResultSetMetaData rsmd1 = rs1.getMetaData();


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
                    while(rs1.next()) {
                      int numColumns = rsmd1.getColumnCount();
                      JSONObject obj = new JSONObject();
                      for (int i=1; i<=numColumns; i++) {
                        String column_name = rsmd1.getColumnName(i);
                        obj.put(column_name, rs1.getObject(column_name));
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
