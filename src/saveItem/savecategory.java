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

import groovy.ui.Console;

@WebServlet("/savecategory")
public class savecategory extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("select1").equals("") || request.getParameter("select1").equals(null)) {
			PrintWriter out = response.getWriter();
			out.println("<script language = 'Javascript'>alert('empty!');</script>");
		}
		else {
		try {
			
			Connection con = DatabaseConnection.initializeDatabase();
			Statement statementcat = con.createStatement();
			


			PrintWriter out1 = response.getWriter();
			ResultSet resultSetcat = statementcat.executeQuery("select category from category");
			boolean status = false;
			while(resultSetcat.next()) {
				if(resultSetcat.getString(1).equals(request.getParameter("select1"))) {
					System.out.println("Category already present");
					out1.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
					out1.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
					out1.println("<script>");
					out1.println("$(document).ready(function(){");
					out1.println("swal ( 'Category Already Exists' ,  '' ,  'error' );");
					out1.println("});");
					out1.println("</script>"); 
					RequestDispatcher rd = request.getRequestDispatcher("addItem.jsp");
					rd.include(request, response);
					status = true;
				}
			}
			int j = -1;
			if(status == false) {
				PreparedStatement ps = con.prepareStatement("insert into category(category) values(?)");
				ps.setString(1, String.valueOf(request.getParameter("select1")));
			j=ps.executeUpdate();
			ps.close();
			}


			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from category c where c.category = '" +String.valueOf(request.getParameter("select1")+"'"));
			
			while(resultSet.next()){

			PreparedStatement ps2 = con.prepareStatement("insert into subcategory(id, subcategory) values(?,?)");
			ps2.setInt(1, Integer.valueOf(resultSet.getInt("categoryid")));
			
			
			ps2.setString(2,String.valueOf(request.getParameter("select2")));
			int i =ps2.executeUpdate();
			ps2.close();
			PrintWriter out = response.getWriter();
			if(i > 0 && j > 0)
			{
				
				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.println("<script>");
				out.println("$(document).ready(function(){");
				out.println("swal ( 'Category Added Successfully' ,  '' ,  'success' );");
				out.println("});");
				out.println("</script>"); 
			RequestDispatcher rd = request.getRequestDispatcher("addItem.jsp");
			request.setAttribute("name", request.getParameter("select1"));
			request.setAttribute("subcatname", request.getParameter("select2"));
			request.setAttribute("iname", request.getParameter("itemname"));
			rd.include(request, response);
			}
			else
			{
			out.print("There is a problem in updating Record.");

			}
			}
			con.close();
			

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}

	
	

}
