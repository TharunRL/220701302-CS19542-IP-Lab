

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibraryDelete
 */
@WebServlet("/LibraryDelete")
public class LibraryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int accno;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{   
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");   
			Statement st=con.createStatement();  
			response.setContentType("text/html");      
	     	int accno = Integer.parseInt(request.getParameter("bookid"));
	        PrintWriter out = response.getWriter();
	        out.println("<html>\n"
	        		+ "<head>\n"
	        		+ "<title>Books</title>\n"
	        		+"<style>.button{\n"
	        		+ "    width:auto;\n"
	        		+ "    height:40px;\n"
	        		+ "    background-color: #00ADB5;\n"
	        		+ "    color: black;\n"
	        		+ "    border: 0;\n"
	        		+ "    border-radius: 10px;\n"
	        		+ "    font-size: medium;\n"
	        		+ "    margin:10px;}\n"
	        		+ "</style>\n"
	        		+ "</head>\n"
	        		+ "<body>");
			int rowsInserted = st.executeUpdate("delete from book where accno="+accno);
			if (rowsInserted > 0) {
			    out.println("A book was deleted successfully!");
			}
			out.println("<a href='./library.html'>HOME</a>");
			out.println("<form method=\"get\" class=\\\"button\\\" action=\"LibraryJDBC\">\n"
					+ "    <input type=\"submit\" class=\"button\" value=\"DISPLAY\"></form></body></html>");
			con.commit();
			con.close();  
			}catch(Exception e){ System.out.println(e);} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
