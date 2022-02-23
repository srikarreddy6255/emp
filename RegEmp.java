

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
@WebServlet("/addServlet")
public class RegEmp extends HttpServlet {
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
		String Name = request.getParameter("Name");
		String age = request.getParameter("age");
		String sal = request.getParameter("salary");
		String desig = request.getParameter("designation");
		
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into details values('"+Id+"','"+Name+"','"+age+"','"+sal+"','"+desig+"')");
			PrintWriter out = response.getWriter();
			if (result>0) {
			out.println("<H1> USER REGISTERED </H2>");
			}else {out.print("<H1> ERROR CREATING THE USER </H1>");}
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
