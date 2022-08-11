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

@WebServlet("/changepassword")
public class changePassword extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
                HttpSession session = req.getSession();
                int sellerid = (int) session.getAttribute("id");
                String oldpassword=req.getParameter("oldpassword");
                String newpassword=req.getParameter("newpassword");
                try {
                    
                    String dbDriver = "com.mysql.jdbc.Driver";
                    String dbURL = "jdbc:mysql:// localhost:3306/";
                    String dbName = "fashioner";
                    String dbUsername = "root";
                    String dbPassword = "hareesh@1705";
                    Class.forName(dbDriver);
                    Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
                    PreparedStatement st = con.prepareStatement("select * from seller where sellerId=?");
                    st.setInt(1, sellerid);
                    ResultSet rs=st.executeQuery();
                    while(rs.next())
                    {
                        String originalpassword=rs.getString("password");
                        if(originalpassword.equals(oldpassword))
                        {
                            st = con.prepareStatement("UPDATE seller SET password=? where sellerId=?");
                            st.setString(1, newpassword);
                            st.setInt(2, sellerid);
                            st.executeUpdate();
                            

                        }
                        else{
                            res.setStatus(406);
                            res.getWriter().print("Password mismatch");
                            return;
                        }

                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
