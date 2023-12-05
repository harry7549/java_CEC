package ProductOperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/yourDB", "yourUsername", "yourPassword");

		    // Use PreparedStatement to avoid SQL injection
		    String query = "SELECT * FROM users WHERE email = ? AND password = ?";
		    try (PreparedStatement pst = con.prepareStatement(query)) {
		        pst.setString(1, email);
		        pst.setString(2, pass);

		        ResultSet rs = pst.executeQuery();

		        if (rs.next()) {
		            // Authentication successful
		            HttpSession session = request.getSession(true);
		            ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
		            session.setAttribute("products", products);
		            response.sendRedirect("HomePagejsp.jsp");
		        } else {
		            // Authentication failed
		            response.sendRedirect("UserHomePage.jsp");
		        }
		    }
		} catch (Exception e) {
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
