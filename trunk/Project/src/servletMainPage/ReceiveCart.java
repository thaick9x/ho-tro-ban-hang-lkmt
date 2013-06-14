package servletMainPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

/**
 * Servlet implementation class ReceiveCart
 */
@WebServlet("/ReceiveCart")
public class ReceiveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info = request.getParameter("info");
		String id = request.getParameter("id");
		String amount = request.getParameter("amount");
		response.setContentType("text/plain");
		try {
			if (BusGioHang.receiveCart(info, id, amount).size() == 0) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write(new Gson().toJson(BusGioHang.receiveCart(info, id, amount)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
