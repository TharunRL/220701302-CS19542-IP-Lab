
import java.sql.*;  
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibraryJDBC
 */
@WebServlet("/LibraryJDBC")
public class LibraryJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryJDBC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
			ResultSet rs=st.executeQuery("select * from book");  
	        PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        out.println("<!DOCTYPE html>\n"
	        		+ "<html>\n"
	        		+ "<head>\n"
	        		+ "<meta charset=\"UTF-8\">\n"
	        		+ "<title>Books</title>\n"
	        		+"<style>.button{\n"
	        		+ "    width:auto;\n"
	        		+ "    height:40px;\n"
	        		+ "    background-color: #00ADB5;\n"
	        		+ "    color: black;\n"
	        		+ "    border: 0;\n"
	        		+ "    border-radius: 10px;\n"
	        		+ "    font-size: medium;\n"
	        		+ "    margin:10px;\n"
	        		+ "</style>\n"
	        		+ "</head>\n"
	        		+ "<body>");
	        out.println("<h1>Book List</h1>");
	        out.println("<table border='1'>");
	        out.println("<tr>");
	        out.println("<td>Book No</td>");
	        out.println("<td>Title</td>");
	        out.println("<td>Author Name</td>");
	        out.println("<td>Publisher</td>");
	        out.println("<td>Edition</td>");
	        out.println("<td>Price</td>");
	        out.println("</tr>");
	        while (rs.next()) {
	        out.println("<tr>");
	        out.println("<td>" + rs.getInt("accno") +
	        "</td>");
	        out.println("<td>" +rs.getString("title") + "</td>");
	        out.println("<td>" +
	        rs.getString("author") + "</td>");
	        out.println("<td>" + rs.getString("publisher") +
	        "</td>");
	        out.println("<td>" + rs.getString("edition") +
	        "</td>");
	        out.println("<td>" + rs.getDouble("price") +
	    	        "</td>");
	        out.println("</tr>");
	        }out.println("</table></body></html>"); 
			}catch(Exception e){ System.out.println(e);}  
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{   
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");   
			Statement st=con.createStatement();  
			response.setContentType("text/html");
	        
	     	int accno = Integer.parseInt(request.getParameter("accno"));
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        String publisher = request.getParameter("publisher");
	        String edition = request.getParameter("edition");
	        double price = Double.parseDouble(request.getParameter("price")); 
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
			int rowsInserted = st.executeUpdate(String.format("insert into book values (%d,'%s','%s','%s','%s',%f)",accno,title,author,publisher,edition,price));
			if (rowsInserted > 0) {
			    out.println("A new book was inserted successfully!");
			}
			out.println("<a href='./library.html'>HOME</a>");
			out.println("<form method=\"get\" class=\\\"button\\\" action=\"LibraryJDBC\">\n"
					+ "    <input type=\"submit\" class=\"button\" value=\"DISPLAY\"></form></body></html>");
			con.commit();
			con.close();  
			}catch(Exception e){ System.out.println(e);} 
	}
}
