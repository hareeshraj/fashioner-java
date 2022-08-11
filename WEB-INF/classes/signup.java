
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
import org.json.JSONObject;

@WebServlet("/signup")

public class signup extends HttpServlet{
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
       
        // PrintWriter out=res.getWriter();
        String name=req.getParameter("name");
        String mobileNumber=req.getParameter("mobilenumber");
        String password=req.getParameter("password");
        String confirmPassword=req.getParameter("confirmpassword");
        String address=req.getParameter("address");
     
        
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
        
                PreparedStatement st = con.prepareStatement("select * from users where mobilenumber=?");
                st.setString(1,mobileNumber);
                ResultSet r1=st.executeQuery();
                if(r1.next()){
                    res.setStatus(406);
                    res.getWriter().print("Mobile number already exist");
                    return;
                }
                if(mobileNumber.length()!=10)
                {
                    res.setStatus(406);
                    res.getWriter().print("Enter 10 digit mobileNumber");
                    return;
                }
                if(password.equals(confirmPassword)==false)
                {
                    res.setStatus(406);
                    res.getWriter().print("password mismatch");
                    return;
                }
                st = con.prepareStatement("insert into users(name,mobilenumber,password,date,time,address)values(?,?,?,?,?,?)");
		        st.setString(1,name);
		        st.setString(2,mobileNumber);
		        st.setString(3,password);
		        st.setString(4,getDate());
                st.setString(5,getTime());
                st.setString(6, address);
		        st.executeUpdate();
		        st.close();
            
          

        JSONObject user=new JSONObject();
	    user.put("name", name);
        user.put("mobilenumber",mobileNumber);
        user.put("password", password);
        user.put("Date",getDate());
        user.put("time", getTime());
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


