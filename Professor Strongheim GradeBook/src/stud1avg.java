

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
 * Servlet implementation class stud1avg
 */
@WebServlet("/stud1avg")
public class stud1avg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stud1avg() {
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
		getServletContext().getRequestDispatcher("/stud1avgUI.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		try {
			conn = connectDB();
			
			String sql = "select sum(grade) as sum, count(assign_type) as count from student where "
					     + "stude_name=" + "\'" + request.getParameter("Student_name") + "\'"
					     ;
			
			// input is student name
			// get the assignment type and sum of grades for respective assign type and for that class too.
			String sql2 = "select weight, assign_type, class_name from assignments";
			
			PreparedStatement preStatement = conn.prepareStatement(sql2);
	        ResultSet result = preStatement.executeQuery();
	        conn.commit();
	        
	        int[] assign_weight = new int[50];
	        String[] assign_type = new String[50];
	        
	        double[] avg_assign_type = new double[50]; 
	        while(result.next()){
	        	for(int i =0; i<assign_weight.length; i++){
	        		assign_weight[i] = result.getInt("weight");
	        	}
	        	
	        	for(int i =0; i<assign_type.length; i++){
	        		assign_type[i] = result.getString("assign_type");
	        	}
	        }
			
			System.out.println(assign_weight[1]);
			System.out.println(assign_type[2]);
					
	        
			for(int i=0; i<assign_type.length; i++){
				
			
	        String sql1 = "select sum(grade) as G, count(assign_type) as C from student where "
	        		      + "stude_name=" + "\'" + request.getParameter("Student_name") + "\'" + "AND "
						  + "assign_type=" + assign_type[i]
						  ;
	        preStatement = conn.prepareStatement(sql1);
	        ResultSet rs1 = preStatement.executeQuery();
	        conn.commit();
	        	
	       // ResultSet rs1 = conn.prepareStatement(sql1).executeQuery();
		    
	        double weight_on_sum = (assign_weight[i] * rs1.getInt("G") / 100 );
	        
	        for(int j=0; j<avg_assign_type.length;j++){
	        	avg_assign_type[j] = weight_on_sum / rs1.getInt("C");
	        }
	        
	        }    
	        
			double gpa=0;
			for(int k =0; k<avg_assign_type.length; k++){
				gpa += avg_assign_type[k];
			}
			
			double GPA = gpa/avg_assign_type.length;
			
			String line = "the value of average gpa is: " +String.valueOf(GPA);
	     	request.setAttribute("message", line);
			getServletContext().getRequestDispatcher("/stud1avgUI.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}	         	
	
	}

}
