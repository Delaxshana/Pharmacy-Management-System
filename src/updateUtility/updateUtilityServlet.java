/**
 * @author Hbs_adithya(IT18258486)
 *UI/UX Designer
 * SLIIT
 */
package updateUtility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commonUtil.DatabaseConnection;

/**
 * Servlet implementation class updateUtilityServlet
 */
@WebServlet("/updateUtilityServlet")
public class updateUtilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateUtilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try { 

			Integer uid = Integer.parseInt(request.getParameter("utilityID"));
			
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			
			
			
			Connection con = DatabaseConnection.initializeDatabase(); 


			PreparedStatement st = con 
				.prepareStatement("Update utilitydetails set utilityID=?,utype=?,description=?,date=?,method=?,amount=? where utilityID ="+uid); 

			st.setInt(1, uid);
			st.setString(2, String.valueOf(request.getParameter("utype"))); 
			st.setString(3, String.valueOf(request.getParameter("description")));
			st.setDate(4,sqlDate); 
			st.setString(5,String.valueOf(request.getParameter("method")));
			st.setDouble(6,Double.valueOf(request.getParameter("amount")));
			

			int i = st.executeUpdate();
			
			st.close(); 
			con.close(); 
			
			PrintWriter out = response.getWriter();
			if(i > 0)
			{

				out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				out.println("<script>");
				out.println("$(document).ready(function(){");
				out.println("swal ( 'Update Successfully' ,  '' ,  'success' );");
				out.println("});");
				out.println("</script>"); 
				
				RequestDispatcher rd = request.getRequestDispatcher("manageUtility.jsp");
				rd.include(request, response);
		    }
	
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		
		
	}

}
