

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibraryEdit
 */
@WebServlet("/LibraryEdit")
public class LibraryEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryEdit() {
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
	     	int id = Integer.parseInt(request.getParameter("bookid"));
			ResultSet rs=st.executeQuery("select * from book where accno="+id); 
	        PrintWriter out = response.getWriter();
	        rs.next();
	        String title=rs.getString("title");
	        String author=rs.getString("author");
	        String publisher=rs.getString("publisher");
	        String edition=rs.getString("edition");
	        Double price=rs.getDouble("price");
	        out.println("<html>\n"
	        		+ "<head>\n"
	        		+ "<title>Book Entry</title>\n"
	        		+ "<style>\n"
	        		+ ".button{\n"
	        		+ "    width:auto;\n"
	        		+ "    height:40px;\n"
	        		+ "    background-color: #00ADB5;\n"
	        		+ "    color: black;\n"
	        		+ "    border: 0;\n"
	        		+ "    border-radius: 10px;\n"
	        		+ "    font-size: medium;\n"
	        		+ "    margin:10px;</style>\n"
	        		+ "</head>\n"
	        		+ "<body>\n"
	        		+ "<form  method=\"post\" action=\"LibraryEdit\">\n"
	        		+ "<h1>EDIT BOOK "+id+" </h1>\n"
	        		+ "    <input type=\"hidden\" id=\"accno\" name=\"accno\" value="+id+" ><br><br>\n"
	        		+ "\n"
	        		+ "    <label for=\"title\">Title:</label><br>\n"
	        		+ "    <input type=\"text\" id=\"title\" name=\"title\" value="+title+" required><br><br>\n"
	        		+ "\n"
	        		+ "     <label for=\"author\">Author:</label><br>\n"
	        		+ "     <input type=\"text\" id=\"author\" name=\"author\" value="+author+" required><br><br>\n"
	        		+ "\n"
	        		+ "     <label for=\"publisher\">Publisher:</label><br>\n"
	        		+ "     <input type=\"text\" id=\"publisher\" name=\"publisher\" value="+publisher+" required><br><br>\n"
	        		+ "\n"
	        		+ "     <label for=\"edition\">Edition:</label><br>\n"
	        		+ "     <input type=\"text\" id=\"edition\" name=\"edition\" value="+edition+" required><br><br>\n"
	        		+ "\n"
	        		+ "     <label for=\"price\">Price:</label><br>\n"
	        		+ "     <input type=\"number\" step=\"0.01\" id=\"price\" name=\"price\" value="+price+" required><br><br>\n"
	        		+ "\n"
	        		+ "	<input type=\"submit\" class=\"button\" value=\"Submit\">\n"
	        		+ "</form>\n"
	        		+ "</body>\n"
	        		+ "</html>");
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
			int rowsInserted=st.executeUpdate(String.format("update book set title='%s',author='%s',publisher='%s',edition='%s',price=%f where accno=%d",title,author,publisher,edition,price,accno));
			if (rowsInserted > 0) {
			    out.println("A book was updated successfully!");
			}
			out.println("<a href='./library.html'>HOME</a>");
			out.println("<form method=\"get\" class=\\\"button\\\" action=\"LibraryJDBC\">\n"
					+ "    <input type=\"submit\" class=\"button\" value=\"DISPLAY\"></form></body></html>");
			con.commit();
			con.close();  
			}catch(Exception e){ System.out.println(e);} 
	}

}
