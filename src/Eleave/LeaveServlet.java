/**
 * @author Hbs_adithya(IT18258486)
 *UI/UX Designer
 * SLIIT
 */
package Eleave;

import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

// Import Database Connection Class file 
import saveEmployee.DatabaseConnection; 


@WebServlet("/LeaveServlet") 
public class LeaveServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException 
				{ 
					try { 
						java.util.Date  date =new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Ldate"));
						java.sql.Date sqlDate = new java.sql.Date(date.getTime());
						Connection con = DatabaseConnection.initializeDatabase(); 

					
						PreparedStatement st = con.prepareStatement("insert into eleave(Ename,Ldate,Ltype,reason) values(?,?,?,?)");
						
						
						st.setString(1, String.valueOf(request.getParameter("Ename")));
						st.setDate(2,sqlDate);
						st.setString(3, String.valueOf(request.getParameter("Ltype")));
						st.setString(4, String.valueOf(request.getParameter("reason")));
						st.executeUpdate(); 

						st.close(); 
						con.close(); 

					
						PrintWriter out = response.getWriter(); 

						out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
						out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
						out.println("<script>");
						out.println("$(document).ready(function(){");
						out.println("swal ( 'Leave Marked' ,  '' ,  'success' );");
						out.println("});");
						out.println("</script>"); 
						
						RequestDispatcher rd = request.getRequestDispatcher("Leave.jsp");
						rd.include(request, response);
					
					} 
					catch (Exception e) { 
						e.printStackTrace(); 
					} 
				} 
			} 