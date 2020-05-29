/**
 * @author Hbs_adithya(IT18258486)
 *UI/UX Designer
 * SLIIT
 */
package saveSup;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class DbConnection {
	  protected static Connection initializeDatabase() 
		        throws SQLException, ClassNotFoundException 
		    { 
		        // Initialize all the information regarding 
		        // Database Connection 
		        String dbDriver = "com.mysql.jdbc.Driver"; 
		        String dbURL = "jdbc:mysql:// localhost:3306/"; 
		        // Database name to access 
		        String dbName = "customer"; 
		        String dbUsername = "root"; 
		        String dbPassword = ""; 
		  
		        Class.forName(dbDriver); 
		        Connection con = DriverManager.getConnection(dbURL + dbName, 
		                                                     dbUsername,  
		                                                     dbPassword); 
		        return con; 
		    } 
		} 
