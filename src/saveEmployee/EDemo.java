/**
 * @author Hbs_adithya(IT18258486)
 *UI/UX Designer
 * SLIIT
 */
package saveEmployee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MaintainRoomCleaning
 */
@WebServlet("/EDemo")
public class EDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("Eid","120");
		request.setAttribute("Ename","Adithya");
		request.setAttribute("Eaddress","Chialw");
		request.setAttribute("EBdate","10-12-1997");
		request.setAttribute("Enic","973451383V");
		request.setAttribute("Egender","Male");
		request.setAttribute("Etp","0719451383");
		getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
		
	}

}