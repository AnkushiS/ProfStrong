

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Allassign
 */
@WebServlet("/Allassign")
public class Allassign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Allassign() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static Connection connectDB() throws SQLException, ClassNotFoundException {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:testuser/password@localhost";
		Properties props = new Properties();
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		Connection conn = DriverManager.getConnection(url, props);
		return conn;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = connectDB();
			
			String sql = "select STUDE_NAME, ASSIGN, ASSIGN_TYPE from student";
			
			

	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        ResultSet result = preStatement.executeQuery();
	        conn.commit();
	    	
	        String line = "All assignments, all students";
		        line += "<table class=" 
		        		+ "\"table table-striped\"" 
		        		+ "style=width:60%>";
		        
		        line += 
	     			"<tr>" 
		        	+"<th>" + "student Name" + "</th> <br>"
	     			+"<th>" + "assignment Name" + "</th> <br>"
	     			+"<th>" + "assignment type" + "</th><br>"
	     			+ "</tr>"
	     			;
		        
		        while(result.next()){
		        	line += "<tr>" 
		        			+"<td>" + result.getString("STUDE_NAME") + "</td>"
		        			+ "<td>" +result.getString("ASSIGN_TYPE") + "</td>"
		        			+ "<td>" +result.getString("ASSIGN") + "</td>"
		        			+"</tr>"
		        	        ;
		        	}
		        
		    line += "</table>";
	     	request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/AllassignUI.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}	         	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
