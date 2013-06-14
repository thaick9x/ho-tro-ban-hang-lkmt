package servletNVGD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DaoGioHang;
import com.google.gson.*;
/**
 * Servlet implementation class RefreshListCart
 * Lấy các mã giỏ đã được nhận từ khách hàng
 */
@WebServlet("/RefreshListCart")
public class RefreshListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshListCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		DaoGioHang cart = new DaoGioHang();
		try {
			response.getWriter().print(new Gson().toJson(cart.getListCart()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
