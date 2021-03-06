/**
 * @author Hbs_adithya(IT18258486)
 *UI/UX Designer
 * SLIIT
 */
package saveItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveItemServlet")
public class SaveItemServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int tot = 0;
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			
			Statement statement = con.createStatement();
			
			String Inumber = String.valueOf(request.getParameter("itemname"));
			String sql1 = "select sum(Qnumber) from stock where Inumber='"+Inumber+"'";
			
			ResultSet resultset1 = statement.executeQuery(sql1);
			if(resultset1.next()) {
				 tot = resultset1.getInt(1);
			}
			PreparedStatement ps = con.prepareStatement("insert into item(name,category,subcategory,genericname,unit,price,quantity,manufacturer) values(?,?,?,?,?,?,?,?)");
			
			ps.setString(1, String.valueOf(request.getParameter("itemname")));
			ps.setString(2, String.valueOf(request.getParameter("select11")));
			ps.setString(3, String.valueOf(request.getParameter("select22")));
			ps.setString(4, String.valueOf(request.getParameter("genname")));
			ps.setString(5, String.valueOf(request.getParameter("unit")));
			ps.setDouble(6, Double.valueOf(request.getParameter("price")));
			ps.setInt(7,tot);
			ps.setString(8, String.valueOf(request.getParameter("manufac")));
			
			int i=ps.executeUpdate();
			ps.close();
			con.close();
			PrintWriter out = response.getWriter();
			if(i > 0)
			{
				
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.println("<script>");
				out.println("$(document).ready(function(){");
				out.println("swal ( 'Added Successfully' ,  '' ,  'success' );");
				out.println("});");
				out.println("</script>"); 
			RequestDispatcher rd = request.getRequestDispatcher("addItem.jsp");
			rd.include(request, response);
			}
			else
			{
			out.print("There is a problem in updating Record.");

			}
			

			
			
			//RequestDispatcher rd = request.getRequestDispatcher("addItem.jsp");
			//rd.include(request,response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
