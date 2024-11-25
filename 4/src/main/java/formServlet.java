

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class formServlet
 */
@WebServlet("/formServlet")
public class formServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public formServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	response.setContentType("text/html");
	        
	     	String studentName = request.getParameter("student_name");
	        String rollNo = request.getParameter("rollno");
	        String gender = request.getParameter("gender");
	        String year = request.getParameter("year");
	        String department = request.getParameter("department");
	        String section = request.getParameter("section");
	        String mobileNo = request.getParameter("mobile_no");
	        String emailId = request.getParameter("email_id");
	        String address = request.getParameter("address");
	        String courseName = request.getParameter("cname");
	        String courseCode = request.getParameter("ccode");
	        String credits = request.getParameter("credits");

	        PrintWriter writer = response.getWriter();
	        
	        writer.println("<!DOCTYPE html>");
	        writer.println("<html lang='en'>");
	        writer.println("<head>");
	        writer.println("<meta charset='UTF-8'>");
	        writer.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
	        writer.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
	        writer.println("<title>Student Information</title>");
	        writer.println("</head>");
	        writer.println("<body>");
	        
	        writer.println("<h1>Student Course Information</h1>");
	        writer.println("<p><strong>Student Name:</strong> " + studentName + "</p>");
	        writer.println("<p><strong>Roll Number:</strong> " + rollNo + "</p>");
	        writer.println("<p><strong>Gender:</strong> " + gender + "</p>");
	        writer.println("<p><strong>Year:</strong> " + year + "</p>");
	        writer.println("<p><strong>Department:</strong> " + department + "</p>");
	        writer.println("<p><strong>Section:</strong> " + section + "</p>");
	        writer.println("<p><strong>Mobile Number:</strong> " + mobileNo + "</p>");
	        writer.println("<p><strong>Email ID:</strong> " + emailId + "</p>");
	        writer.println("<p><strong>Address:</strong> " + address + "</p>");
	        writer.println("<p><strong>Course Name:</strong> " + courseName + "</p>");
	        writer.println("<p><strong>Course Code:</strong> " + courseCode + "</p>");
	        writer.println("<p><strong>Credits:</strong> " + credits + "</p>");
	        
	        writer.println("</body>");
	        writer.println("</html>");
	        
	        writer.close();
	}

}
