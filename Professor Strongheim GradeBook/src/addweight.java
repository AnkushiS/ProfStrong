

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addweight")
public class addweight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addweight() {
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		getServletContext().getRequestDispatcher("/addweightUI.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		try {
			conn = connectDB();			
			String sql = "insert into assignments(ASSIGN_TYPE, CLASS_NAME, Weight) values("
					+ "\'" + request.getParameter("assignment_type") + "\'" + ","
					+ "\'" + request.getParameter("class_Name") + "\'" + ","
					+  request.getParameter("grade")  
					+ ")";
			
			//creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        preStatement.executeQuery();
	        conn.commit();
	    	
	        String line = "<h3> successfully submitted</h3>";
		    request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/addweightUI.jsp").forward(request, response);
			
	}catch(Exception e){
		e.printStackTrace();
	}	         	

}

}
