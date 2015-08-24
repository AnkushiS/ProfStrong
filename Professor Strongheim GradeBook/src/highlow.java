

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
 * Servlet implementation class highlow
 */
@WebServlet("/highlow")
public class highlow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public highlow() {
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

		getServletContext().getRequestDispatcher("/highlowUI.jsp").forward(request, response);		

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		try {
			conn = connectDB();
			
			String sql = "select min(grade) as min1, max(grade) as max1 from student where "
					     + "assign_type=" + "\'" + request.getParameter("assign_type") + "\'"
					     ;
			
			System.out.println("one ASSIGN avg" + sql);
			
	     //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        ResultSet result = preStatement.executeQuery();
	        conn.commit();
	    	String line = " for assignment type: " + request.getParameter("assign_type") + ",";
	    	while(result.next()){
		        	line += "<br> high grade is: " + result.getInt("min1") + "<br>";
		        	line+= "low grade is: " + result.getInt("max1");
		        	}
		        
		    
	     	request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/highlowUI.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}	         	
			}

	

}
