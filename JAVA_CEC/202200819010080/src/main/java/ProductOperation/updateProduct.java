package ProductOperation;

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
 * Servlet implementation class updateProduct
 */
@WebServlet("/updateProduct")
public class updateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String id= request.getParameter("id");
		String productName= request.getParameter("productName");
		String productDes = request.getParameter("productDes");
		String productPrice = request.getParameter("productPrice");
//		out.print(id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cec", "root", "");
			Statement st = con.createStatement();
			String query = "Update productitem set ProductName = '"+productName+"', ProductDescription = '"+productDes+"',ProductPrice = '"+productPrice+"' where ProductID = '"+id+"'";
			st.executeUpdate(query);
			response.sendRedirect("HomePagejsp.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
