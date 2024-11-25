

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibraryDelete
 */
@WebServlet("/LibrarySearch")
public class LibrarySearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarySearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	try {
		String id=request.getParameter("id");
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");   
		Statement st=con.createStatement();  
		ResultSet rs=st.executeQuery("select * from book where accno="+id);  
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        rs.next();
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
        out.println("Accno : "+rs.getInt("accno"));
        out.println("<br>Title : "+rs.getString("title"));
        out.println("<br>Author : "+rs.getString("author"));
        out.println("<br>Publisher : "+rs.getString("publisher"));
        out.println("<br>Edition : "+rs.getString("edition"));
        out.println("<br>Price : "+rs.getDouble("price")+"<br>");
        out.println("<form name='delete' method='get' action='LibraryDelete'>");
        out.println("<input type='hidden' name='bookid' value="+id+"><input type='submit' class='button' value='DELETE'></form>\n");
        out.println("<form name='edit' method='get' action='LibraryEdit'>");
        out.println("<input type='hidden' name='bookid' value="+id+"><input type='submit' class='button' value='EDIT'></form>\n");
        out.println("</body></html>");
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
