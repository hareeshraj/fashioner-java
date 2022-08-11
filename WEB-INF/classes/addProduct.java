import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet("/addproduct")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class addProduct extends HttpServlet{
   
    String UPLOAD_DIRECTORY = "uploads";
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
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
            // System.out.println();
            HttpSession session = req.getSession();
            int sellerid = (int) session.getAttribute("id");
            String productname=req.getParameter("name").toLowerCase();
            int quantity=Integer.parseInt(req.getParameter("quantity"));
            int price=Integer.parseInt(req.getParameter("price"));
            String category=req.getParameter("category");
                
            int categoryId=0;      
            
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "fashioner";
            String dbUsername = "root";
            String dbPassword = "hareesh@1705";
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
            PreparedStatement st = con.prepareStatement("select * from products where sellerId=? AND  productname=?");
            st.setInt(1,sellerid);
            st.setString(2, productname);
            ResultSet rs=st.executeQuery();
            if(rs.next())
            {
                res.setStatus(406);
                res.getWriter().print("You already added this product");
                return;
            }

            st= con.prepareStatement("select * from category where categoryname=?");
            st.setString(1,category);
            ResultSet r1=st.executeQuery();
            if(r1.next())
            {
                categoryId=r1.getInt("categoryId");
            }
            fileName = sellerid + "-" + productname + "-" + category + "-" + fileName;
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY +"\\";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

            System.out.println(uploadPath);
			for (Part part : req.getParts()) {
				part.write(uploadPath + fileName);
			}
            
            st = con.prepareStatement("insert into products(productname,quantity,price,categoryId,sellerId,image,date,time) values(?,?,?,?,?,?,?,?)");
            st.setString(1,productname);
            st.setInt(2, quantity);
            st.setInt(3, price);
            st.setInt(4, categoryId);
            st.setInt(5, sellerid);
            st.setString(6, fileName);
            st.setString(7,getDate());
            st.setString(8,getTime());
            st.executeUpdate();
            st.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
