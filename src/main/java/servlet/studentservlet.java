package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class studentservlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			String sql="insert into employee(rollno,name,branch,email,password) values(?,?,?,?,?)";
			PreparedStatement pmst=conn.prepareStatement(sql);
			String rollno=req.getParameter("rollno");
			String name=req.getParameter("name");
			String branch=req.getParameter("branch");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			
			pmst.setString(1, rollno);
			pmst.setString(2, name);
			pmst.setString(3, branch);
			pmst.setString(4, email);
			pmst.setString(5, password);
			int i=pmst.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if(i>0) {
				pw.println("successfully inserted ");
			}
			else {
				pw.println(" not successfully inserted ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
