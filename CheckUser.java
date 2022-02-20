

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckUser
 */
@WebServlet("/CheckUser")
public class CheckUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		Connection con=DBConnection.DBConnect();
		try {
			PreparedStatement pst = con.prepareStatement("select * from userregister where id=? and pwd=?");
			pst.setString(1, uid);
			pst.setString(2, pwd);
			
			ResultSet rs = pst.executeQuery();
			int count=0;
			while(rs.next())
			{
	
				count++;
			}
			if(count>0)
			{
//				
//				RequestDispatcher rd = request.getRequestDispatcher("Success.html");
//				rd.forward(request, response);
				//pw.println("<h4><font color='red'>Invalid UID or PWD</font></h4>");
				response.sendRedirect("Success.html");
			}
			else
		{
				RequestDispatcher rd = request.getRequestDispatcher("LoginPage.html");
				rd.include(request, response);
				pw.println("<h4><center><font color='red'>Invalid UID or PWD</font></center></h4>");
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
	}

}
