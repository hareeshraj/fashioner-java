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

@WebServlet("/login")

public class login extends HttpServlet {
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        String originalPassword="";
        int id=0;
        String mobileNumber=req.getParameter("mobilenumber");
        String password=req.getParameter("password");
        String role=req.getParameter("role");
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            if(mobileNumber.length()!=10)
            {
                res.setStatus(406);
                res.getWriter().print("Enter 10 digit mobileNumber");
                return;
            }
            if(role.equals("User"))
            {
                PreparedStatement st = con.prepareStatement("select * from users where mobilenumber=?");
                st.setString(1,mobileNumber);
                ResultSet rs=st.executeQuery();
                if(!rs.next()){
                    res.setStatus(406);
                    res.getWriter().print("User not found");
                    return;
                }
                st = con.prepareStatement("select * from users where mobilenumber=?");
                st.setString(1,mobileNumber);
                ResultSet r1=st.executeQuery();
                while(r1.next()) {
                    id=r1.getInt("userId"); 
                     originalPassword=r1.getString("password");
                    if(originalPassword.equals(password)==false)
                    {
                    res.setStatus(406);
                    res.getWriter().print("User password wrong");
                    return;

                    }
                }

            }else if(role.equals("Seller")){

                PreparedStatement st = con.prepareStatement("select * from seller where mobilenumber=?");
                st.setString(1,mobileNumber);
                ResultSet rs=st.executeQuery();
                if(!rs.next()){
                    res.setStatus(406);
                    res.getWriter().print("Seller not found");
                    return;
                }
                st = con.prepareStatement("select * from seller where mobilenumber=?");
                st.setString(1,mobileNumber);
                ResultSet r1=st.executeQuery();
                while(r1.next()) {
                    id=r1.getInt("sellerId");                
                     originalPassword=r1.getString("password");
                    if(originalPassword.equals(password)==false)
                    {
                    res.setStatus(406);
                    res.getWriter().print("Seller password wrong");
                    return;

                    }
                }


            }
            HttpSession session = req.getSession();
            session.setAttribute("mobilenumber", mobileNumber);
            session.setAttribute("role", role);
            session.setAttribute("id",id);
            session.setMaxInactiveInterval(-1);

            JSONObject user=new JSONObject();
            user.put("mobilenumber",mobileNumber);
            user.put("password", password);
            user.put("orgpassword",originalPassword);
            user.put("role", role);
            user.put("status","success");

            
            
	        res.setContentType("application/json");
	        res.setCharacterEncoding("UTF-8");
	        res.getWriter().print(user.toString());
	        res.getWriter().flush();    

        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }
    
}
