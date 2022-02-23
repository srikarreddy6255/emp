

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/updateServlet")
public class UpdateEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
       
   public void init() {
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "2222");
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
   }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Id = request.getParameter("Id");
		String sal = request.getParameter("salary");
		
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("update details set salary='"+sal+"' where Id = '"+Id+"'");
			PrintWriter out = response.getWriter();
			if (result>0) {
			out.println("<H1>SALARY UPDATED </H2>");
			}else {out.print("<H1> ERROR UPDATING THE SALARY</H1>");}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
