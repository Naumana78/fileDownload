package fileDownload;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownload extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		// get print writer object
		PrintWriter pw = res.getWriter();
	
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		
		
		
		String query = "SELECT * FROM STUDENT";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletpractice","root","Ariba@123");
		ps = con.prepareStatement(query);
		
		
		rs =ps.executeQuery();
		pw.println("<table border='1' align='center' bgcolor='cyan'>");
		pw.println("<tr><th>EMPNO</th><th>ENAME</th><th>EADD</th><th>RESUME</th><th>PHOTO</th>");
		while(rs.next()) {
			pw.println("<tr>");
			pw.println("<td>"+rs.getInt(1)+"</td>");
			pw.println("<td>"+rs.getString(2)+"</td>");
			pw.println("<td>"+rs.getString(3)+"</td>");
			pw.println("<td><a href='downloadurl?resumeid="+rs.getInt(1)+"'>Download Resume</a></td>");
			pw.println("<td><a href='downloadurl?photoid="+rs.getInt(1)+"'>Download Photo</a></td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
			
		
		
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
