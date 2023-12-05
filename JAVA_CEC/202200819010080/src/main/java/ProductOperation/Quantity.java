import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Quantity")
public class Quantity extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Quantity() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));
        int op = Integer.parseInt(request.getParameter("op"));

        try {
            HttpSession ses = request.getSession(false);
            ArrayList<ProductDetails> products = (ArrayList<ProductDetails>) ses.getAttribute("products");

            for (ProductDetails product : products) {
                if (product.getProductID() == id) {
                    int quantity = product.getproductQuntity();

                    if (op == 1) {
                        quantity += 1;
                    } else {
                        quantity -= 1;
                    }

                    product.setproductQuntity(quantity);
                }
            }

            response.sendRedirect("Viewcart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
