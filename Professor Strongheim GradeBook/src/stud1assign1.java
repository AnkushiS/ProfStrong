

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
 * Servlet implementation class stud1assign1
 */
@WebServlet("/stud1assign1")
public class stud1assign1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stud1assign1() {
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
		getServletContext().getRequestDispatcher("/stud1assign1UI.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection conn;
		try {
			conn = connectDB();
			
			String sql = "select * from student where assign_type="
					     + "\'" + request.getParameter("assignment_type") + "\'" + "AND "
					     + "stude_name=" + "\'" + request.getParameter("Student_name") + "\'"
					     ;
			System.out.println("in one stud one assign" + sql);
			
	     //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        ResultSet result = preStatement.executeQuery();
	        conn.commit();
	    	
	        String line = "one students, one assignment type";
		        line += "<table class=" 
		        		+ "\"table table-striped\"" 
		        		+ "style=width:60%>";
		        
		        line += 
	     			"<tr>" 
		        	+"<th>" + "student Name" + "</th> <br>"
	     			+"<th>" + "assignment type" + "</th><br>"
		        	+"<th>" + "assignment name" + "</th><br>"
	     			+"<th>" + "grade" + "</th><br>"
	     			+ "</tr>"
	     			;
		        
		        while(result.next()){
		        	line += "<tr>" 
		        			+"<td>" + result.getString("STUDE_NAME") + "</td>"
		        			+ "<td>" +result.getString("ASSIGN_TYPE") + "</td>"
		        			+"<td>" + result.getString("ASSIGN") + "</td>"
		        			+"<td>" + result.getString("GRADE") + "</td>"
		        			+"</tr>"
		        	        ;
		        	}
		        
		    line += "</table>";
	     	request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/stud1assign1UI.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}	         	
	
	}

	

}
