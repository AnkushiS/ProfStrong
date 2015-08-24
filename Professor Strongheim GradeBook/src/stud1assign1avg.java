

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
 * Servlet implementation class stud1assign1avg
 */
@WebServlet("/stud1assign1avg")
public class stud1assign1avg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stud1assign1avg() {
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
		getServletContext().getRequestDispatcher("/stud1assign1avgUI.jsp").forward(request, response);		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		try {
			conn = connectDB();
			
			String sql = "select sum(grade) as sum, count(assign_type) as count from student where "
					     + "stude_name=" + "\'" + request.getParameter("Student_name") + "\'" + "AND "
					     + "assign_type=" + "\'" + request.getParameter("assign_type") + "\'"
					     ;
			System.out.println("one stud avg" + sql);
			
	     //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        ResultSet result = preStatement.executeQuery();
	        conn.commit();
	    	String line = "average for student: " + request.getParameter("Student_name") + " for assignment type: " + request.getParameter("assign_type") + ": " ;
		    
		    int average=0;
		        while(result.next()){
		        	average = result.getInt("sum") / result.getInt("count");
		        	}
		        
		    
	     	request.setAttribute("message", line + String.valueOf(average));
			getServletContext().getRequestDispatcher("/stud1assign1avgUI.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}	         	
			}

}
